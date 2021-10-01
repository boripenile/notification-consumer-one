package com.pangaea.notification.consumer.events;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pangaea.notification.consumer.kafka.CustomConsumerProperties;
import com.pangaea.notification.consumer.kafka.TopicProperties;

@Service
public class ConsumerEventManager {

	Properties consumerProperties = null;
	
	@Autowired
	private TopicProperties topic;
	
	@Autowired
	private CustomConsumerProperties values;
	
	private KafkaConsumer<String, byte[]> consumerTemplate;

	public JSONObject readCurrentMessage(String url) {
		JSONObject jsonObject = null;
		try {
			consumerProperties = new Properties();
			consumerProperties.put("bootstrap.servers", values.getBootstrapServers());
			consumerProperties.put("max.poll.records", values.getMaxPollRecords());
			consumerProperties.put("key.deserializer", values.getKeyDeserializer());
			consumerProperties.put("value.deserializer", values.getValueDeserializer());
			consumerProperties.put("auto.offset.reset", values.getAutoOffsetReset());
			consumerProperties.put("fetch.min.bytes", values.getFetchMinBytes());
			consumerProperties.put("fetch.max.wait.ms", values.getFetchMaxWaitMs());
			consumerProperties.put("heartbeat.interval.ms", values.getHeartbeatIntervalMs());
			consumerProperties.put("max.poll.interval.ms", values.getMaxPollIntervalMs());
			consumerProperties.put("max.partition.fetch.bytes", values.getMaxPartitionFetchBytes());
			consumerProperties.put("session.timeout.ms", values.getSessionTimeoutMs());
			consumerProperties.put("enable.auto.commit", values.isEnableAutoCommit());
			consumerProperties.put("auto.commit.interval.ms", values.getAutoCommitIntervalMs());
			consumerProperties.put("isolation.level", values.getIsolationLevel());
			
			String groupId = "notify-group-"+Base64.getEncoder().encodeToString(url.getBytes());
			consumerProperties.put("group.id", groupId);
			consumerProperties.put("group.instance.id", Base64.getEncoder().encodeToString(url.getBytes()));
			consumerTemplate = new KafkaConsumer<String, byte[]>(consumerProperties);
			TopicPartition topicPartition = new TopicPartition(topic.getName(), 0);
			consumerTemplate.assign(Collections.singletonList(topicPartition));
			
			ConsumerRecords<String, byte[]> consumerRecords =
					consumerTemplate.poll(Duration.ofMillis(1000));
			for(TopicPartition partition : consumerRecords.partitions()) {
				List<ConsumerRecord<String, byte[]>> partitionRecords = consumerRecords.records(partition);
				for(ConsumerRecord<String, byte[]> record : partitionRecords) {
					jsonObject = new JSONObject(new String(record.value(), StandardCharsets.UTF_8));
				}
				long lastOffSet = partitionRecords.get(partitionRecords.size() - 1).offset();
				consumerTemplate.commitSync(Collections.singletonMap(partition, 
						new OffsetAndMetadata(lastOffSet + 1)));
			}
		} catch (Exception e) {
		}
		return jsonObject;
	}
}
