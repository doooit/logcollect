package essw.com.kafka;

import essw.com.utils.KafkaOffsetZKUtil;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class LogCollectKafkaConsumer extends KafkaConsumer<String, String>{
    static Properties prop = null;
    public static String TOPIC_NAME = "LogNotify";
    public static String GROUP_NAME = "pull-group1";
    public static String ZK_SERVERS = "master1:2181,master2:2181,node1:2181";
    public static String KAFKA_SERVERS = "node4:9092,node5:9092,node6:9092";

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
}


