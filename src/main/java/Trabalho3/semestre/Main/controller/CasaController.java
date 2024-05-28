package Trabalho3.semestre.Main.controller;

import Trabalho3.semestre.Main.DTO.CasaDTO;
import Trabalho3.semestre.Main.entidades.Casa;
import Trabalho3.semestre.Main.service.CasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/casa")
public class CasaController {

    private final CasaService casaService;

    @Autowired
    public CasaController(CasaService casaService) {
        this.casaService = casaService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Long> criarCasa(@RequestBody CasaDTO casaDTO) {
        Long novaCasaDTO = casaService.criarCasa(casaDTO);
        if (novaCasaDTO != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(novaCasaDTO);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Long> buscarCasa(@PathVariable Long id) {
        Casa casaDTO = casaService.buscarCasa(id);
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Long>> listarCasas() {
        List<Long> casasDTO = casaService.listarCasas();
        return ResponseEntity.ok(casasDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<Long> atualizarCasa(@PathVariable Long id, @RequestBody CasaDTO casaAtualizadaDTO) {
        Long casaDTOAtualizada = casaService.atualizarCasa(id, casaAtualizadaDTO);
        if (casaDTOAtualizada != null) {
            return ResponseEntity.ok(casaDTOAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluirCasa(@PathVariable Long id) {
        casaService.excluirCasa(id);
        return ResponseEntity.noContent().build();
    }
}
