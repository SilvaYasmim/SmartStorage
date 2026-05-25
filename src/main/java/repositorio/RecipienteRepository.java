package com.yasmim.smartstorage.repository;

import com.yasmim.smartstorage.model.Recipiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipienteRepository extends JpaRepository<Recipiente, Long> {
    List<Recipiente> findByLocalizacaoId(Long localizacaoId);
    List<Recipiente> findByCategoria(String categoria);
    Optional<Recipiente> findByQrCode(String qrCode);
}