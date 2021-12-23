package edu.nuc.ihouse_01.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //判断用户是否登录
        if (session.getAttribute("user")==null) {
            request.setAttribute("msg","未登录，请登录后再来访问");
            request.getRequestDispatcher("/loginPage").forward(request,response);
            return false;
        }else {
            return true;
        }
    }
}
