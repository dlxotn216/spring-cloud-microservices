package me.taesu.spcloud.spcloudlicense.infra

import me.taesu.spcloud.spcloudlicense.support.SuccessResponse
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component

/**
 * Created by taesu on 2022/10/29.
 *
 * @author Lee Tae Su
 * @version spcloud-license
 * @since spcloud-license
 */
@Component
class OrganizationKeyCloakClient(private val keycloakRestTemplate: KeycloakRestTemplate) {
    fun retrieve(organizationKey: Long): SuccessResponse<OrganizationRetrieveResponse> {
        return keycloakRestTemplate.exchange(
            "http://gateway:8072/organization/api/v1/organizations/{organizationKey}",
            HttpMethod.GET,
            null,
            object: ParameterizedTypeReference<SuccessResponse<OrganizationRetrieveResponse>>() {},
            organizationKey
        ).body!!
    }
}
