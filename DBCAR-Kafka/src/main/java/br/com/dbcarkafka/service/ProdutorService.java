package br.com.dbcarkafka.service;

import br.com.dbcarkafka.dto.ManutencaoDTO;
import br.com.dbcarkafka.repository.ManutencaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProdutorService {

    private final KafkaTemplate<String, String>kafkaTemplate;

    private final ObjectMapper objectMapper;

    private final ManutencaoRepository manutencaoRepository;

    @Value("${kafka.client-id}")
    private String empresa;

    @Value("${kafka.topic}")
    private String topico;

    public void enviarCarroParaManutencao(ManutencaoDTO manutencaoDTO) throws JsonProcessingException {
        String carroObjetoString = objectMapper.writeValueAsString(manutencaoDTO);
        enviarMensagem(carroObjetoString, topico);
    }

    // TODO VERIFICAR ESTE MÉTODO DEPOIS (JÁ QUE ELE NÃO QUER LOG);
    private void enviarMensagem(String mensagem, String topico) {
        MessageBuilder<String> stringMessageBuilder = MessageBuilder.withPayload(mensagem)
                .setHeader(KafkaHeaders.TOPIC, topico)
                .setHeader(KafkaHeaders.MESSAGE_KEY, UUID.randomUUID().toString());
        Message<String> stringMessage = stringMessageBuilder
                .build();

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(stringMessage);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult result) {
                log.info(" Log enviado para o kafka com o texto: {} ", mensagem);
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error(" Erro ao publicar duvida no kafka com a mensagem: {}", mensagem, ex);
            }
        });
    }
}
