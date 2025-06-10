package com.exemplo.financeiro.repository;

import com.exemplo.financeiro.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {}