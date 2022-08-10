package br.com.dbcarkafka.repository;

import br.com.dbcarkafka.entity.ManutencaoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ManutencaoRepository extends MongoRepository<ManutencaoEntity, String> {
}
