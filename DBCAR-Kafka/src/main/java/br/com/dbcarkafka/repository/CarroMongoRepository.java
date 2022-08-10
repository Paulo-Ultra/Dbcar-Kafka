package br.com.dbcarkafka.repository;

import br.com.dbcarkafka.entity.CarroMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarroMongoRepository extends MongoRepository<CarroMongoEntity, Integer> {

    @Query("{ $and: [ { 'anoFabricacao': { $gte: ?0 } }, { 'anoFabricacao': {$lte: ?1 }}]}")
    List<CarroMongoEntity> obterCarroPorAnoFabricacao(LocalDate de, LocalDate ate);

}
