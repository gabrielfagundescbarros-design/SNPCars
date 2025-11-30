package com.snpcars.SNPCars.model;

import java.math.BigDecimal;

public class Carro {
    private int id; // Pode continuar int
    private String marca;
    private String modelo;
    private Integer ano; // <--- CORRIGIDO: de int para Integer
    private Integer quilometragem; // <--- CORRIGIDO: de int para Integer
    private String cor;
    private String combustivel;
    private BigDecimal preco; // Tipo correto para moeda
    private String fotoUrl;
    
    // Chave Estrangeira
    private int vendedorId; // Pode continuar int

    // Atributo extra para exibição
    private String nomeVendedor;
    
    // Construtor Vazio
    public Carro() { }

    // Construtor Cheio (com todos os campos do banco)
    public Carro(int id, String marca, String modelo, Integer ano, Integer quilometragem, String cor, String combustivel, BigDecimal preco, String fotoUrl, int vendedorId) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.quilometragem = quilometragem;
        this.cor = cor;
        this.combustivel = combustivel;
        this.preco = preco;
        this.fotoUrl = fotoUrl;
        this.vendedorId = vendedorId;
    }

    // Construtor Sem ID (para INSERT)
    public Carro(String marca, String modelo, Integer ano, Integer quilometragem, String cor, String combustivel, BigDecimal preco, String fotoUrl, int vendedorId) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.quilometragem = quilometragem;
        this.cor = cor;
        this.combustivel = combustivel;
        this.preco = preco;
        this.fotoUrl = fotoUrl;
        this.vendedorId = vendedorId;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    
    // Getters e Setters para Integer
    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }
    public Integer getQuilometragem() { return quilometragem; }
    public void setQuilometragem(Integer quilometragem) { this.quilometragem = quilometragem; }
    
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public String getCombustivel() { return combustivel; }
    public void setCombustivel(String combustivel) { this.combustivel = combustivel; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public String getFotoUrl() { return fotoUrl; }
    public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; }
    public int getVendedorId() { return vendedorId; }
    public void setVendedorId(int vendedorId) { this.vendedorId = vendedorId; }

    public String getNomeVendedor() { return nomeVendedor; }
    public void setNomeVendedor(String nomeVendedor) { this.nomeVendedor = nomeVendedor; }
}