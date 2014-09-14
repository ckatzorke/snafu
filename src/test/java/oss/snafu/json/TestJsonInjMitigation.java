package oss.snafu.json;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class TestJsonInjMitigation extends WebMvcConfigurerAdapter {

	@Test
	public void testJsonInjectionMitigationInterceptor() throws Exception {
		final MockMvc mvc = MockMvcBuilders.standaloneSetup(new SimpleController())
				.addMappedInterceptors(new String[] { "/mav", "/string" }, new JsonInjectionMitigationHandlerInterceptor())
				.build();
		mvc.perform(MockMvcRequestBuilders.get("/string")).andExpect(
				MockMvcResultMatchers.content().string(JsonInjectionMitigationHandlerInterceptor.PRE + "123"));
		mvc.perform(MockMvcRequestBuilders.get("/mav")).andExpect(MockMvcResultMatchers.view().name("index"));
	}

	@Test
	public void testJsonInjectionMitigationFilter() throws Exception {
		final MockMvc mvc = MockMvcBuilders.standaloneSetup(new SimpleController())
				.addFilter(new JsonInjectionMitigationFilter(), "/mav", "/string").build();
		mvc.perform(MockMvcRequestBuilders.get("/string")).andExpect(
				MockMvcResultMatchers.content().string(JsonInjectionMitigationHandlerInterceptor.PRE + "123"));
		mvc.perform(MockMvcRequestBuilders.get("/mav")).andExpect(MockMvcResultMatchers.view().name("index"));
	}

}
