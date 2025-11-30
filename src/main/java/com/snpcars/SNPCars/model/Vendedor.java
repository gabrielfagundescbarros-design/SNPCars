package com.snpcars.SNPCars.model;

import java.util.List;

public class Vendedor {
    private int id; // Corresponde à coluna 'id' (SERIAL PRIMARY KEY)
    private String nome; // Corresponde à coluna 'nome'
    private String email; // Corresponde à coluna 'email'
    private String telefone; // Corresponde à coluna 'telefone'
    private String cargo; // Corresponde à coluna 'cargo' (Ex: Gerente de Vendas)
    private String especialidade; // Corresponde à coluna 'especialidade'
    private String fotoUrl; // Corresponde à coluna 'foto_url' (Usada na View Vendedores)

    // Opcional, mas útil: Lista de carros que este vendedor é responsável
    // Não será mapeado diretamente pelo JDBC, mas pode ser preenchido pelo Service
    private List<Carro> carros; 
    
    // Construtor Vazio: Essencial para o Spring e Thymeleaf (@ModelAttribute)
    public Vendedor() { }

    // Construtor Cheio: Útil para carregar dados do banco de dados (SELECT)
    public Vendedor(int id, String nome, String email, String telefone, String cargo, String especialidade, String fotoUrl) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cargo = cargo;
        this.especialidade = especialidade;
        this.fotoUrl = fotoUrl;
    }

    // Construtor Sem ID: Útil para inserção de novos registros (INSERT)
    public Vendedor(String nome, String email, String telefone, String cargo, String especialidade, String fotoUrl) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cargo = cargo;
        this.especialidade = especialidade;
        this.fotoUrl = fotoUrl;
    }
    
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
    public String getFotoUrl() { return fotoUrl; }
    public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; }
    
    // Getters e Setters para a lista de carros (Se for utilizar)
    public List<Carro> getCarros() { return carros; }
    public void setCarros(List<Carro> carros) { this.carros = carros; }
}