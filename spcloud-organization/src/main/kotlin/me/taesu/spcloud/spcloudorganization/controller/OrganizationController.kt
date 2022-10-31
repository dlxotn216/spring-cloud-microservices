package me.taesu.spcloud.spcloudorganization.controller

import me.taesu.spcloud.spcloudorganization.support.SuccessResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import javax.annotation.security.RolesAllowed

/**
 * Created by taesu on 2022/10/29.
 *
 * @author Lee Tae Su
 * @version spcloud-organization
 * @since spcloud-organization
 */
@RestController
class OrganizationController {
    @RolesAllowed(value = ["ADMIN"])
    @GetMapping("/api/v1/organizations/{organizationKey}")
    fun retrieve(@PathVariable organizationKey: Long): SuccessResponse<OrganizationRetrieveResponse> {
        return SuccessResponse.from(
            OrganizationRetrieveResponse(
                organizationKey = organizationKey,
                name = "Taesu ORG",
                description = "test org"
            ),
            message = "success to retrieve"
        )
    }
}
