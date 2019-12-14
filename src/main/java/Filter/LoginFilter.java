package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.LoginBean;

public class LoginFilter implements Filter{
	
	private LoginBean loginBean;

	@Override
	public void init (FilterConfig filterConfig) throws ServletException {
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getRequestURL().toString();
		
		System.out.println(url);
		if (url.contains("menu") && loginBean.loginRecepcionista()
				== null) {
			res.sendRedirect(req.getServletContext().getContextPath()+"/login.xhtml");
		}
		else {
			chain.doFilter(request, response);
	}
	}
	
	@Override
	public void destroy() {
		
	}

}
