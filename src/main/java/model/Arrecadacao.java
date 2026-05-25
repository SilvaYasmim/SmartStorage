package com.yasmim.smartstorage.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "arrecadacao")
public class Arrecadacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 255)
    private String descricao;

    @OneToMany(mappedBy = "arrecadacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Localizacao> localizacoes;

    // ─── Construtores ───────────────────────────────────
    public Arrecadacao() {}

    public Arrecadacao(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    // ─── Getters e Setters ──────────────────────────────
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public List<Localizacao> getLocalizacoes() { return localizacoes; }
    public void setLocalizacoes(List<Localizacao> localizacoes) { this.localizacoes = localizacoes; }
}