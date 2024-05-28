package Trabalho3.semestre.Main.controller;

import Trabalho3.semestre.Main.entidades.Cliente;
import Trabalho3.semestre.Main.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/criar")
    public Cliente salvar(@RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        clienteService.deletar(id);
    }

    @GetMapping("/{id}")
    public Cliente buscarCliente(@PathVariable Long id) {
        return clienteService.buscarCliente(id);
    }

    @GetMapping
    public List<Cliente> buscarTodosClientes() {
        return clienteService.listarClientes();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteService.buscarCliente(id);
        if (clienteExistente == null) {
            return ResponseEntity.notFound().build();
        }
        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setIdade(clienteAtualizado.getIdade());
        clienteExistente.setSexo(clienteAtualizado.getSexo());
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        clienteExistente.setTelefone(clienteAtualizado.getTelefone());
        clienteExistente.setEndereco(clienteAtualizado.getEndereco());
        clienteExistente.setLogin(clienteAtualizado.getLogin());
        clienteExistente.setSenha(clienteAtualizado.getSenha());

        clienteExistente = clienteService.salvar(clienteExistente);
        return ResponseEntity.ok(clienteExistente);
    }

}
