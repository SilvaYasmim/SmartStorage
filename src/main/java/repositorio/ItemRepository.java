package com.yasmim.smartstorage.repository;

import com.yasmim.smartstorage.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByRecipienteId(Long recipienteId);
    List<Item> findByCategoria(String categoria);
    List<Item> findByNomeContainingIgnoreCase(String nome);
}