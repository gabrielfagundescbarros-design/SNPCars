package com.snpcars.SNPCars.service;

import com.snpcars.SNPCars.model.Vendedor;
import com.snpcars.SNPCars.repository.VendedorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendedorService {

    @Autowired
    private VendedorDAO vendedorDAO;

    /**
     * READ: Lista todos os vendedores.
     */
    public List<Vendedor> listarVendedores() {
        return vendedorDAO.listar();
    }
    
    /**
     * READ: Busca um vendedor pelo seu ID.
     */
    public Vendedor buscarVendedorPorId(int id) {
        // Se houver lógica de negócios para tratar vendedores não encontrados, vai aqui.
        return vendedorDAO.buscarPorId(id);
    }
    
    /**
     * CREATE: Insere um novo vendedor.
     */
    public void inserirVendedor(Vendedor vendedor) {
        // Exemplo de regra de negócio: Garante que o nome não é nulo antes de inserir.
        if (vendedor.getNome() == null || vendedor.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do vendedor não pode ser vazio.");
        }
        vendedorDAO.inserir(vendedor);
    }
    
    /**
     * UPDATE: Atualiza os dados de um vendedor existente.
     */
    public void atualizarVendedor(Vendedor vendedor) {
        // Exemplo de regra de negócio: Verifica se o ID é válido para atualização.
        if (vendedor.getId() <= 0) {
            throw new IllegalArgumentException("ID de vendedor inválido para atualização.");
        }
        vendedorDAO.atualizar(vendedor);
    }
    
    /**
     * DELETE: Deleta um vendedor pelo seu ID.
     */
    public void deletarVendedor(int id) {
        // Antes de deletar, poderia haver uma verificação para saber 
        // se este vendedor não tem mais carros ativos (para integridade).
        vendedorDAO.deletar(id);
    }
}