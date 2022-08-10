package br.com.dbcarkafka.service;

import br.com.dbcarkafka.dto.CarroMongoDTO;
import br.com.dbcarkafka.entity.CarroMongoEntity;
import br.com.dbcarkafka.exception.RegraDeNegocioException;
import br.com.dbcarkafka.repository.CarroMongoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarroMongoService {
    
    private final ObjectMapper objectMapper;
    private final CarroMongoRepository carroMongoRepository;
    
    public List<CarroMongoDTO> carroMongoDTOList() throws RegraDeNegocioException {
        if (!carroMongoRepository.findAll().isEmpty()) {
            return carroMongoRepository.findAll().stream()
                    .map(carroMongoEntity -> {
                        CarroMongoDTO carroMongoDTO = carroMongoEntityToDTO(carroMongoEntity);
                        return carroMongoDTO;
                    }).toList();
        } else {
            throw new RegraDeNegocioException("Não foi possível realizar a listagem dos carros");
        }
    }
    
    public List<CarroMongoDTO> listCarroMongoPorFabricacao(LocalDate de, LocalDate ate) throws RegraDeNegocioException {
        if (!carroMongoRepository.obterCarroPorAnoFabricacao(de, ate).isEmpty()) {
            return carroMongoRepository.obterCarroPorAnoFabricacao(de, ate).stream()
                    .map(carroMongoEntity -> {
                        CarroMongoDTO carroMongoDTO = carroMongoEntityToDTO(carroMongoEntity);
                        return carroMongoDTO;
                    }).toList();
        } else {
            throw new RegraDeNegocioException("Não foi possível realizar a listagem dos carros");
        }
    }
    
    public CarroMongoDTO carroMongoEntityToDTO(CarroMongoEntity carroMongoEntity) {
        return objectMapper.convertValue(carroMongoEntity, CarroMongoDTO.class);
    }
    
}
