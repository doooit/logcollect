import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import essw.com.kafka.LogCollectKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        //1.创建spring的ioc容器对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");

        //2.从ioc容器中获取bean实例
        LogCollectKafkaConsumer logConsumer = (LogCollectKafkaConsumer) ctx.getBean("LogCollectKafkaConsumer");

        boolean restored = false;
        Map<Integer, Long> curOffset = new HashMap<>();

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
