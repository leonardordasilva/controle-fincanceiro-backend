package com.exemplo.financeiro.controller;

import com.exemplo.financeiro.entity.ValorMensal;
import com.exemplo.financeiro.repository.ValorMensalRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/valores")
@CrossOrigin
public class ValorMensalController {

    private final ValorMensalRepository repo;

    public ValorMensalController(ValorMensalRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<ValorMensal> listar() {
        return repo.findAll();
    }

    @PostMapping
    public ValorMensal salvar(@RequestBody ValorMensal valor) {
        return repo.save(valor);
    }

    @PutMapping("/{id}")
    public ValorMensal atualizar(@PathVariable Long id, @RequestBody ValorMensal valor) {
        valor.setId(id);
        return repo.save(valor);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}