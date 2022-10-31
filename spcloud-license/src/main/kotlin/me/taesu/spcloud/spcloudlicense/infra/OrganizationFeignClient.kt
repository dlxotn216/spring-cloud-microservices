package me.taesu.spcloud.spcloudlicense.infra

import me.taesu.spcloud.spcloudlicense.support.SuccessResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

/**
 * Created by taesu on 2022/10/29.
 *
 * @author Lee Tae Su
 * @version spcloud-license
 * @since spcloud-license
 */
@FeignClient("organization-service")
interface OrganizationFeignClient {
    @GetMapping(
        value = ["/api/v1/organizations/{organizationKey}"],
        consumes = ["application/json"]
    )
    fun retrieve(@PathVariable organizationKey: Long): SuccessResponse<OrganizationRetrieveResponse>
}
