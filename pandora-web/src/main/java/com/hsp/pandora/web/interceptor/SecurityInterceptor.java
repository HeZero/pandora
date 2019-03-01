package com.hsp.pandora.web.interceptor;

import com.alibaba.druid.util.StringUtils;
import com.hsp.pandora.web.beans.AuthContext;
import com.hsp.pandora.web.beans.AuthEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: heshipeng
 * @Date: 2019/2/28
 * @Time: 16:37
 * @Description: 权限拦截器
 */
@Component
public class SecurityInterceptor implements HandlerInterceptor
{

    private static final Set<String> IGNORES = new HashSet<String>();

    static
    {
        //TODO 这里可以放不需要权限直接放行的url
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        System.out.println("当前请求路径:" + request.getRequestURI());
        if(IGNORES.stream().anyMatch(i -> request.getRequestURI().contains(i)))
        {
            return true;
        }

        try
        {

            Map<String, String> auths = null;//这里需要自己扩展一下，从数据库拿权限,或者从配置文件中拿

            for(Map.Entry<String, String> entry: auths.entrySet())
            {
                //校验模块权限
                if(Pattern.matches(".*".concat(entry.getKey().concat(".*")), request.getRequestURI()))
                {
                    if(this.hasPermission(handler, entry.getValue()))
                    {
                        return true;
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 根据controller注解检查用户权限是否满足
     * @param handler
     * @return
     */
    private boolean hasPermission(Object handler, String permission)
    {
        if (handler instanceof HandlerMethod)
        {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            AuthContext authContext = handlerMethod.getMethod().getAnnotation(AuthContext.class);
            // 不需要授权的方法
           if(authContext == null || StringUtils.equals(permission, AuthEnum.ALL.name())) return true;

           if(!StringUtils.equals(permission, authContext.value().name())) return false;

        }

        return true;
    }
}
