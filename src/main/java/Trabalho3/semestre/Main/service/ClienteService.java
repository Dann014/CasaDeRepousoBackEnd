package Trabalho3.semestre.Main.service;

import Trabalho3.semestre.Main.DTO.ClienteDTO;
import Trabalho3.semestre.Main.entidades.Cliente;
import Trabalho3.semestre.Main.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente buscarCliente(Long id) {
        return clienteRepository.getById(id);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            Cliente clienteExistente = optionalCliente.get();
            clienteExistente.setNome(clienteAtualizado.getNome());
            clienteExistente.setIdade(clienteAtualizado.getIdade());
            clienteExistente.setSexo(clienteAtualizado.getSexo());
            clienteExistente.setEmail(clienteAtualizado.getEmail());
            clienteExistente.setTelefone(clienteAtualizado.getTelefone());
            clienteExistente.setEndereco(clienteAtualizado.getEndereco());
            clienteExistente.setLogin(clienteAtualizado.getLogin());
            clienteExistente.setSenha(clienteAtualizado.getSenha());

            return clienteRepository.save(clienteExistente);
        } else {
            return null;
        }
    }

    public Cliente converterParaCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setIdade(clienteDTO.getIdade());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEndereco(clienteDTO.getEndereco());
        return cliente;
    }

    public Long converterParaDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setIdade(cliente.getIdade());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setTelefone(cliente.getTelefone());
        clienteDTO.setEndereco(cliente.getEndereco());
        return clienteDTO.getId();
    }
}
