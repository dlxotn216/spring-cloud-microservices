package me.taesu.spcloud.spcloudconfig

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class SpcloudConfigApplication

fun main(args: Array<String>) {
    runApplication<SpcloudConfigApplication>(*args)
}
