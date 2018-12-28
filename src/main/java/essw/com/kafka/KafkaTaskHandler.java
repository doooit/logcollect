package essw.com.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import essw.com.utils.KafkaOffsetZKMgr;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class KafkaTaskHandler {
    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(KafkaTaskHandler.class);

    static Properties prop = null;
    public static String TOPIC_NAME = "LogNotify";
    public static String GROUP_NAME = "pull-group1";
    public static String ZK_SERVERS = "master1:2181,master2:2181,node1:2181";
    public static String KAFKA_SERVERS = "node4:9092,node5:9092,node6:9092";

    private KafkaConsumer<String, String> consumer = null;

    static {
        prop = new Properties();
        prop.put("bootstrap.servers", KAFKA_SERVERS);
        prop.put("enable.auto.commit", "false");
        prop.put("group.id", GROUP_NAME);
        prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        prop.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    }

    private KafkaOffsetZKMgr kafkaOffsetZKUtil = null;

    public KafkaTaskHandler() {

        consumer = new KafkaConsumer<>(prop);

        consumer.subscribe(Arrays.asList(TOPIC_NAME));

        kafkaOffsetZKUtil = new KafkaOffsetZKMgr(ZK_SERVERS);
    }

    private Long fetchOffset(Integer partition) {
        return kafkaOffsetZKUtil.readOffset(TOPIC_NAME, GROUP_NAME, Arrays.asList(partition)).get(partition);
    }

    private boolean commitOffset(Integer partition, Long offset) {
        // offset + 1 => ack + 1
        return kafkaOffsetZKUtil.writeOffset(TOPIC_NAME, GROUP_NAME, partition, offset + 1);
    }

    public void run() {

        // 1. 恢复消息队列消费 offset
        boolean restored = false;
        Map<Integer, Long> curOffset = new HashMap<>();
        Map<Integer, Boolean> modifyFlag = new HashMap<>();

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);

            if (!restored) {
                // 恢复消费 offset
                for (PartitionInfo pi: consumer.partitionsFor(KafkaTaskHandler.TOPIC_NAME)) {
                    Long offset = this.fetchOffset(pi.partition());
                    TopicPartition tp = new TopicPartition(KafkaTaskHandler.TOPIC_NAME, pi.partition());
                    consumer.seek(tp, offset);

                    // 当前消费到的 offset
                    curOffset.put(pi.partition(), offset);
                    modifyFlag.put(pi.partition(), false);
                    logger.info("seek partition " + pi.partition() + " to " + offset);
                }

                restored = true;
            }

            if (!records.isEmpty()) {
                logger.info("========================Begin========================");

                // 2. 消费消息，根据消息执行相应任务
                for (ConsumerRecord<String, String> record : records) {
                    // 记录当前 offset
                    if (curOffset.get(record.partition()) <= record.offset()) {
                        curOffset.put(record.partition(), record.offset());
                        modifyFlag.put(record.partition(), true);
                    }

                    try{
                        JSONObject jb = JSON.parseObject(record.value());
                        if (jb != null) {
                            logger.info("recordOffset = " + record.offset() + ", recordPartition = " + record.partition() + ", recordValue = " + record.value());
                        } else {
                            logger.error("recordOffset = " + record.offset() + ", recordPartition = " + record.partition() + ", Empty message, skip it.");
                        }
                    } catch(JSONException ex) {
                        logger.error("recordOffset = " + record.offset() + ", recordPartition = " + record.partition() + ", Invalid msg: " + record.value() + ", could not parsed by JsonParser");
                    }
                }

                // 3. 消费之后，确认当前消费 offset
                for (Map.Entry<Integer, Long> mapEntry : curOffset.entrySet()) {
                    // offset 写入到 zookeeper
                    if (modifyFlag.get(mapEntry.getKey())) {
                        logger.info("partition is " + mapEntry.getKey() + ", offset is " + mapEntry.getValue());
                        this.commitOffset(mapEntry.getKey(), mapEntry.getValue());
                    }
                }

                logger.info("======================== End ========================");
            }
        }
    }
}
