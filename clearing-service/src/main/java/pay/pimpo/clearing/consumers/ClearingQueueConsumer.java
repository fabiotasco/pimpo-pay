
package pay.pimpo.clearing.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

import pay.pimpo.clearing.rules.ClearingRules;
import pay.pimpo.commons.entities.Transaction;
import pay.pimpo.configurations.HazelcastConfiguration;

@Component
public class ClearingQueueConsumer implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(ClearingQueueConsumer.class);

	private final IQueue<Transaction> clearingQueue;

	@Autowired
	private ClearingRules clearingRules;

	public ClearingQueueConsumer(@Autowired final HazelcastInstance hazelcastInstance) {
		clearingQueue = hazelcastInstance.getQueue(HazelcastConfiguration.CLEARING_QUEUE_NAME);
	}

	@Override
	public void run(final String... args) throws Exception {
		while (true) {
			try {
				if (!clearingQueue.isEmpty()) {
					clearingRules.process(clearingQueue.take());
				}
			} catch (final Exception e) {
				LOG.error("Could not process transaction!", e);
			}
		}
	}

}
