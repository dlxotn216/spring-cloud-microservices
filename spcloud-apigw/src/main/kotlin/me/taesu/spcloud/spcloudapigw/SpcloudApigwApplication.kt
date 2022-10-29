package me.taesu.spcloud.spcloudapigw

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class SpcloudApigwApplication

fun main(args: Array<String>) {
    runApplication<SpcloudApigwApplication>(*args)
}
