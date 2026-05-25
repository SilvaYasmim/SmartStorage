package com.yasmim.smartstorage.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "localizacao")
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 255)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "arrecadacao_id", nullable = false)
    private Arrecadacao arrecadacao;

    @OneToMany(mappedBy = "localizacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recipiente> recipientes;

    // ─── Construtores ───────────────────────────────────
    public Localizacao() {}

    public Localizacao(String nome, String descricao, Arrecadacao arrecadacao) {
        this.nome = nome;
        this.descricao = descricao;
        this.arrecadacao = arrecadacao;
    }

    // ─── Getters e Setters ──────────────────────────────
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Arrecadacao getArrecadacao() { return arrecadacao; }
    public void setArrecadacao(Arrecadacao arrecadacao) { this.arrecadacao = arrecadacao; }

    public List<Recipiente> getRecipientes() { return recipientes; }
    public void setRecipientes(List<Recipiente> recipientes) { this.recipientes = recipientes; }
}