package org.jvnet.hudson.plugins.cors;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Add CORS header to resonse.
 * 
 * @author Jochen Hinrichsen
 */
public class JenkinsCorsFilter implements Filter {

    private static final String CORS_ORIGIN = System.getProperty("cors.origin", "*");
    private static final String CORS_METHODS = System.getProperty("cors.methods", "GET, POST, PUT, DELETE");
    private static final String CORS_HEADERS = System.getProperty("cors.headers", "Authorization");

    /** {@inheritDoc} */
    public void init(FilterConfig filterConfig) throws ServletException {}

    /** {@inheritDoc} */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (response instanceof HttpServletResponse) {
            final HttpServletResponse resp = (HttpServletResponse) response;
            resp.addHeader("Access-Control-Allow-Origin", CORS_ORIGIN);
            resp.addHeader("Access-Control-Allow-Methods", CORS_METHODS);
            resp.addHeader("Access-Control-Allow-Headers", CORS_HEADERS);
        }
        chain.doFilter(request, response);
    }

    /** {@inheritDoc} */
    public void destroy() {}
}