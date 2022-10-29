package me.taesu.spcloud.spcloudeureka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class SpcloudEurekaApplication

fun main(args: Array<String>) {
    runApplication<SpcloudEurekaApplication>(*args)
}
