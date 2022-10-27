package me.taesu.spcloud.spcloudlicense.application

import me.taesu.spcloud.spcloudlicense.interfaces.LicenseCreateRequest
import me.taesu.spcloud.spcloudlicense.interfaces.LicenseRetrieveResponse
import me.taesu.spcloud.spcloudlicense.model.License
import me.taesu.spcloud.spcloudlicense.model.LicenseRepository
import me.taesu.spcloud.spcloudlicense.model.findOrThrow
import org.springframework.stereotype.Service

/**
 * Created by taesu on 2022/10/27.
 *
 * @author Lee Tae Su
 * @version spcloud-license
 * @since spcloud-license
 */
@Service
class LicenseService(private val repository: LicenseRepository) {
    fun create(organizationKey: Long, request: LicenseCreateRequest): LicenseRetrieveResponse {
        val license = repository.save(request.toEntity(organizationKey))
        return license.toResponse()
    }

    fun retrieve(organizationKey: Long, licenseKey: Long): LicenseRetrieveResponse {
        val license = repository.findOrThrow(organizationKey, licenseKey)
        return license.toResponse()
    }

    fun delete(organizationKey: Long, licenseKey: Long) {
        repository.deleteByOrganizationKeyAndLicenseKey(organizationKey, licenseKey)
    }
}

fun LicenseCreateRequest.toEntity(organizationKey: Long) = License(
    organizationKey = organizationKey,
    licenseId = licenseId,
    licenseType = licenseType,
    productName = productName,
    description = description
)

fun License.toResponse() = LicenseRetrieveResponse(
    licenseKey = licenseKey,
    organizationKey = organizationKey,
    licenseId = licenseId,
    licenseType = licenseType,
    productName = productName,
    description = description
)
