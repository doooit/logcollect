package essw.com.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaTaskProducer {
    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(KafkaTaskProducer.class);

    static Properties prop = null;
    public static String TOPIC_NAME = "LogNotify";
    public static String KAFKA_SERVERS = "node4:9092,node5:9092,node6:9092";

    private KafkaProducer<String, String> producer = null;

    static {
        prop = new Properties();
        prop.put("bootstrap.servers", KAFKA_SERVERS);
        prop.put("acks", "all"); // 所有follower都响应了才认为消息提交成功，即"committed"
        prop.put("retries", 0);
        prop.put("batch.size", 16384); // producer将试图批处理消息记录，以减少请求次数.默认的批量处理消息字节数
        // bach.size当批量的数据大小达到设定值后，就会立即发送，不顾下面的linger.ms
        prop.put("linger.ms", 1);// 延迟1ms发送，这项设置将通过增加小的延迟来完成--即，不是立即发送一条记录，producer将会等待给定的延迟时间以允许其他消息记录发送，这些消息记录可以批量处理
        prop.put("buffer.memory", 33554432); // producer可以用来缓存数据的内存大小。
        prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
    }

    public KafkaTaskProducer() {
        producer= new KafkaProducer<>(prop);
    }

    public void produce(String message) {
        try {
            producer.send(new ProducerRecord<>(TOPIC_NAME, null, null, null, message)).get();
        } catch (InterruptedException e) {
            logger.error("send msg fail: " + e.toString());
            System.exit(1);
        } catch (ExecutionException e) {
            logger.error("send msg fail: " + e.toString());
            System.exit(1);
        }
    }

    @Override
    protected void finalize() {
        producer.close();
    }
}
