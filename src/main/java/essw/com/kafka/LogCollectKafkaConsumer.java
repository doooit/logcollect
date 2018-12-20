package essw.com.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import essw.com.utils.KafkaOffsetZKUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.log4j.Logger;

import java.util.*;

public class LogCollectKafkaConsumer extends KafkaConsumer<String, String>{
    static Properties prop = null;
    static String TOPIC_NAME = "LogNotify";
    static String GROUP_NAME = "pull-group1";
    static String ZK_SERVERS = "master1:2181,master2:2181,node1:2181";
    static String KAFKA_SERVERS = "node4:9092,node5:9092,node6:9092";


    static {
        prop = new Properties();
        prop.put("bootstrap.servers", KAFKA_SERVERS);
        prop.put("enable.auto.commit", "false");
        prop.put("group.id", GROUP_NAME);
        prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        prop.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    }

    private KafkaOffsetZKUtil kafkaOffsetZKUtil = null;

    public LogCollectKafkaConsumer() {
        super(prop);

        this.subscribe(Arrays.asList(TOPIC_NAME));

        kafkaOffsetZKUtil = new KafkaOffsetZKUtil(ZK_SERVERS);
    }

    public Long fetchOffset(Integer partition) {
        return kafkaOffsetZKUtil.readOffset(TOPIC_NAME, Arrays.asList(partition)).get(partition);
    }

    public boolean commitOffset(Integer partition, Long offset) {
        // offset + 1 => ack + 1
        return kafkaOffsetZKUtil.writeOffset(TOPIC_NAME, partition, offset + 1);
    }

    public static void main(String[] args) {


        LogCollectKafkaConsumer logConsumer = new LogCollectKafkaConsumer();

        boolean restored = false;
        Map<Integer, Long> curOffset = new HashMap<>();
        Map<Integer, Long> zkOffset = new HashMap<>();

        while (true) {
            ConsumerRecords<String, String> records = logConsumer.poll(100);

            if (!restored) {
                // 恢复消费 offset
                for (PartitionInfo pi: logConsumer.partitionsFor(LogCollectKafkaConsumer.TOPIC_NAME)) {
                    Long offset = logConsumer.fetchOffset(pi.partition());
                    TopicPartition tp = new TopicPartition(LogCollectKafkaConsumer.TOPIC_NAME, pi.partition());
                    logConsumer.seek(tp, offset);

                    // 当前 offset
                    curOffset.put(pi.partition(), offset);

                    zkOffset.put(pi.partition(), 0L);
                }

                restored = true;
            }

            if (!records.isEmpty()) {
                System.out.println("========================Begin========================");
            }

            for (ConsumerRecord<String, String> record : records) {
                // 记录当前 offset
                if (curOffset.get(record.partition()) < record.offset()) {
                    curOffset.put(record.partition(), record.offset());
                }

                try{
                    JSONObject jb = JSON.parseObject(record.value());
                    if (jb != null) {
                        System.out.println("recordOffset = " + record.offset() + ", recordPartition = " + record.partition() + ", recordValue = " + record.value());
                    } else {
                        System.out.println("recordOffset = " + record.offset() + ", recordPartition = " + record.partition() + ", Empty message.");
                    }
                } catch(JSONException ex) {
                    System.out.println("recordOffset = " + record.offset() + ", recordPartition = " + record.partition() + ", recordValue = " + record.value());
                }

            }

            if (!records.isEmpty()) {
                for (Map.Entry<Integer, Long> mapEntry : curOffset.entrySet()) {
                    // offset 写入到 zookeeper
                    System.out.println("partition is " + mapEntry.getKey() + ", offset is " + mapEntry.getValue());
                    logConsumer.commitOffset(mapEntry.getKey(), mapEntry.getValue());
                }

                System.out.println("========================End========================");
            }
        }
    }
}


