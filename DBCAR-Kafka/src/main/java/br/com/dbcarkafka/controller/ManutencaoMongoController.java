package br.com.dbcarkafka.controller;


import br.com.dbcarkafka.config.Response;
import br.com.dbcarkafka.dto.ManutencaoMongoDTO;
import br.com.dbcarkafka.exception.RegraDeNegocioException;
import br.com.dbcarkafka.service.ManutencaoMongoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/manutencao")
@Validated
@RequiredArgsConstructor
public class ManutencaoMongoController {
    private final ManutencaoMongoService manutencaoMongoService;
    
    @Operation(summary = "Listar todos as manutenções", description = "Realizará a listagem de todos as manutenções dos carros da DBCAR.")
    @Response
    @GetMapping("/listar")
    public ResponseEntity<List<ManutencaoMongoDTO>> listManutencao() throws RegraDeNegocioException {
        return new ResponseEntity<>(manutencaoMongoService.listManutencao(), HttpStatus.OK);
    }

    @Operation(summary = "Listar manutenções pela data e seus valores.",
            description = "Realizará a listagem das manutenções abaixo da data informada e seus valores.")
    @Response
    @GetMapping("/listar-manutencoes-data")
    public ResponseEntity<List<ManutencaoMongoDTO>> listManutencaoData(@RequestParam("data_manutencao") LocalDate dataManutencao,
                                                                          @RequestParam("valorTotal") Double valorTotal) throws RegraDeNegocioException {
        return new ResponseEntity<>(manutencaoMongoService.listDataManutencaoEValor(dataManutencao, valorTotal), HttpStatus.OK);
    }
}
