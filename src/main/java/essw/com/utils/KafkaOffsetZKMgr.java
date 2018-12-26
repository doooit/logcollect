package essw.com.utils;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class KafkaOffsetZKMgr {
    private ZkClient zkClient = null;

    public KafkaOffsetZKMgr(String uri) {
        zkClient = new ZkClient(uri, 3000);
    }

    public KafkaOffsetZKMgr(String uri, int sessionTimeout) {
        zkClient = new ZkClient(uri, 3000);
    }


    private String getTopicPath(String topic) {
        StringBuffer sb = new StringBuffer();
        sb.append("/kafka-consumer/");
        sb.append(topic);
        return sb.toString();
    }

    private String getTopicGroupPath(String topic, String group) {
        StringBuffer sb = new StringBuffer();
        sb.append(getTopicPath(topic));
        sb.append("/");
        sb.append(group);
        return sb.toString();
    }

    private String getTopicPartitionPath(String topic, String group, Integer partition) {
        StringBuffer sb = new StringBuffer();
        sb.append(getTopicGroupPath(topic, group));
        sb.append("/");
        sb.append(String.valueOf(partition));
        return sb.toString();
    }

    private boolean makeSurePathExists(String zkPath) {
        if (!zkClient.exists(zkPath)) {
            zkClient.createPersistent(zkPath, true);
        }

        return true;
    }

    public Map<Integer, Long> readOffset(String topic, String group, Collection<Integer> partitions) {
        HashMap<Integer, Long> hm = new HashMap<>();

        for (Integer partition : partitions) {
            try {
                Object data = zkClient.readData(getTopicPartitionPath(topic, group, partition));
                hm.put(partition, Long.parseLong(data.toString()));
            } catch (ZkNoNodeException exc) {
                hm.put(partition, 0L);
            }
        }

        return hm;
    }

    public boolean writeOffset(String topic, String group, Integer partition, Long offset) {
        String zkPath = getTopicPartitionPath(topic, group, partition);
        makeSurePathExists(zkPath);
        zkClient.writeData(zkPath, String.valueOf(offset));

        return true;
    }
}
