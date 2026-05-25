package com.yasmim.smartstorage.service;

import com.yasmim.smartstorage.model.Item;
import com.yasmim.smartstorage.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ItemService {

    private final ItemRepository repository;

    @Value("${app.upload.dir}")
    private String uploadDir;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> listarTodos() {
        return repository.findAll();
    }

    public List<Item> listarPorRecipiente(Long recipienteId) {
        return repository.findByRecipienteId(recipienteId);
    }

    public List<Item> listarPorCategoria(String categoria) {
        return repository.findByCategoria(categoria);
    }

    public List<Item> pesquisarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public Item buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado: " + id));
    }

    public Item guardar(Item item, MultipartFile foto) {
        if (foto != null && !foto.isEmpty()) {
            String nomeFicheiro = UUID.randomUUID() + "_" + foto.getOriginalFilename();
            try {
                Path dir = Paths.get(uploadDir, "fotos");
                Files.createDirectories(dir);
                Path destino = dir.resolve(nomeFicheiro);
                foto.transferTo(destino);
                item.setFotoPath("fotos/" + nomeFicheiro);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao guardar foto", e);
            }
        }
        return repository.save(item);
    }

    public void apagar(Long id) {
        repository.deleteById(id);
    }
}