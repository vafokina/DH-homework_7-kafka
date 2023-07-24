package dh.homework.producer.service;

import dh.homework.producer.domain.Site;
import java.util.function.BiConsumer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import static dh.homework.producer.config.KafkaProducerConfig.SITE_TOPIC_NAME;
import static dh.homework.producer.config.KafkaProducerConfig.TOPIC_NAME;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class SenderService {

    KafkaTemplate<String, String> kafkaTemplate;
    KafkaTemplate<String, Site> siteKafkaTemplate;
    StringListenerCallback stringListenerCallback;
    SiteListenerCallback siteListenerCallback;

    public void sendMessage(String key, String message) {
        val future = kafkaTemplate.send(TOPIC_NAME, key, message);
        future.whenComplete(stringListenerCallback);
    }

    public void sendSiteMessage(Site site) {
        val future = siteKafkaTemplate.send(SITE_TOPIC_NAME, site.getExternalId(), site);
        future.whenComplete(siteListenerCallback);
    }
}
