package me.taesu.spcloud.spcloudlicense.model

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

/**
 * Created by taesu on 2022/10/27.
 *
 * @author Lee Tae Su
 * @version spcloud-license
 * @since spcloud-license
 */
@Entity
class License(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LICENSE_SEQ")
    @SequenceGenerator(
        name = "LICENSE_SEQ", sequenceName = "LICENSE_SEQ",
        initialValue = 1, allocationSize = 1
    )
    val licenseKey: Long = 0L,
    val organizationKey: Long,
    val licenseId: String,
    val licenseType: String,
    productName: String,
    description: String,
) {
    var productName: String = productName
        protected set

    var description: String = description
        protected set
}

interface LicenseRepository: JpaRepository<License, Long> {
    fun findByOrganizationKeyAndLicenseKey(
        organizationKey: Long,
        licenseKey: Long
    ): License?

    fun deleteByOrganizationKeyAndLicenseKey(
        organizationKey: Long,
        licenseKey: Long
    )
}

fun LicenseRepository.findOrThrow(
    organizationKey: Long,
    licenseKey: Long
) = findByOrganizationKeyAndLicenseKey(organizationKey = organizationKey, licenseKey = licenseKey)
    ?: throw IllegalArgumentException("$organizationKey, $licenseKey")
