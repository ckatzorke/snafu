package oss.snafu.json;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {

	/**
	 * Should be handled by {@link JsonInjectionMitigationHandlerInterceptor}
	 *
	 * @return
	 */
	@RequestMapping("/string")
	public @ResponseBody String testResponseBody() {
		return "123";
	}

	/**
	 * Should NOT be handled by {@link JsonInjectionMitigationHandlerInterceptor}, due to not haven @RespomseBody
	 * annotation
	 *
	 * @return
	 */
	@RequestMapping("/mav")
	public String testNoResponseBody() {
		return "index";
	}

	/**
	 * SHould not be handled since the test case does not add the interceptor to this mapping
	 *
	 * @return
	 */
	@RequestMapping("/nointerceptor")
	public @ResponseBody String testNoInterceptor() {
		return "123";
	}

}
