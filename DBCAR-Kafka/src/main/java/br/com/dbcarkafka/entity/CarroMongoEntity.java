package br.com.dbcarkafka.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Document(collection = "carro")
@RequiredArgsConstructor
@Getter
@Setter
public class CarroMongoEntity {

    @Id
    @Field(name = "id_carro_mongo")
    private Integer idCarroMongo;

    @Field(name = "marca")
    private String marcaMongo;

    @Field(name = "modelo")
    private String modeloMongo;

    @Field(name = "ano_fabricacao")
    private LocalDate anoFabricacao;

    @Field(name = "placa")
    private String placaMongo;
}
