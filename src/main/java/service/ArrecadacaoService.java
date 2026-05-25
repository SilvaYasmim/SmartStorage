package com.yasmim.smartstorage.service;

import com.yasmim.smartstorage.model.Arrecadacao;
import com.yasmim.smartstorage.repository.ArrecadacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArrecadacaoService {

    private final ArrecadacaoRepository repository;

    public ArrecadacaoService(ArrecadacaoRepository repository) {
        this.repository = repository;
    }

    public List<Arrecadacao> listarTodas() {
        return repository.findAll();
    }

    public Arrecadacao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arrecadação não encontrada: " + id));
    }

    public Arrecadacao guardar(Arrecadacao arrecadacao) {
        return repository.save(arrecadacao);
    }

    public void apagar(Long id) {
        repository.deleteById(id);
    }
}