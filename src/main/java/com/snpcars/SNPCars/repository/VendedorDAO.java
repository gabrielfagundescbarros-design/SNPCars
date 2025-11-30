package com.snpcars.SNPCars.repository;

import com.snpcars.SNPCars.model.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VendedorDAO {

    @Autowired
    private JdbcTemplate jdbc;

    // RowMapper: Responsável por mapear cada linha do ResultSet (SQL) para um objeto Vendedor (Java)
    private RowMapper<Vendedor> vendedorRowMapper = new RowMapper<Vendedor>() {
        @Override
        public Vendedor mapRow(ResultSet rs, int rowNum) throws SQLException {
            Vendedor v = new Vendedor();
            v.setId(rs.getInt("id"));
            v.setNome(rs.getString("nome"));
            v.setEmail(rs.getString("email"));
            v.setTelefone(rs.getString("telefone"));
            v.setCargo(rs.getString("cargo"));
            v.setEspecialidade(rs.getString("especialidade"));
            v.setFotoUrl(rs.getString("foto_url"));
            return v;
        }
    };

    /**
     * READ: Lista todos os vendedores do banco de dados.
     */
    public List<Vendedor> listar() {
        String sql = "SELECT id, nome, email, telefone, cargo, especialidade, foto_url FROM vendedor ORDER BY nome";
        return jdbc.query(sql, vendedorRowMapper);
    }
    
    /**
     * READ: Busca um vendedor pelo ID.
     */
    public Vendedor buscarPorId(int id) {
        String sql = "SELECT id, nome, email, telefone, cargo, especialidade, foto_url FROM vendedor WHERE id = ?";
        try {
            return jdbc.queryForObject(sql, vendedorRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            // Retorna null se não encontrar o vendedor.
            return null;
        }
    }
    
    /**
     * CREATE: Insere um novo vendedor no banco.
     */
    public void inserir(Vendedor vendedor) {
        String sql = "INSERT INTO vendedor (nome, email, telefone, cargo, especialidade, foto_url) VALUES (?, ?, ?, ?, ?, ?)";
        jdbc.update(sql, 
            vendedor.getNome(), 
            vendedor.getEmail(), 
            vendedor.getTelefone(), 
            vendedor.getCargo(),
            vendedor.getEspecialidade(),
            vendedor.getFotoUrl()
        );
    }
    
    /**
     * UPDATE: Atualiza um vendedor existente.
     */
    public void atualizar(Vendedor vendedor) {
        String sql = "UPDATE vendedor SET nome=?, email=?, telefone=?, cargo=?, especialidade=?, foto_url=? WHERE id=?";
        jdbc.update(sql, 
            vendedor.getNome(), 
            vendedor.getEmail(), 
            vendedor.getTelefone(), 
            vendedor.getCargo(),
            vendedor.getEspecialidade(),
            vendedor.getFotoUrl(),
            vendedor.getId() // O ID é o último parâmetro para a cláusula WHERE
        );
    }
    
    /**
     * DELETE: Deleta um vendedor pelo ID.
     */
    public void deletar(int id) {
        String sql = "DELETE FROM vendedor WHERE id=?";
        jdbc.update(sql, id);
    }
}