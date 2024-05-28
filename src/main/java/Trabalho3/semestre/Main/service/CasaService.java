package Trabalho3.semestre.Main.service;

import Trabalho3.semestre.Main.DTO.CasaDTO;
import Trabalho3.semestre.Main.entidades.Casa;
import Trabalho3.semestre.Main.repository.CasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CasaService {

    private final CasaRepository casaRepository;

    @Autowired
    public CasaService(CasaRepository casaRepository) {
        this.casaRepository = casaRepository;
    }

    public Long criarCasa(CasaDTO casaDTO) {
        Casa casa = converterParaCasa(casaDTO);
        Casa casaSalva = casaRepository.save(casa);
        return converterParaDTO(casaSalva).getId();
    }

    public Casa buscarCasa(Long id) {
        Optional<Casa> optionalCasa = casaRepository.findById(id);
        return optionalCasa.orElse(null);
    }

    public List<Long> listarCasas() {
        List<Casa> casas = casaRepository.findAll();
        return converterParaDTOLista(casas);
    }

    public Long atualizarCasa(Long id, CasaDTO casaDTO) {
        Casa casaAtualizada = converterParaCasa(casaDTO);
        if (casaRepository.existsById(casaAtualizada.getId())) {
            Casa casaSalva = casaRepository.save(casaAtualizada);
            return converterParaDTO(casaSalva).getId();
        } else {
            return null;
        }
    }

    public void excluirCasa(Long id) {
        casaRepository.deleteById(id);
    }

    public Casa converterParaCasa(CasaDTO casaDTO) {
        Casa casa = new Casa();
        casa.setId(casaDTO.getId());
        casa.setNome(casaDTO.getNome());
        casa.setNumero(casaDTO.getNumero());
        casa.setTipo(casaDTO.getTipo());
        casa.setDescricao(casaDTO.getDescricao());
        casa.setPreco(casaDTO.getPreco());

        return casa;
    }
    CasaDTO converterParaDTO(Casa casa) {
        CasaDTO casaDTO = new CasaDTO();
        casaDTO.setId(casa.getId());
        casaDTO.setNome(casa.getNome());
        casaDTO.setNumero(casa.getNumero());
        casaDTO.setTipo(casa.getTipo());
        casaDTO.setDescricao(casa.getDescricao());
        casaDTO.setPreco(casa.getPreco());
        return casaDTO;
    }



    private List<Long> converterParaDTOLista(List<Casa> casas) {
        return casas.stream()
                .map(Casa::getId)
                .collect(Collectors.toList());
    }
}
