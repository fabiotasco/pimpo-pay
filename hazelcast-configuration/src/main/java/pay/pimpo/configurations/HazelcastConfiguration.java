
package pay.pimpo.configurations;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.config.QueueConfig;
import com.hazelcast.config.QueueStoreConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

/**
 * Configurações do Hazelcast para utilização das filas.
 *
 * @author FabioTasco
 */
@Configuration
public class HazelcastConfiguration {

	public static final String CLEARING_QUEUE_NAME = "ClearingQueue";

	@Bean(name = "hazelcastInstance", destroyMethod = "shutdown")
	public HazelcastInstance createHazelcastInstance() {
		final HazelcastInstance hazelcastInstance
			= Hazelcast.getOrCreateHazelcastInstance(createHazelcastConfiguration());

		return hazelcastInstance;
	}

	private Config createHazelcastConfiguration() {
		final Config config = new Config("PimpoPayHazelcastConfiguration");
		config.setNetworkConfig(createNetworkConfig());
		config.setQueueConfigs(createQueueConfigs());

		return config;
	}

	private NetworkConfig createNetworkConfig() {
		final NetworkConfig networkConfig = new NetworkConfig();
		networkConfig.setPort(10000);
		networkConfig.setPortCount(30);
		networkConfig.setPortAutoIncrement(true);

		return networkConfig;
	}

	private Map<String, QueueConfig> createQueueConfigs() {
		final Map<String, QueueConfig> queueConfigMap = new HashMap<>();
		queueConfigMap.put("PimpoPayQueues", createQueueConfig(CLEARING_QUEUE_NAME));

		return queueConfigMap;
	}

	private QueueConfig createQueueConfig(final String queueName) {
		final QueueConfig queueConfig = new QueueConfig();
		queueConfig.setMaxSize(Integer.MAX_VALUE); // Armazena a fila toda em memória.
		queueConfig.setName(queueName);
		queueConfig.setQueueStoreConfig(createQueueStoreConfig());

		return queueConfig;
	}

	private QueueStoreConfig createQueueStoreConfig() {
		final QueueStoreConfig queueStoreConfig = new QueueStoreConfig();
		queueStoreConfig.setEnabled(true);
		queueStoreConfig.setProperty("binary", "true");
		queueStoreConfig.setProperty("memory-limit", "500000");
		queueStoreConfig.setProperty("bulk-load", "1000");

		return queueStoreConfig;
	}

}
