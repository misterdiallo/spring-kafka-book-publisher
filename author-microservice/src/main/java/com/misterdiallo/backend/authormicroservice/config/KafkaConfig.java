package com.misterdiallo.backend.authormicroservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaConfig {
    @Bean
    public NewTopic booksPublishedTopic(final KafkaConfigProps kafkaConfigProps) {
        return TopicBuilder.name(kafkaConfigProps.getTopic())
                .partitions(10)
                .replicas(1)
                .build();
    }
}
