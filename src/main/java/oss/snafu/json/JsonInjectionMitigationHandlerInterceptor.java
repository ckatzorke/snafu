package oss.snafu.json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Spring {@link HandlerInterceptor} based implementation of {@link JsonInjectionMitigationFilter}. When put in
 * front of a controller handler it tries to find if the handler method is annotated with {@link ResponseBody} .
 *
 * @author ckatzorke
 *
 */
public class JsonInjectionMitigationHandlerInterceptor implements HandlerInterceptor {
	public static final String PRE = ")[}',\n";

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		//

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		//

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//
		if (HandlerMethod.class.isAssignableFrom(handler.getClass())) {
			final HandlerMethod handlerMethod = (HandlerMethod) handler;
			final ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
			if (responseBody != null) {
				response.getOutputStream().print(PRE);
			}
		}
		return true;
	}
}
