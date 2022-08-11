package br.com.dbcarkafka.entity;

import br.com.dbcarkafka.enums.StatusManutencao;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@Document(collection = "manutencao")
public class ManutencaoEntity {

    @Id
    private Integer idManutencao;
    
    @Field(name = "placa")
    private String placaCarro;
    
    @Field(name = "servico")
    private String servico;
    
    @Field(name = "valor")
    private Double valorTotal;
    
    @Field(name = "data_manutencao")
    private LocalDate dataManutencao;

    @Field(name = "status_manutencao")
    private StatusManutencao status;
}
