package me.taesu.spcloud.spcloudlicense.config

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.web.servlet.LocaleContextResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.SessionLocaleResolver

/**
 * Created by taesu on 2022/10/27.
 *
 * @author Lee Tae Su
 * @version spcloud-license
 * @since spcloud-license
 */
@Configuration
class AppConfig: WebMvcConfigurer {
    @Bean
    fun localeContextResolver(): LocaleContextResolver = SessionLocaleResolver()

    @Bean
    fun messageSource(): MessageSource {
        return ReloadableResourceBundleMessageSource().apply {
            this.setBasename("classpath:messages/message")
            this.setDefaultEncoding("UTF-8")
            this.setUseCodeAsDefaultMessage(true)
        }
    }
}
