// src/main/java/com/exemplo/financeiro/dto/ItemValoresDTO.java
package com.exemplo.financeiro.dto;

import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemValoresDTO {
    private String nomeItem;
    private List<BigDecimal> valoresMensais;
}