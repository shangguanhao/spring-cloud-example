package com.shangguan.client.filter;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;


/**
 * 权限拦截，区分卖家和卖家
 * @author Administrator
 *
 */
@Component
public class AuthFilter extends ZuulFilter{
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();

		/*
		 * /order/caeate只能买家访问（cookie里面有openid）
		 * /order/finish只能卖家访问（cookie里面 有token，并且对应的redis中值）
		 * /product/list都可访问
		 */
		//这样写不容易维护，可以新建卖家端权限验证和买家端权限验证分别进行判断
//		if("/order/order/create".equals(request.getRequestURI())) {
//			Cookie  cookie = CookieUtil.get(request,"openid");
//			if(cookie == null || StringUtils.isEmpty(cookie.getValue())) {
//				requestContext.setSendZuulResponse(false);
//				requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
//			}
//		}
//		
//		if("/order/order/finish".equals(request.getRequestURI())) {
//			Cookie  cookie = CookieUtil.get(request,"token");
//			if(cookie == null || StringUtils.isEmpty(cookie.getValue())
//					|| StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
//				requestContext.setSendZuulResponse(false);
//				requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
//			}
//		}
		
		
		
		return null;
	}

	@Override
	public String filterType() {
		return PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return PRE_DECORATION_FILTER_ORDER - 1;
	}

}
