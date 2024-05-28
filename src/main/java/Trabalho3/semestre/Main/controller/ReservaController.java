package Trabalho3.semestre.Main.controller;

import Trabalho3.semestre.Main.DTO.ReservaDTO;
import Trabalho3.semestre.Main.entidades.Casa;
import Trabalho3.semestre.Main.entidades.Cliente;
import Trabalho3.semestre.Main.service.CasaService;
import Trabalho3.semestre.Main.service.ClienteService;
import Trabalho3.semestre.Main.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    private final ReservaService reservaService;
    private final ClienteService clienteService;
    private final CasaService casaService;

    @Autowired
    public ReservaController(ReservaService reservaService, ClienteService clienteService, CasaService casaService) {
        this.reservaService = reservaService;
        this.clienteService = clienteService;
        this.casaService = casaService;
    }

    @PostMapping("/criar")
    public ResponseEntity<ReservaDTO> criarReserva(@RequestBody ReservaDTO reservaDTO) {
        Cliente cliente = clienteService.buscarCliente(reservaDTO.getClienteId());
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        Long casaId = casaService.buscarCasa(reservaDTO.getCasaId()).getId(); // Alterado para receber o ID da casa
        if (casaId == null) {
            return ResponseEntity.notFound().build();
        }

        reservaDTO.setClienteId(cliente.getId()); // Alterar para setar apenas o ID do cliente
        reservaDTO.setCasaId(casaId); // Alterar para setar apenas o ID da casa

        ReservaDTO reservaSalvaDTO = reservaService.salvarReserva(reservaDTO);

        return ResponseEntity.ok(reservaSalvaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> buscarReservaPorId(@PathVariable Long id) {
        ReservaDTO reservaDTO = reservaService.buscarReservaPorId(id);
        if (reservaDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> atualizarReserva(@PathVariable Long id, @RequestBody ReservaDTO reservaDTO) {
        ReservaDTO reservaAtualizadaDTO = reservaService.atualizarReserva(id, reservaDTO);
        if (reservaAtualizadaDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservaAtualizadaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirReserva(@PathVariable Long id) {
        boolean excluida = reservaService.excluirReserva(id);
        if (!excluida) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
