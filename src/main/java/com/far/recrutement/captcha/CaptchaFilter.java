package com.far.recrutement.captcha;/*
 * package com.far.recrutement.captcha; import java.io.IOException;
 * 
 * import javax.servlet.Filter; import javax.servlet.FilterChain; import
 * javax.servlet.FilterConfig; import javax.servlet.ServletException; import
 * javax.servlet.ServletRequest; import javax.servlet.ServletResponse;
 * 
 * import org.springframework.core.annotation.Order; import
 * org.springframework.stereotype.Component; import
 * javax.servlet.http.HttpServletRequest;
 * 
 * @Component
 * 
 * @Order(1) public class CaptchaFilter implements Filter{
 * 
 * @Override public void init(FilterConfig filterConfig) throws ServletException
 * { // TODO Auto-generated method stub
 * 
 * } String referrer ; HttpServletRequest req ;
 * 
 * @Override public void doFilter(ServletRequest request, ServletResponse
 * response, FilterChain chain) throws IOException, ServletException {
 * 
 * req = (HttpServletRequest) request;
 * 
 * referrer = req.getHeader("referer");
 * 
 * if( referrer == null ) {
 * 
 * request.getRequestDispatcher("/captcha").forward(request,response); }else {
 * chain.doFilter(request, response); }
 * 
 * }
 * 
 * @Override public void destroy() { // TODO Auto-generated method stub
 * 
 * }
 * 
 * 
 * }
 */