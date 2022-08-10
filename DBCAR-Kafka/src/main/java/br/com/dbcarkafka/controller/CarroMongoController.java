package br.com.dbcarkafka.controller;

import br.com.dbcarkafka.config.Response;
import br.com.dbcarkafka.dto.CarroMongoDTO;
import br.com.dbcarkafka.exception.RegraDeNegocioException;
import br.com.dbcarkafka.service.CarroMongoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/carromongo")
@RequiredArgsConstructor
public class CarroMongoController {

    private final CarroMongoService carroMongoService;

    @Operation(summary = "Listar carros", description = "Realizará a listagem de todos os carros da DBCAR.")
    @Response
    @GetMapping("/listar")
    public ResponseEntity<List<CarroMongoDTO>> list() throws RegraDeNegocioException {
        return new ResponseEntity<>(carroMongoService.carroMongoDTOList(), HttpStatus.OK);
    }

    @Operation(summary = "Listar carros pelo range de datas de fabricação.",
            description = "Realizará a listagem dos carros com range das datas informadas.")
    @Response
    @GetMapping("/listar-carros-data")
    public ResponseEntity<List<CarroMongoDTO>> listCarroMongoDataRange(@RequestParam("de") LocalDate de,
                                                                  @RequestParam("ate") LocalDate ate) throws RegraDeNegocioException {
        return new ResponseEntity<>(carroMongoService.listCarroMongoPorFabricacao(de, ate), HttpStatus.OK);
    }
}
