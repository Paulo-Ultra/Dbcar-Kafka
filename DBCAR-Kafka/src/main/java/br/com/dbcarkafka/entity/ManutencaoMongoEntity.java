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
@Document(collection = "manutencao")
@RequiredArgsConstructor
@Getter
@Setter
public class ManutencaoMongoEntity {

    @Id
    @Field(name = "id_manutencao")
    private Integer idManutencao;
    
    @Field(name = "placa")
    private String placaCarro;
    
    @Field(name = "servico")
    private String servico;
    
    @Field(name = "valor")
    private Double valorTotal;
    
    @Field(name = "data_manutencao")
    private LocalDate dataManutencao;
}
