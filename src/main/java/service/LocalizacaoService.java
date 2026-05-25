package com.yasmim.smartstorage.service;

import com.yasmim.smartstorage.model.Localizacao;
import com.yasmim.smartstorage.repository.LocalizacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalizacaoService {

    private final LocalizacaoRepository repository;

    public LocalizacaoService(LocalizacaoRepository repository) {
        this.repository = repository;
    }

    public List<Localizacao> listarTodas() {
        return repository.findAll();
    }

    public List<Localizacao> listarPorArrecadacao(Long arrecadacaoId) {
        return repository.findByArrecadacaoId(arrecadacaoId);
    }

    public Localizacao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Localização não encontrada: " + id));
    }

    public Localizacao guardar(Localizacao localizacao) {
        return repository.save(localizacao);
    }

    public void apagar(Long id) {
        repository.deleteById(id);
    }
}