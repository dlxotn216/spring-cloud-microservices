package me.taesu.spcloud.spcloudlicense.interfaces

import me.taesu.spcloud.spcloudlicense.application.LicenseService
import me.taesu.spcloud.spcloudlicense.application.MessageService
import me.taesu.spcloud.spcloudlicense.support.SuccessResponse
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * Created by taesu on 2022/10/27.
 *
 * @author Lee Tae Su
 * @version spcloud-license
 * @since spcloud-license
 */
@RestController
class LicenseController(
    private val service: LicenseService,
    private val messageService: MessageService
) {
    @PostMapping("/api/v1/organizations/{organizationKey}/licenses")
    fun create(
        @PathVariable organizationKey: Long,
        @RequestBody request: LicenseCreateRequest
    ): SuccessResponse<LicenseRetrieveResponse> {
        val response = service.create(organizationKey, request)
        return SuccessResponse.from(
            response,
            messageService.resolve(
                "license.create.message",
                arrayOf(response.licenseKey),
                LocaleContextHolder.getLocale()
            )
        )
    }

    @GetMapping("/api/v1/organizations/{organizationKey}/licenses/{licenseKey}")
    fun retrieve(
        @PathVariable organizationKey: Long,
        @PathVariable licenseKey: Long,
    ): SuccessResponse<LicenseRetrieveResponse> {
        val response = service.retrieve(organizationKey, licenseKey)
        return SuccessResponse.from(
            response,
            messageService.resolve(
                "license.retrieve.message",
                arrayOf(response.licenseKey),
                LocaleContextHolder.getLocale()
            )
        )
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/api/v1/organizations/{organizationKey}/licenses/{licenseKey}")
    fun delete(
        @PathVariable organizationKey: Long,
        @PathVariable licenseKey: Long,
    ): SuccessResponse<Map<String, String>> {
        service.delete(organizationKey, licenseKey)
        return SuccessResponse.empty(
            messageService.resolve(
                "license.create.message",
                arrayOf(licenseKey),
                LocaleContextHolder.getLocale()
            )
        )
    }
}
