package me.taesu.spcloud.spcloudorganization.controller

/**
 * Created by taesu on 2022/10/29.
 *
 * @author Lee Tae Su
 * @version spcloud-organization
 * @since spcloud-organization
 */
class OrganizationRetrieveResponse(
    val organizationKey: Long,
    val name: String,
    val description: String,
)
