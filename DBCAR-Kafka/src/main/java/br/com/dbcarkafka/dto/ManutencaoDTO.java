package br.com.dbcarkafka.dto;

import br.com.dbcarkafka.enums.StatusManutencao;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManutencaoDTO {

    private Integer idManutencao;
    private String placaCarro;
    private String servico;
    private Double valorTotal;
    private LocalDateTime dataManutencao;
    private StatusManutencao status;
}
