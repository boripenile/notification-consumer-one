package com.example.notification.consumer.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.kafka.consumer")
public class CustomConsumerProperties {

	private String bootstrapServers;
	
	private String keyDeserializer;
	
	private String valueDeserializer;
	
	private String autoOffsetReset;
	
	private int maxPartitionFetchBytes;
	
	private int maxPollIntervalMs;
	
	private int maxPollRecords;
	
	private int heartbeatIntervalMs;
	
	private int sessionTimeoutMs;
	
	private int fetchMaxWaitMs;
	
	private int fetchMinBytes;
	
	private boolean enableAutoCommit;
	
	private int autoCommitIntervalMs;
	
	private String isolationLevel;

	public String getBootstrapServers() {
		return bootstrapServers;
	}

	public void setBootstrapServers(String bootstrapServers) {
		this.bootstrapServers = bootstrapServers;
	}

	public String getKeyDeserializer() {
		return keyDeserializer;
	}

	public void setKeyDeserializer(String keyDeserializer) {
		this.keyDeserializer = keyDeserializer;
	}

	public String getValueDeserializer() {
		return valueDeserializer;
	}

	public void setValueDeserializer(String valueDeserializer) {
		this.valueDeserializer = valueDeserializer;
	}

	public String getAutoOffsetReset() {
		return autoOffsetReset;
	}

	public void setAutoOffsetReset(String autoOffsetReset) {
		this.autoOffsetReset = autoOffsetReset;
	}

	public int getMaxPartitionFetchBytes() {
		return maxPartitionFetchBytes;
	}

	public void setMaxPartitionFetchBytes(int maxPartitionFetchBytes) {
		this.maxPartitionFetchBytes = maxPartitionFetchBytes;
	}

	public int getMaxPollIntervalMs() {
		return maxPollIntervalMs;
	}

	public void setMaxPollIntervalMs(int maxPollIntervalMs) {
		this.maxPollIntervalMs = maxPollIntervalMs;
	}

	public int getMaxPollRecords() {
		return maxPollRecords;
	}

	public void setMaxPollRecords(int maxPollRecords) {
		this.maxPollRecords = maxPollRecords;
	}

	public int getHeartbeatIntervalMs() {
		return heartbeatIntervalMs;
	}

	public void setHeartbeatIntervalMs(int heartbeatIntervalMs) {
		this.heartbeatIntervalMs = heartbeatIntervalMs;
	}

	public int getSessionTimeoutMs() {
		return sessionTimeoutMs;
	}

	public void setSessionTimeoutMs(int sessionTimeoutMs) {
		this.sessionTimeoutMs = sessionTimeoutMs;
	}

	public int getFetchMaxWaitMs() {
		return fetchMaxWaitMs;
	}

	public void setFetchMaxWaitMs(int fetchMaxWaitMs) {
		this.fetchMaxWaitMs = fetchMaxWaitMs;
	}

	public int getFetchMinBytes() {
		return fetchMinBytes;
	}

	public void setFetchMinBytes(int fetchMinBytes) {
		this.fetchMinBytes = fetchMinBytes;
	}

	public boolean isEnableAutoCommit() {
		return enableAutoCommit;
	}

	public void setEnableAutoCommit(boolean enableAutoCommit) {
		this.enableAutoCommit = enableAutoCommit;
	}

	public int getAutoCommitIntervalMs() {
		return autoCommitIntervalMs;
	}

	public void setAutoCommitIntervalMs(int autoCommitIntervalMs) {
		this.autoCommitIntervalMs = autoCommitIntervalMs;
	}

	public String getIsolationLevel() {
		return isolationLevel;
	}

	public void setIsolationLevel(String isolationLevel) {
		this.isolationLevel = isolationLevel;
	}

}
