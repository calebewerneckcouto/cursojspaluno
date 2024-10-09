package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ModelLogin;

@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"})
public class ServletLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletLogin() {
        // Construtor padrão
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém login, senha e URL de redirecionamento dos parâmetros da requisição
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String url = request.getParameter("url");

        // Validação dos parâmetros de login e senha
        if (isNotEmpty(login) && isNotEmpty(senha)) {
            ModelLogin modelLogin = new ModelLogin();
            modelLogin.setLogin(login);
            modelLogin.setSenha(senha);

            // Autenticação simples (pode ser adaptada para verificação em banco de dados)
            if ("admin".equalsIgnoreCase(modelLogin.getLogin()) && "admin".equalsIgnoreCase(modelLogin.getSenha())) {
                // Armazena o usuário na sessão
                request.getSession().setAttribute("usuario", modelLogin.getLogin());

                // Define a URL de redirecionamento, caso não seja fornecida
                if (url == null || "null".equals(url)) {
                    url = "principal/principal.jsp";
                }

                // Redireciona para a URL desejada
                RequestDispatcher redirecionar = request.getRequestDispatcher(url);
                redirecionar.forward(request, response);
            } else {
                // Autenticação falhou, redireciona para a página de login com mensagem de erro
                redirecionarComErro(request, response, "Informe login e senha corretamente.");
            }
        } else {
            // Parâmetros de login ou senha estão vazios, redireciona para a página de login com mensagem de erro
            redirecionarComErro(request, response, "Login ou senha inválidos.");
        }
    }

    // Método auxiliar para verificar se uma string não está vazia
    private boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    // Método auxiliar para redirecionar com mensagem de erro
    private void redirecionarComErro(HttpServletRequest request, HttpServletResponse response, String mensagem) throws ServletException, IOException {
        RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
        request.setAttribute("msg", mensagem);
        redirecionar.forward(request, response);
    }
}
