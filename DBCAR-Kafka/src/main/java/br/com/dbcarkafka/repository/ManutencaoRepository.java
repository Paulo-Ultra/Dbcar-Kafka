package br.com.dbcarkafka.repository;

import br.com.dbcarkafka.entity.ManutencaoEntity;
import br.com.dbcarkafka.enums.StatusManutencao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManutencaoRepository extends MongoRepository<ManutencaoEntity, String> {

    @Query(value = "{ 'status' : ?0 }")
    List<ManutencaoEntity> findByStatus(StatusManutencao status);
}
