package br.com.dbcarkafka.service;

import br.com.dbcarkafka.dto.ManutencaoDTO;
import br.com.dbcarkafka.entity.ManutencaoEntity;
import br.com.dbcarkafka.enums.StatusManutencao;
import br.com.dbcarkafka.repository.ManutencaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumidorService {

    private final ObjectMapper objectMapper;

    private final ManutencaoRepository manutencaoRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    @KafkaListener(
            topics = "${kafka.topic}",
            groupId = "manutencao1",
            topicPartitions = {@TopicPartition(topic = "${kafka.topic}", partitions = {"0"})},
            containerFactory = "listenerContainerFactory",
            clientIdPrefix = "manutencaoDBCar")
    public void consumir(@Payload String mensagem,
                         @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition) throws JsonProcessingException {
        ManutencaoDTO manutencaoDTO = objectMapper.readValue(mensagem, ManutencaoDTO.class);
        ManutencaoEntity manutencaoEntity = objectMapper.convertValue(manutencaoDTO, ManutencaoEntity.class);
        manutencaoRepository.save(manutencaoEntity);

        imprimirMensagem(manutencaoDTO);

    }

    public List<ManutencaoDTO> findByStatus (StatusManutencao status){
        List<ManutencaoEntity> manutencaoEntities = manutencaoRepository.findByStatus(status);
        return manutencaoEntities.stream().map(manutencaoEntity -> {
            ManutencaoDTO manutencaoDTO = objectMapper.convertValue(manutencaoEntity, ManutencaoDTO.class);
            return manutencaoDTO;
        }).toList();
    }
    
    public List<ManutencaoDTO> listServicosByPlaca (String placa){
        List<ManutencaoEntity> manutencaoEntities = manutencaoRepository.findListServicosByPlaca(placa);
        return manutencaoEntities.stream().map(manutencaoEntity -> {
            ManutencaoDTO manutencaoDTO = objectMapper.convertValue(manutencaoEntity, ManutencaoDTO.class);
            return manutencaoDTO;
        }).toList();
    }

    public ManutencaoDTO atualizarManutencao(ManutencaoDTO manutencaoDTO, String placa) {
        ManutencaoEntity manutencaoEntity = manutencaoRepository.findByPlacaAndStatus(placa, StatusManutencao.PENDENTE);
        manutencaoDTO.setIdManutencao(manutencaoEntity.getIdManutencao());
        manutencaoDTO.setPlacaCarro(manutencaoEntity.getPlacaCarro());
        manutencaoDTO.setStatus(StatusManutencao.CONCLUIDA);
        manutencaoDTO.setDataManutencao(manutencaoEntity.getDataManutencao());
        ManutencaoEntity manutencaoEntity1 = objectMapper.convertValue(manutencaoDTO, ManutencaoEntity.class);
        manutencaoRepository.save(manutencaoEntity1);
        ManutencaoDTO manutencaoDTO1 = objectMapper.convertValue(manutencaoEntity1, ManutencaoDTO.class);
        return manutencaoDTO1;
    }

    @Scheduled(cron = "0 0 3 * * *")
    public void listManutencoesPendentes(){
        List<ManutencaoEntity> manutencaoEntities = manutencaoRepository.obterDataManutencao(LocalDate.now(), LocalDate.now().minusYears(1));
        manutencaoRepository.deleteAll(manutencaoEntities);
    }

    public void imprimirMensagem (ManutencaoDTO manutencaoDTO) {
        String data = manutencaoDTO.getDataManutencao().format(formatter);
        String placa = manutencaoDTO.getPlacaCarro();
        log.info("Uma nova manutenção foi adicionada. Veículo de placa: " + placa + " Data que foi adicionado: "
                    + data + ".");
    }
}
