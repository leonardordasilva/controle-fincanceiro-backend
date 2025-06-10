package com.exemplo.financeiro.controller;

import com.exemplo.financeiro.entity.Item;
import com.exemplo.financeiro.repository.ItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens")
@CrossOrigin
public class ItemController {

    private final ItemRepository repo;

    public ItemController(ItemRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Item> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Item salvar(@RequestBody Item item) {
        return repo.save(item);
    }

    @PutMapping("/{id}")
    public Item atualizar(@PathVariable Long id, @RequestBody Item item) {
        item.setId(id);
        return repo.save(item);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}