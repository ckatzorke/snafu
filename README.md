# snafu


Security, so it don't get to [SNAFU](http://en.wikipedia.org/wiki/Military_slang#SNAFU)

## JsonInjectionMitigation

This project provides to basic utilities to enable JsonInjectionMitigation for your web project (especially when using [Spring MVC)](http://spring.io).
The mitigation is provided by adding **)]}',\n** to the JSON response. See http://haacked.com/archive/2008/11/20/anatomy-of-a-subtle-json-vulnerability.aspx/ for more info.

Additionally, the prefix is also used by [AngularJS](https://angularjs.org/) to prevent JSON injection.

### oss.snafu.json.JsonInjectionMitigationFilter

HttpServletFilter for mitigation. Just put in front of your handler.

### oss.snafu.json.JsonInjectionMitigationHandlerInterceptor

Spring HandlerInterceptor for mitigation. Just put in front of your handler. E.g. when using WebMvcConfigurerAdapter  

```java
@Override
public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(new JsonInjectionMitigationHandlerInterceptor()).addPathPatterns("/resturl1", "/resturl2");
}
```