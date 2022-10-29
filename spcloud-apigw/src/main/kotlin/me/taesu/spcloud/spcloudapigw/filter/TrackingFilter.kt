package me.taesu.spcloud.spcloudapigw.filter

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.*

/**
 * Created by taesu on 2022/10/29.
 *
 * @author Lee Tae Su
 * @version spcloud-apigw
 * @since spcloud-apigw
 */
@Order(1)
@Component
class TrackingFilter(private val filterUtils: FilterUtils): GlobalFilter {
    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void?>? {
        val requestHeaders: HttpHeaders = exchange.request.headers
        val nextExchange = if (isCorrelationIdPresent(requestHeaders)) {
            logger.debug(
                "tmx-correlation-id found in tracking filter: {}. ",
                filterUtils.getCorrelationId(requestHeaders)
            )
            exchange
        } else {
            val correlationID = generateCorrelationId()
            logger.debug("tmx-correlation-id generated in tracking filter: {}.", correlationID)
            filterUtils.setCorrelationId(exchange, correlationID)
        }
        return chain.filter(nextExchange)
    }


    private fun isCorrelationIdPresent(requestHeaders: HttpHeaders): Boolean {
        return filterUtils.getCorrelationId(requestHeaders) != null
    }

    private fun generateCorrelationId(): String {
        return UUID.randomUUID().toString()
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
