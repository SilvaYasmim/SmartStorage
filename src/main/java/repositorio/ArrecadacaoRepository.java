package com.yasmim.smartstorage.repository;

import com.yasmim.smartstorage.model.Arrecadacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrecadacaoRepository extends JpaRepository<Arrecadacao, Long> {
}