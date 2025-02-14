/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ucan.sdp2.conectacore.kafka;


import java.util.HashMap;
import java.util.Map;

import edu.ucan.sdp2.conectacore.models.TransacaoConecta;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import static edu.ucan.sdp2.conectacore.enums.ConectaTopico.*;


/**
 *
 * @author escutelo
 */
@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String offsetReset;

    private final int TOPIC_REPLICA = 1;

    private final int TOPIC_PARTICOES = 1;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerProps());
    }

    private Map<String, Object> consumerProps() {
        var props = new HashMap<String, Object>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, offsetReset);
        return props;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerProps());
    }

    private Map<String, Object> producerProps() {
        var props = new HashMap<String, Object>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

//    private NewTopic buildTopic(String nome) {
//
//        return TopicBuilder
//                .name(nome)
//                .replicas(TOPIC_REPLICA)
//                .partitions(TOPIC_PARTICOES)
//                .build();
//    }

//    @Bean
//    public NewTopic StartSaga() {
//        return buildTopic(CONECTA.getTopico());
//    }
//
//    @Bean
//    public NewTopic BaseOrquestrador() {
//        return buildTopic(TRANSFERENCIA_SUCESS.getTopico());
//    }
//
//    @Bean
//    public NewTopic FinishSuccess() {
//        return buildTopic(FINISH_SUCCESS.getTopico());
//    }
//
//    @Bean
//    public NewTopic FinishFail() {
//        return buildTopic(FINISH_FAIL.getTopico());
//    }
//
//    @Bean
//    public NewTopic PagamentoSucess() {
//        return buildTopic(PAGAMENTO_SUCCESS.getTopico());
//    }
//
//    @Bean
//    public NewTopic PagamentoFail() {
//        return buildTopic(PAGAMENTO_FAIL.getTopico());
//    }
//
//    @Bean
//    public NewTopic MovimentoSucess() {
//        return buildTopic(MOVIMENTOS_SUCCESS.getTopico());
//    }
//
//    @Bean
//    public NewTopic MovimentoFail() {
//        return buildTopic(MOVIMENTOS_FAIL.getTopico());
//    }

}
