package br.com.dbcarkafka.dto;

import br.com.dbcarkafka.enums.StatusManutencao;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManutencaoDTO {
    @Schema(hidden = true)
    private String idManutencao;
    @Schema(hidden = true)
    private String placaCarro;
    
    private String servico;
    private Double valorTotal;
    private LocalDate dataManutencao;
    @Schema(hidden = true)
    private StatusManutencao status;
}
