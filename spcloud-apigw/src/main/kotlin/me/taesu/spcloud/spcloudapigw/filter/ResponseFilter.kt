package me.taesu.spcloud.spcloudapigw.filter

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono


/**
 * Created by taesu on 2022/10/29.
 *
 * @author Lee Tae Su
 * @version spcloud-apigw
 * @since spcloud-apigw
 */
@Configuration
class ResponseFilter(private val filterUtils: FilterUtils) {
    @Bean
    fun postGlobalFilter(): GlobalFilter {
        return GlobalFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->
            chain.filter(exchange).then(
                Mono.fromRunnable {
                    val requestHeaders: HttpHeaders = exchange.request.headers
                    val correlationId = filterUtils.getCorrelationId(requestHeaders)
                    logger.debug("Adding the correlation id to the outbound headers. {}", correlationId)
                    exchange.response.headers.add(FilterUtils.CORRELATION_ID, correlationId)
                    logger.debug("Completing outgoing request for {}.", exchange.request.uri)
                }
            )
        }
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
