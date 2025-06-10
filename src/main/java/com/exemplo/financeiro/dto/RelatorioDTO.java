// src/main/java/com/exemplo/financeiro/dto/RelatorioDTO.java
package com.exemplo.financeiro.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioDTO {
    private List<ItemValoresDTO> receitas;
    private List<ItemValoresDTO> despesas;
}