// src/main/java/com/exemplo/financeiro/controller/RelatorioController.java
package com.exemplo.financeiro.controller;

import com.exemplo.financeiro.dto.ItemValoresDTO;
import com.exemplo.financeiro.dto.RelatorioDTO;
import com.exemplo.financeiro.entity.Item;
import com.exemplo.financeiro.entity.ValorMensal;
import com.exemplo.financeiro.repository.ValorMensalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/relatorio")
@CrossOrigin
public class RelatorioController {

    @Autowired
    private ValorMensalRepository valorMensalRepository;

    @GetMapping("/{ano}")
    public RelatorioDTO getRelatorio(@PathVariable int ano) {
        List<ValorMensal> valores = valorMensalRepository.findByAno(ano);

        // Mapa: itemId => array[12], para RECEITA e DESPESA separados
        Map<Long, List<BigDecimal>> mapaReceitas = new HashMap<>();
        Map<Long, List<BigDecimal>> mapaDespesas = new HashMap<>();
        Map<Long, String> nomesItens = new HashMap<>(); // para recuperar o nome do item

        for (ValorMensal vm : valores) {
            Long idItem = vm.getItem().getId();
            nomesItens.putIfAbsent(idItem, vm.getItem().getNome());

            int mesIndex = vm.getMes() - 1;
            if (vm.getItem().getTipo() == Item.Tipo.RECEITA) {
                mapaReceitas.putIfAbsent(idItem, initZeroList());
                List<BigDecimal> arr = mapaReceitas.get(idItem);
                arr.set(mesIndex, arr.get(mesIndex).add(vm.getValor()));
            } else if (vm.getItem().getTipo() == Item.Tipo.DESPESA) {
                mapaDespesas.putIfAbsent(idItem, initZeroList());
                List<BigDecimal> arr = mapaDespesas.get(idItem);
                arr.set(mesIndex, arr.get(mesIndex).add(vm.getValor()));
            }
        }

        List<ItemValoresDTO> listReceitas = convertMapToList(mapaReceitas, nomesItens);
        List<ItemValoresDTO> listDespesas = convertMapToList(mapaDespesas, nomesItens);

        return new RelatorioDTO(listReceitas, listDespesas);
    }

    private List<BigDecimal> initZeroList() {
        List<BigDecimal> arr = new ArrayList<>(12);
        for (int i = 0; i < 12; i++) {
            arr.add(BigDecimal.ZERO);
        }
        return arr;
    }

    private List<ItemValoresDTO> convertMapToList(Map<Long, List<BigDecimal>> mapa, Map<Long, String> nomesItens) {
        List<ItemValoresDTO> lista = new ArrayList<>();
        for (Map.Entry<Long, List<BigDecimal>> entry : mapa.entrySet()) {
            Long idItem = entry.getKey();
            String nome = nomesItens.getOrDefault(idItem, "Item " + idItem);
            List<BigDecimal> valores = entry.getValue();
            lista.add(new ItemValoresDTO(nome, valores));
        }
        return lista;
    }
}