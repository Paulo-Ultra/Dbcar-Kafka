package br.com.dbcarkafka.controller;

import br.com.dbcarkafka.dto.ManutencaoDTO;
import br.com.dbcarkafka.enums.StatusManutencao;
import br.com.dbcarkafka.service.ConsumidorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manutencoes")
@RequiredArgsConstructor
public class ManutencaoController {

    private final ConsumidorService consumidorService;
    @GetMapping
    public ResponseEntity<List<ManutencaoDTO>> listManutencaoConcluida (@RequestParam StatusManutencao status){
        return new ResponseEntity<>(consumidorService.findByStatus(status), HttpStatus.OK);
    }
}
