package com.snpcars.SNPCars.repository;

import com.snpcars.SNPCars.model.Carro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CarroDAO {

    @Autowired
    private JdbcTemplate jdbc;

    // RowMapper: Mapeia o ResultSet para o objeto Carro
    private RowMapper<Carro> carroRowMapper = new RowMapper<Carro>() {
        @Override
        public Carro mapRow(ResultSet rs, int rowNum) throws SQLException {
            Carro c = new Carro();
            c.setId(rs.getInt("id"));
            c.setMarca(rs.getString("marca"));
            c.setModelo(rs.getString("modelo"));
            c.setAno(rs.getInt("ano"));
            c.setQuilometragem(rs.getInt("quilometragem"));
            c.setCor(rs.getString("cor"));
            c.setCombustivel(rs.getString("combustivel"));
            c.setPreco(rs.getBigDecimal("preco"));
            c.setFotoUrl(rs.getString("foto_url"));
            c.setVendedorId(rs.getInt("vendedor_id"));
            
            // Campo extra do JOIN
            c.setNomeVendedor(rs.getString("nomeVendedor")); 
            
            return c;
        }
    };

    /**
     * READ: Lista todos os carros com o nome do vendedor responsável.
     */
    public List<Carro> listar() {
        String sql = "SELECT c.*, v.nome AS nomeVendedor FROM carro c JOIN vendedor v ON c.vendedor_id = v.id ORDER BY c.id DESC";
        return jdbc.query(sql, carroRowMapper);
    }
    
    /**
     * READ: Busca um carro pelo ID.
     */
    public Carro buscarPorId(int id) {
        // Usa a mesma consulta de listar, mas adiciona o filtro WHERE
        String sql = "SELECT c.*, v.nome AS nomeVendedor FROM carro c JOIN vendedor v ON c.vendedor_id = v.id WHERE c.id = ?";
        try {
            // queryForObject é usado quando se espera *apenas um* resultado.
            return jdbc.queryForObject(sql, carroRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            // Retorna null se não encontrar o carro.
            return null;
        }
    }

    /**
     * CREATE: Insere um novo carro no banco.
     */
    public void inserir(Carro carro) {
        String sql = "INSERT INTO carro (marca, modelo, ano, quilometragem, cor, combustivel, preco, foto_url, vendedor_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbc.update(sql, 
            carro.getMarca(), 
            carro.getModelo(), 
            carro.getAno(), 
            carro.getQuilometragem(), 
            carro.getCor(),
            carro.getCombustivel(), 
            carro.getPreco(), 
            carro.getFotoUrl(),
            carro.getVendedorId()
        );
    }
    
    /**
     * UPDATE: Atualiza um carro existente.
     */
    public void atualizar(Carro carro) {
        String sql = "UPDATE carro SET marca=?, modelo=?, ano=?, quilometragem=?, cor=?, combustivel=?, preco=?, foto_url=?, vendedor_id=? WHERE id=?";
        jdbc.update(sql, 
            carro.getMarca(), 
            carro.getModelo(), 
            carro.getAno(), 
            carro.getQuilometragem(), 
            carro.getCor(),
            carro.getCombustivel(), 
            carro.getPreco(), 
            carro.getFotoUrl(),
            carro.getVendedorId(),
            carro.getId() // O ID é o último parâmetro para a cláusula WHERE
        );
    }
    
    /**
     * DELETE: Deleta um carro pelo ID.
     */
    public void deletar(int id) {
        String sql = "DELETE FROM carro WHERE id=?";
        jdbc.update(sql, id);
    }
}