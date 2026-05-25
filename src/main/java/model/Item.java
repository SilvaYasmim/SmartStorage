package com.yasmim.smartstorage.model;

import jakarta.persistence.*;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 255)
    private String descricao;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(length = 100)
    private String categoria;

    @Column(name = "foto_path", length = 500)
    private String fotoPath;

    @ManyToOne
    @JoinColumn(name = "recipiente_id", nullable = false)
    private Recipiente recipiente;

    // ─── Construtores ───────────────────────────────────
    public Item() {}

    public Item(String nome, String descricao, Integer quantidade, String categoria, Recipiente recipiente) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.recipiente = recipiente;
    }

    // ─── Getters e Setters ──────────────────────────────
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getFotoPath() { return fotoPath; }
    public void setFotoPath(String fotoPath) { this.fotoPath = fotoPath; }

    public Recipiente getRecipiente() { return recipiente; }
    public void setRecipiente(Recipiente recipiente) { this.recipiente = recipiente; }
}