package me.taesu.spcloud.spcloudlicense

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.cloud.openfeign.EnableFeignClients

@RefreshScope
@SpringBootApplication
@EnableFeignClients
class SpcloudLicenseApplication

fun main(args: Array<String>) {
    runApplication<SpcloudLicenseApplication>(*args)
}
