package oss.snafu.json;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet {@link Filter} intended to be used to mitigate JSON injection. <br>
 * Simply prefixes your json response with <code>
 * <pre>
 * )]}',\n
 * </pre>
 * </code> <br>
 * When using angular's $http client, the prefix is automatically stripped from the result.
 *
 * @see http ://haacked.com/archive/2008/11/20/anatomy-of-a-subtle-json-vulnerability.aspx/
 * @author ckatzorke
 *
 */
public class JsonInjectionMitigationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
	ServletException {
		final JsonInjectionMitigationResponseWrapper wrapper = new JsonInjectionMitigationResponseWrapper(
				(HttpServletResponse) response);
		chain.doFilter(request, wrapper);
	}

	@Override
	public void destroy() {
		//

	}

}
