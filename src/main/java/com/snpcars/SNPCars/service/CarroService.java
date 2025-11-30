package com.snpcars.SNPCars.service;

import com.snpcars.SNPCars.model.Carro;
import com.snpcars.SNPCars.repository.CarroDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroDAO carroDAO;

    /**
     * READ: Lista todos os carros (Catálogo).
     */
    public List<Carro> listarCarros() {
        return carroDAO.listar();
    }
    
    /**
     * READ: Busca um carro pelo seu ID.
     */
    public Carro buscarCarroPorId(int id) {
        return carroDAO.buscarPorId(id);
    }
    
    /**
     * CREATE: Insere um novo carro.
     */
    public void inserirCarro(Carro carro) {
        // Exemplo de regra de negócio: Preço deve ser positivo.
        if (carro.getPreco() == null || carro.getPreco().doubleValue() <= 0) {
             throw new IllegalArgumentException("O preço do carro deve ser positivo.");
        }
        carroDAO.inserir(carro);
    }
    
    /**
     * UPDATE: Atualiza os dados de um carro existente.
     */
    public void atualizarCarro(Carro carro) {
        // Se houver uma auditoria de mudanças, seria tratada aqui.
        carroDAO.atualizar(carro);
    }
    
    /**
     * DELETE: Deleta um carro pelo seu ID.
     */
    public void deletarCarro(int id) {
        carroDAO.deletar(id);
    }
}