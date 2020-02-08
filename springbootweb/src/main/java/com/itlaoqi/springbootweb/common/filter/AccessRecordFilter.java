package com.itlaoqi.springbootweb.common.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * @author caiww
 * @date 2019/8/27 -2:16
 */
public class AccessRecordFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(AccessRecordFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".jpg") || uri.endsWith(".png")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String ua = request.getHeader("user-agent");
        String ip = request.getRemoteAddr();
        Long st = new Date().getTime();
        //将请求向后送到Controller进行处理
        filterChain.doFilter(servletRequest, servletResponse);
        Long et = new Date().getTime();
        logger.info("url:{},ip:{},time:{}ms,ua:{}", uri, ip, (et - st), ua);


    }

    @Override
    public void destroy() {

    }
}
