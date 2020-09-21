package indi.wechat.work.interceptor;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 后台监测数据拦截器
 * </p>
 *
 * @author liguoqing
 * @since 2020/4/24
 */
@Slf4j
public class WebRequestDataInterceptor implements HandlerInterceptor {

    @Resource
    private Environment environment;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long start = System.currentTimeMillis();
        request.setAttribute("action-start-time", start);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 处理方法
        boolean rightMethod = handler instanceof HandlerMethod;
        if (!rightMethod) {
            return;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 方法描述
        String methodDesc = "";
        Operation methodAnnotation = handlerMethod.getMethodAnnotation(Operation.class);
        if (methodAnnotation != null) {
            methodDesc = methodAnnotation.description();
        }
        // 请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        String requestParams = JSON.toJSONString(parameterMap);
        log.debug("request method:{},url:{}", methodDesc, requestParams);
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
