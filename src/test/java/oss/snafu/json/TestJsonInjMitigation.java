package oss.snafu.json;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

public class TestJsonInjMitigation {

	@Test
	public void testJsonInjectionMitigation() throws Exception {
		final JsonInjectionMitigationFilter filter = new JsonInjectionMitigationFilter();

		final ServletRequest request = Mockito.mock(ServletRequest.class);
		final ServletResponse response = Mockito.mock(ServletResponse.class);
		final FilterChain chain = Mockito.mock(FilterChain.class);

		filter.doFilter(request, response, chain);

	}
}
