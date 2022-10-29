package me.taesu.spcloud.spcloudapigw.filter

import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange




/**
 * Created by taesu on 2022/10/29.
 *
 * @author Lee Tae Su
 * @version spcloud-apigw
 * @since spcloud-apigw
 */
@Component
class FilterUtils {
    fun getCorrelationId(requestHeaders: HttpHeaders): String? {
        return if (requestHeaders[CORRELATION_ID] != null) {
            val header: List<String> = requestHeaders[CORRELATION_ID] ?: emptyList()
            header.firstOrNull()
        } else {
            null
        }
    }

    fun setRequestHeader(exchange: ServerWebExchange, name: String, value: String): ServerWebExchange {
        return exchange.mutate().request(
            exchange.request.mutate()
                .header(name, value)
                .build()
        ).build()
    }

    fun setCorrelationId(exchange: ServerWebExchange, correlationId: String): ServerWebExchange {
        return setRequestHeader(exchange, CORRELATION_ID, correlationId)
    }

    companion object {
        const val CORRELATION_ID = "tmx-correlation-id"
        const val AUTH_TOKEN = "tmx-auth-token"
        const val USER_ID = "tmx-user-id"
        const val ORG_ID = "tmx-org-id"
        const val PRE_FILTER_TYPE = "pre"
        const val POST_FILTER_TYPE = "post"
        const val ROUTE_FILTER_TYPE = "route"
    }
}
