package me.taesu.spcloud.spcloudlicense.application

import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.util.*

/**
 * Created by taesu on 2022/10/27.
 *
 * @author Lee Tae Su
 * @version spcloud-license
 * @since spcloud-license
 */
@Service
class MessageService(
    private val messageSource: MessageSource
) {
    fun resolve(
        messageId: String,
        args: Array<Any>,
        locale: Locale
    ): String {
        return messageSource.getMessage(messageId, args,locale)
    }
}
