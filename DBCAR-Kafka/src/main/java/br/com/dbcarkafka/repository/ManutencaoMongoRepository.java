package br.com.dbcarkafka.repository;


import br.com.dbcarkafka.dto.ManutencaoMongoDTO;
import br.com.dbcarkafka.entity.ManutencaoMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ManutencaoMongoRepository extends MongoRepository<ManutencaoMongoEntity, Integer> {

    @Query(value = "{ 'dataManutencao' : { $lte : ?0 }, 'valorTotal' : { $lte :  ?1 } }")
    List<ManutencaoMongoDTO> findDataManutencaoAndValor(LocalDate data, Double valor);
}
