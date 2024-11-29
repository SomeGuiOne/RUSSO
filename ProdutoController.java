java
package controller;

import dao.ProdutoDAO;
import model.Produto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ProdutoController")
public class ProdutoController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ProdutoDAO produtoDAO;
        try {
            produtoDAO = new ProdutoDAO();
            switch (action) {
                case "incluir":
                    Produto novoProduto = new Produto();
                    novoProduto.setNome(request.getParameter("nome"));
                    novoProduto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                    novoProduto.setPreco(Double.parseDouble(request.getParameter("preco")));
                    produtoDAO.incluirProduto(novoProduto);
                    break;
                case "alterar":
                    Produto produtoAlterado = new Produto();
                    produtoAlterado.setId(Integer.parseInt(request.getParameter("id")));
                    produtoAlterado.setNome(request.getParameter("nome"));
                    produtoAlterado.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                    produtoAlterado.setPreco(Double.parseDouble(request.getParameter("preco")));
                    produtoDAO.alterarProduto(produtoAlterado);
                    break;
                case "excluir":
                    int id = Integer.parseInt(request.getParameter("id"));
                    produtoDAO.excluirProduto(id);
                    break;
            }
            response.sendRedirect("menu.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
