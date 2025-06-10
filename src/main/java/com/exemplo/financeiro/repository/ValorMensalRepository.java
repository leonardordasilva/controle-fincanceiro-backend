// src/main/java/com/exemplo/financeiro/repository/ValorMensalRepository.java
package com.exemplo.financeiro.repository;

import com.exemplo.financeiro.entity.ValorMensal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValorMensalRepository extends JpaRepository<ValorMensal, Long> {
    List<ValorMensal> findByAno(int ano);
}
