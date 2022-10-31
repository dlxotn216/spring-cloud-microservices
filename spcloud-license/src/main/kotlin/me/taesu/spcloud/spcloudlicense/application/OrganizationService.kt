package me.taesu.spcloud.spcloudlicense.application

import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.retry.annotation.Retry
import me.taesu.spcloud.spcloudlicense.infra.OrganizationFeignClient
import me.taesu.spcloud.spcloudlicense.infra.OrganizationKeyCloakClient
import me.taesu.spcloud.spcloudlicense.infra.OrganizationRetrieveResponse
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.TimeoutException

/**
 * Created by taesu on 2022/10/29.
 *
 * @author Lee Tae Su
 * @version spcloud-license
 * @since spcloud-license
 */
@Service
class OrganizationService(
    private val organizationFeignClient: OrganizationFeignClient,
    private val organizationKeyCloakClient: OrganizationKeyCloakClient,
) {
    @CircuitBreaker(name = "licenseService", fallbackMethod = "retrieveFailBack")
    @Bulkhead(name = "bulkheadLicenseService", fallbackMethod = "retrieveFailBack")
    @Retry(name = "retryLicenseService", fallbackMethod = "retrieveFailBack")
    fun retrieve(organizationKey: Long): OrganizationRetrieveResponse {
        randomSleep()
        return organizationKeyCloakClient.retrieve(organizationKey).result
    }

    fun retrieveFailBack(organizationKey: Long, t: Throwable) =
        OrganizationRetrieveResponse(
            organizationKey = -1L,
            name = "unknown",
            description = "service not available."
        )

    private fun randomSleep() {
        with((Random().nextInt(3) + 1)) {
            if (this == 3) {
                sleep()
            }
        }
    }

    private fun sleep() {
        try {
            Thread.sleep(5000)
            throw TimeoutException()
        } catch (e: InterruptedException) {
            // ignore
        }
    }
}
