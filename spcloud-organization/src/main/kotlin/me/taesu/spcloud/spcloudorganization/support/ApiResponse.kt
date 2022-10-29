package me.taesu.spcloud.spcloudorganization.support

/**
 * Created by taesu on 2022/10/29.
 *
 * @author Lee Tae Su
 * @version spcloud-organization
 * @since spcloud-organization
 */
class SuccessResponse<T>(
    val result: T,
    val message: String
) {
    val status: Boolean = true

    companion object {
        fun <T> from(
            result: T,
            message: String
        ): SuccessResponse<T> {
            return SuccessResponse(result, message)
        }

        fun empty(message: String): SuccessResponse<Map<String, String>> {
            return SuccessResponse(emptyMap(), message)
        }
    }
}
