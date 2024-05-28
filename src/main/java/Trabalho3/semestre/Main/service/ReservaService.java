package Trabalho3.semestre.Main.service;

import Trabalho3.semestre.Main.DTO.ReservaDTO;
import Trabalho3.semestre.Main.entidades.Casa;
import Trabalho3.semestre.Main.entidades.Cliente;
import Trabalho3.semestre.Main.entidades.Reserva;
import Trabalho3.semestre.Main.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final ClienteService clienteService;
    private final CasaService casaService;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, ClienteService clienteService, CasaService casaService) {
        this.reservaRepository = reservaRepository;
        this.clienteService = clienteService;
        this.casaService = casaService;
    }

    public ReservaDTO salvarReserva(ReservaDTO reservaDTO) {
        Reserva reserva = new Reserva();
        reserva.setDataEntrada(reservaDTO.getDataEntrada());
        reserva.setDataSaida(reservaDTO.getDataSaida());
        reserva.setDisponivel(reservaDTO.getDisponivel());
        reserva.setStatus(reservaDTO.getStatus());

        Cliente cliente = clienteService.buscarCliente(reservaDTO.getClienteId());
        reserva.setCliente(cliente);

        Casa casa = casaService.buscarCasa(reservaDTO.getCasaId());
        reserva.setCasa(casa);

        Reserva reservaSalva = reservaRepository.save(reserva);

        ReservaDTO reservaSalvaDTO = new ReservaDTO();
        reservaSalvaDTO.setId(reservaSalva.getId());
        reservaSalvaDTO.setDataEntrada(reservaSalva.getDataEntrada());
        reservaSalvaDTO.setDataSaida(reservaSalva.getDataSaida());
        reservaSalvaDTO.setDisponivel(reservaSalva.getDisponivel());
        reservaSalvaDTO.setStatus(reservaSalva.getStatus());
        reservaSalvaDTO.setClienteId(reservaSalva.getCliente().getId());
        reservaSalvaDTO.setCasaId(reservaSalva.getCasa().getId());

        return reservaSalvaDTO;
    }

    public ReservaDTO buscarReservaPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id).orElse(null);
        if (reserva == null) {
            return null;
        }

        ReservaDTO reservaDTO = new ReservaDTO();
        reservaDTO.setId(reserva.getId());
        reservaDTO.setDataEntrada(reserva.getDataEntrada());
        reservaDTO.setDataSaida(reserva.getDataSaida());
        reservaDTO.setDisponivel(reserva.getDisponivel());
        reservaDTO.setStatus(reserva.getStatus());
        reservaDTO.setClienteId(reserva.getCliente().getId());
        reservaDTO.setCasaId(reserva.getCasa().getId());
        reservaDTO.setClienteId(clienteService.converterParaDTO(reserva.getCliente()));
        reservaDTO.setCasaId(casaService.converterParaDTO(reserva.getCasa()).getId());

        return reservaDTO;


    }

    public ReservaDTO atualizarReserva(Long id, ReservaDTO reservaDTO) {
        Optional<Reserva> optionalReserva = reservaRepository.findById(id);
        if (!optionalReserva.isPresent()) {
            return null;
        }

        Reserva reserva = optionalReserva.get();
        reserva.setDataEntrada(reservaDTO.getDataEntrada());
        reserva.setDataSaida(reservaDTO.getDataSaida());
        reserva.setDisponivel(reservaDTO.getDisponivel());
        reserva.setStatus(reservaDTO.getStatus());

        Cliente cliente = clienteService.buscarCliente(reservaDTO.getClienteId());
        if (cliente != null) {
            reserva.setCliente(cliente);
        }

        Casa casa = casaService.buscarCasa(reservaDTO.getCasaId());
        if (casa != null) {
            reserva.setCasa(casa);
        }

        Reserva reservaAtualizada = reservaRepository.save(reserva);

        ReservaDTO reservaAtualizadaDTO = new ReservaDTO();
        reservaAtualizadaDTO.setId(reservaAtualizada.getId());
        reservaAtualizadaDTO.setDataEntrada(reservaAtualizada.getDataEntrada());
        reservaAtualizadaDTO.setDataSaida(reservaAtualizada.getDataSaida());
        reservaAtualizadaDTO.setDisponivel(reservaAtualizada.getDisponivel());
        reservaAtualizadaDTO.setStatus(reservaAtualizada.getStatus());
        reservaAtualizadaDTO.setClienteId(reservaAtualizada.getCliente().getId());
        reservaAtualizadaDTO.setCasaId(reservaAtualizada.getCasa().getId());

        return reservaAtualizadaDTO;
    }

    public boolean excluirReserva(Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
