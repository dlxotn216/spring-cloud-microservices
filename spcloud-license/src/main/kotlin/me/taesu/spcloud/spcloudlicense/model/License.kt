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
@Table(name = "LICENSE")
class License(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LICENSE_KEY")
    val licenseKey: Long = 0L,

    @Column(name = "ORGANIZATION_KEY")
    val organizationKey: Long,

    @Column(name = "LICENSE_ID")
    val licenseId: String,

    @Column(name = "LICENSE_TYPE")
    val licenseType: String,
    productName: String,
    description: String,
) {
    @Column(name = "PRODUCT_NAME")
    var productName: String = productName
        protected set

    @Column(name = "DESCRIPTION", columnDefinition = "text")
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
