package essw.com.utils;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.KeeperException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class KafkaOffsetZKUtil {
    private ZkClient zkClient = null;

    public KafkaOffsetZKUtil(String uri) {
        zkClient = new ZkClient(uri, 3000);
    }

    public KafkaOffsetZKUtil(String uri, int sessionTimeout) {
        zkClient = new ZkClient(uri, 3000);
    }


    private String getTopicPath(String topic) {
        StringBuffer sb = new StringBuffer();
        sb.append("/kafka-consumer/");
        sb.append(topic);
        return sb.toString();
    }

    private String getTopicPartitionPath(String topic, Integer partition) {
        StringBuffer sb = new StringBuffer();
        sb.append(getTopicPath(topic));
        sb.append("/");
        sb.append(String.valueOf(partition));
        return sb.toString();
    }

    private boolean makeSurePathExists(String zkPath) throws KeeperException, InterruptedException {
        if (!zkClient.exists(zkPath)) {
            zkClient.createPersistent(zkPath, true);
        }

        return true;
    }

    public Map<Integer, Long> readOffset(String topic, Collection<Integer> partitions) {
        HashMap<Integer, Long> hm = new HashMap<>();

        for (Integer partition : partitions) {
            Object data = zkClient.readData(getTopicPartitionPath(topic, partition));
            hm.put(partition, Long.parseLong(data.toString()));
        }

        return hm;
    }

    public boolean writeOffset(String topic, Integer partition, Long offset) {
        try {
            String zkPath = getTopicPartitionPath(topic, partition);
            makeSurePathExists(zkPath);
            zkClient.writeData(zkPath, String.valueOf(offset));
        } catch (KeeperException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
