package com.fxz.gateway.filter;

import com.fxz.common.core.constant.SecurityConstants;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局拦截器，作用所有的微服务,对请求头中参数进行处理
 *
 * @author fxz
 */
@Component
public class FxzRequestGlobalFilter implements GlobalFilter, Ordered {

	/**
	 * Process the Web request and (optionally) delegate to the next {@code WebFilter}
	 * through the given {@link GatewayFilterChain}.
	 * @param exchange the current server exchange
	 * @param chain provides a way to delegate to the next filter
	 * @return {@code Mono<Void>} to indicate when request processing is complete
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// 清洗请求头中from 参数
		ServerHttpRequest request = exchange.getRequest().mutate()
				.headers(httpHeaders -> httpHeaders.remove(SecurityConstants.FROM)).build();

		return chain.filter(exchange.mutate().request(request).build());
	}

	@Override
	public int getOrder() {
		return -1000;
	}

}
