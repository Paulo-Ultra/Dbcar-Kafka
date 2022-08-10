package br.com.dbcarkafka.service;

import br.com.dbcarkafka.dto.ManutencaoDTO;
import br.com.dbcarkafka.entity.ManutencaoEntity;
import br.com.dbcarkafka.repository.ManutencaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumidorService {

    private final ObjectMapper objectMapper;

    private final ManutencaoRepository manutencaoRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // TODO CONCLUIR O MÉTODO DEPOIS...

    @KafkaListener(
            topics = "${kafka.topic}",
            groupId = "manutencao1",
            containerFactory = "listenerContainerFactory",
            clientIdPrefix = "manutencaoDBCar")
    public void consumir(@Payload String mensagem) throws JsonProcessingException {
        ManutencaoDTO manutencaoDTO = objectMapper.readValue(mensagem, ManutencaoDTO.class);
        ManutencaoEntity manutencaoEntity = objectMapper.convertValue(manutencaoDTO, ManutencaoEntity.class);
        ManutencaoEntity manutencaoSalvar = manutencaoRepository.save(manutencaoEntity);

        log.info("Uma nova manutenção foi adicionada...");
    }
}
