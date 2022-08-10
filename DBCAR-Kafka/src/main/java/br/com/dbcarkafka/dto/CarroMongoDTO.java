package br.com.dbcarkafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarroMongoDTO {

    private Integer idCarroMongo;

    private String marcaMongo;

    private String modeloMongo;

    private LocalDate anoFabricacao;

    private String placaMongo;
}
