package dh.homework.consumer.service;

import dh.homework.consumer.config.KafkaConsumerConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ListenerService {

    @KafkaListener(topics = KafkaConsumerConfig.TOPIC_NAME)
    public void consume(@Payload String message,
                        @Header(KafkaHeaders.RECEIVED_KEY) ConsumerRecord<String, String> consumerRecord,
                        @Header(KafkaHeaders.GROUP_ID) String groupId) {
        log.info("\n{} consume new message {} : {}\n" +
                "Offset: {}\n" +
                "Partition: {}",
                groupId, consumerRecord.key(), message, consumerRecord.offset(), consumerRecord.partition());
    }

    @KafkaListener(topics = KafkaConsumerConfig.TOPIC_NAME, groupId = "another-group")
    public void consumeFromAnotherGroup(@Payload String message,
                        @Header(KafkaHeaders.RECEIVED_KEY) ConsumerRecord<String, String> consumerRecord,
                        @Header(KafkaHeaders.GROUP_ID) String groupId) {
        log.info("\n{} consume new message {} : {}\n" +
                        "Offset: {}\n" +
                        "Partition: {}",
                groupId, consumerRecord.key(), message, consumerRecord.offset(), consumerRecord.partition());
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = KafkaConsumerConfig.TOPIC_NAME, partitions = {"2", "4", "6", "7", "9"}), groupId = "one-more-group")
    public void consumeCertainPartitions(@Payload String message,
                        @Header(KafkaHeaders.RECEIVED_KEY) ConsumerRecord<String, String> consumerRecord,
                        @Header(KafkaHeaders.GROUP_ID) String groupId) {
        log.info("\n{} consume new message {} : {}\n" +
                        "Offset: {}\n" +
                        "Partition: {}",
                groupId, consumerRecord.key(), message, consumerRecord.offset(), consumerRecord.partition());
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = KafkaConsumerConfig.TOPIC_NAME, partitionOffsets = {
            @PartitionOffset(partition = "0", initialOffset = "0"),
            @PartitionOffset(partition = "1", initialOffset = "0"),
            @PartitionOffset(partition = "2", initialOffset = "0"),
            @PartitionOffset(partition = "3", initialOffset = "0"),
    }), groupId = "and-one-more-group")
    public void consumeForAllTime(@Payload String message,
                        @Header(KafkaHeaders.RECEIVED_KEY) ConsumerRecord<String, String> consumerRecord,
                        @Header(KafkaHeaders.GROUP_ID) String groupId) {
        log.info("\n{} consume new message {} : {}\n" +
                        "Offset: {}\n" +
                        "Partition: {}",
                groupId, consumerRecord.key(), message, consumerRecord.offset(), consumerRecord.partition());
    }
}
