java
package dao;

import model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private Connection conn;

    public ProdutoDAO() throws SQLException {
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TechParts", "root", "password");
    }

    public void incluirProduto(Produto produto) throws SQLException {
        String sql = "INSERT INTO Produto (nome, quantidade, preco) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, produto.getNome());
        stmt.setInt(2, produto.getQuantidade());
        stmt.setDouble(3, produto.getPreco());
        stmt.executeUpdate();
    }

    public List<Produto> consultarTodos() throws SQLException {
        String sql = "SELECT * FROM Produto";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Produto> produtos = new ArrayList<>();
        while (rs.next()) {
            Produto p = new Produto();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setQuantidade(rs.getInt("quantidade"));
            p.setPreco(rs.getDouble("preco"));
            produtos.add(p);
        }
        return produtos;
    }

    public void alterarProduto(Produto produto) throws SQLException {
        String sql = "UPDATE Produto SET nome = ?, quantidade = ?, preco = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, produto.getNome());
        stmt.setInt(2, produto.getQuantidade());
        stmt.setDouble(3, produto.getPreco());
        stmt.setInt(4, produto.getId());
        stmt.executeUpdate();
    }

    public void excluirProduto(int id) throws SQLException {
        String sql = "DELETE FROM Produto WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
