package dh.homework.consumer.service;

import dh.homework.consumer.domain.Site;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import static dh.homework.consumer.config.KafkaConsumerConfig.SITE_TOPIC_NAME;

@Service
@Slf4j
public class SiteListenerService {

    @KafkaListener(topics = SITE_TOPIC_NAME, containerFactory = "siteListenerContainerFactory")
    public void consume(@Payload Site site,
                        @Header(KafkaHeaders.RECEIVED_KEY) ConsumerRecord<String, Site> consumerRecord,
                        @Header(KafkaHeaders.GROUP_ID) String groupId) {
        log.info("\n{} consume new message {} : {}\n" +
                "Offset: {}\n" +
                "Partition: {}",
                groupId, consumerRecord.key(), site, consumerRecord.offset(), consumerRecord.partition());
    }


}
