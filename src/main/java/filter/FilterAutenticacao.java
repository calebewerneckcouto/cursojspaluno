package filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/principal/*" })
public class FilterAutenticacao extends HttpFilter {

	private static final long serialVersionUID = 1L;

	public FilterAutenticacao() {
		// Construtor vazio
	}

	@Override
	public void destroy() {
		// Método chamado quando o filtro é destruído
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// Faz o casting para HttpServletRequest para usar métodos HTTP
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(); // Obtém a sessão atual, se houver

		// Obtém o caminho solicitado
		String urlParaAutenticar = req.getServletPath();

		// Verifica se o usuário está logado (se existe o atributo "usuario" na sessão)
		String usuarioLogado = (String) session.getAttribute("usuario");

		// Se o usuário não estiver logado e a URL não for a de login, redireciona para a página de login
		if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {
			// Redireciona para a página de login, enviando o caminho da URL que o usuário tentou acessar
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
			request.setAttribute("msg", "Por favor, realize o login!");
			redireciona.forward(request, response);
			return; // Interrompe o fluxo, pois o redirecionamento já ocorreu
		} else {
			// Se o usuário estiver logado ou a URL for a de login, prossegue com a requisição
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// Lógica de inicialização do filtro, se necessário
	}
}
