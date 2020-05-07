package com.example.gateway.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.alibaba.fastjson.JSONObject;
import com.example.gateway.model.Result;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GlobalFilter , Ordered {

    @Override
    public int getOrder() {
        // TODO Auto-generated method stub
        return -999;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // TODO Auto-generated method stub
        exchange.getResponse().setStatusCode(HttpStatus.OK);
        exchange.getResponse().getHeaders().add("Content-type", "application/json;charset=UTF-8");
        Result result = Result.getInstance();
        boolean isSkipSign = false;
        if (!isSkipSign) {
            String sign = exchange.getRequest().getQueryParams().getFirst("sign");
            if (StringUtils.isEmpty(sign)) {
                result.setCode(200);
                result.setMsg("签名为空");
                return exchange.getResponse().writeWith(Flux.just(getBodyBuffer(exchange.getResponse(), result)));
            }

        }
        return null;
    }

    private DataBuffer getBodyBuffer(ServerHttpResponse response, Result result) {
        return response.bufferFactory().wrap(JSONObject.toJSONBytes(result));
    }

}