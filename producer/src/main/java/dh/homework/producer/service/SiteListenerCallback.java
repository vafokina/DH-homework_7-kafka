package dh.homework.producer.service;

import dh.homework.producer.domain.Site;
import java.util.function.BiConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SiteListenerCallback implements BiConsumer<SendResult<String, Site>, Throwable> {

    @Override
    public void accept(SendResult<String, Site> stringStringSendResult, Throwable throwable) {
        if (throwable != null) {
            log.error("Cannot send message to Kafka", throwable);
        } else {
            log.info("Send message to Kafka:\n" +
                    "Offset: {}\n" +
                    "Partition: {}",
                    stringStringSendResult.getRecordMetadata().offset(),
                    stringStringSendResult.getRecordMetadata().partition());
        }
    }
}
