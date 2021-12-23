package edu.nuc.ihouse_01.intercepter;

import com.alibaba.fastjson.JSON;
import edu.nuc.ihouse_01.model.dto.ResponseData;
import edu.nuc.ihouse_01.utils.JWTUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//拦截校验Token
public class HeaderTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ResponseData responseData = null;
        //获取请求头中的token
        String headerToken = request.getHeader("token");
        //检测当前请求是否需要拦截
        if (!request.getRequestURI().contains("login") && !request.getRequestURI().contains("/doc.html")) {
            if (headerToken == null) {
                //token不存在  则返回错误信息
                responseData = ResponseData.customerError("用户认证不通过");
            }
            //如果token存在 则进行验证和更新  对token进行续期
            try {
                headerToken = JWTUtil.updateToken(headerToken);
            } catch (Exception e) {
                //token验证错误
                responseData = ResponseData.serverInternalError();
            }
        }
        if (responseData != null) {// 如果由错误信息
            response.getWriter().write(JSON.toJSONString(responseData));
            return false;
        } else {
            //认证通过  将token加入页面你的header 返回
            response.setHeader("token", headerToken);
            return true;
        }
    }
}