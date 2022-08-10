package br.com.dbcarkafka.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ManutencaoMongoDTO {

    private Integer idManutencao;
    
    private String placaCarro;
    
    private String servico;
    
    private Double valorTotal;
    
    private LocalDate dataManutencao;
}
