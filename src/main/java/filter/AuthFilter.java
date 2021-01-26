package filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 20.01.2021
 * email roman9628@gmail.com
 * The class filter check all request url if url different from auth servlet,
 * when check(get user attribute) user was authenticated if not redirect to login page.
 */
public class AuthFilter implements Filter {
    /**
     * The method override init. Make some action before filter call doFilter.
     * @param filterConfig FilterConfig.
     * @throws javax.servlet.ServletException Exception.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * The filter check request url equals "auth.do", "reg.do", "login.jsp"
     * then the filter passes through itself.
     * Otherwise check was user authenticated in app.
     * @param sreq ServletRequest.
     * @param sresp ServletResponse.
     * @param chain FilterChain.
     * @throws java.io.IOException Exception.
     * @throws javax.servlet.ServletException Exception.
     */
    @Override
    public void doFilter(ServletRequest sreq, ServletResponse sresp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) sreq;
        HttpServletResponse resp = (HttpServletResponse) sresp;
        String uri = req.getRequestURI();
        if (uri.endsWith("auth.do")
                || uri.endsWith("reg.do")
                || uri.endsWith("login.jsp"))
        {
            chain.doFilter(sreq, sresp);
            return;
        }
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        chain.doFilter(sreq, sresp);
    }

    /**
     * The method clean resources.
     * Method call when web container taken out of service.
     */
    @Override
    public void destroy() {
    }
}