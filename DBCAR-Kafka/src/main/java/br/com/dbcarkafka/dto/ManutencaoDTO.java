package br.com.dbcarkafka.dto;

import br.com.dbcarkafka.enums.StatusManutencao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManutencaoDTO {

    private Integer idManutencao;
    private String placaCarro;
    private String servico;
    private Double valorTotal;
    private LocalDate dataManutencao;
    private StatusManutencao status;
}
