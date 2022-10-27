package me.taesu.spcloud.spcloudlicense.interfaces

/**
 * Created by taesu on 2022/10/27.
 *
 * @author Lee Tae Su
 * @version spcloud-license
 * @since spcloud-license
 */
class LicenseCreateRequest(
    val licenseId: String,
    val description: String,
    val productName: String,
    val licenseType: String,
)

class LicenseRetrieveResponse(
    val licenseKey: Long,
    val licenseId: String,
    val organizationKey: Long,
    val description: String,
    val productName: String,
    val licenseType: String,
)
