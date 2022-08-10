package br.com.dbcarkafka.service;



import br.com.dbcarkafka.dto.ManutencaoMongoDTO;
import br.com.dbcarkafka.entity.ManutencaoMongoEntity;
import br.com.dbcarkafka.exception.RegraDeNegocioException;
import br.com.dbcarkafka.repository.ManutencaoMongoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ManutencaoMongoService {
    private final ManutencaoMongoRepository manutencaoMongoRepository;
    private final ObjectMapper objectMapper;
    
    public List<ManutencaoMongoDTO> listManutencao() throws RegraDeNegocioException {
        if (!manutencaoMongoRepository.findAll().isEmpty()) {
            return manutencaoMongoRepository.findAll()
                    .stream()
                    .map(manutencaoMongoEntity -> {
                        ManutencaoMongoDTO manutencaoDTO = convertManutencaoDTO(manutencaoMongoEntity);
                        return manutencaoDTO;
                    }).toList();
        } else {
            throw new RegraDeNegocioException("Não foi possível realizar a listagem das Manutenções.");
        }
    }
    
    public List<ManutencaoMongoDTO> listDataManutencaoEValor(LocalDate data, Double valor) throws RegraDeNegocioException {
        log.info("Listando as manutenções realizadas no período...");
        if (manutencaoMongoRepository.findDataManutencaoAndValor(data, valor).isEmpty()) {
            throw new RegraDeNegocioException("Não há registro de nenhuma manutenção com os parâmetros informados.");
        } else {
            return manutencaoMongoRepository.findDataManutencaoAndValor(data, valor);
        }
    }
    
    private ManutencaoMongoDTO convertManutencaoDTO(ManutencaoMongoEntity manutencaoEntity) {
        return objectMapper.convertValue(manutencaoEntity, ManutencaoMongoDTO.class);
    }
}
