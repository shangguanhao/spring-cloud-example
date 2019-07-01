package com.shangguan.client.filter;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class addResponseHeaderFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
	      RequestContext requestContext = RequestContext.getCurrentContext();
	      HttpServletResponse response = requestContext.getResponse();
	      response.setHeader("To-Ali", UUID.randomUUID().toString());
	      return null;
	}

	@Override
	public String filterType() {
		return POST_TYPE;
	}

	@Override
	public int filterOrder() {
		return SEND_RESPONSE_FILTER_ORDER - 1;
	}

}
