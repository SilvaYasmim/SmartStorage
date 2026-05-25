package com.yasmim.smartstorage.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.yasmim.smartstorage.model.Recipiente;
import com.yasmim.smartstorage.repository.RecipienteRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class RecipienteService {

    private final RecipienteRepository repository;

    @Value("${app.upload.dir}")
    private String uploadDir;

    public RecipienteService(RecipienteRepository repository) {
        this.repository = repository;
    }

    public List<Recipiente> listarTodos() {
        return repository.findAll();
    }

    public List<Recipiente> listarPorLocalizacao(Long localizacaoId) {
        return repository.findByLocalizacaoId(localizacaoId);
    }

    public List<Recipiente> listarPorCategoria(String categoria) {
        return repository.findByCategoria(categoria);
    }

    public Recipiente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipiente não encontrado: " + id));
    }

    public Recipiente buscarPorQrCode(String qrCode) {
        return repository.findByQrCode(qrCode)
                .orElseThrow(() -> new RuntimeException("Recipiente não encontrado para QR: " + qrCode));
    }

    public Recipiente guardar(Recipiente recipiente) {
        if (recipiente.getId() == null) {
            // Novo recipiente — gerar QR Code único
            String qrCode = UUID.randomUUID().toString();
            recipiente.setQrCode(qrCode);
            recipiente.setDataCriacao(LocalDate.now());
            Recipiente salvo = repository.save(recipiente);
            gerarImagemQrCode(qrCode, salvo.getId());
            return salvo;
        }
        return repository.save(recipiente);
    }

    public void apagar(Long id) {
        repository.deleteById(id);
    }

    private void gerarImagemQrCode(String qrCode, Long id) {
        try {
            String url = "http://localhost:8080/publico/recipiente/" + qrCode;
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix matrix = writer.encode(url, BarcodeFormat.QR_CODE, 300, 300);

            Path dir = Paths.get(uploadDir, "qrcodes");
            Files.createDirectories(dir);
            Path path = dir.resolve("qr-" + id + ".png");
            MatrixToImageWriter.writeToPath(matrix, "PNG", path);
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Erro ao gerar QR Code", e);
        }
    }
}