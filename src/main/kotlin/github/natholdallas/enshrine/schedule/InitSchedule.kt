package github.natholdallas.enshrine.schedule

import github.natholdallas.enshrine.mapper.*
import github.natholdallas.enshrine.model.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.io.File

@Component
class InitSchedule(
    private val userMapper: UserMapper,
    private val configMapper: ConfigMapper,
    private val starMapper: StarMapper,
    private val videoMapper: VideoMapper,
    private val websiteMapper: WebsiteMapper
) : ApplicationRunner {

    private val logger: Logger = LoggerFactory.getLogger(InitSchedule::class.java)

    @Value("\${upload.images}")
    private lateinit var imagePath: String

    override fun run(args: ApplicationArguments?) {
        // mkdirs
        logger.info("imagePath: $imagePath")
        File(imagePath).mkdirs()
        logger.info("Mkdirs completed.")

        // Init database
        logger.info("args: $args")
        val user = User(email = "natholdallas@qq.com", password = "xs1234", nickname = "dallas")
        val config = Config(user = user)
        val stars = listOf(
            Star(user = user, imageUrl = "https://picsum.photos/300/300", name = "Candy Love"),
            Star(user = user, imageUrl = "https://picsum.photos/300/300", name = "Somi"),
            Star(user = user, imageUrl = "https://picsum.photos/300/300", name = "Gina"),
            Star(user = user, imageUrl = "https://picsum.photos/300/300", name = "Luna"),
            Star(user = user, imageUrl = "https://picsum.photos/300/300", name = "Skylar"),
            Star(user = user, imageUrl = "https://picsum.photos/300/300", name = "Lena Star"),
            Star(user = user, imageUrl = "https://picsum.photos/300/300", name = "Ahh"),
            Star(user = user, imageUrl = "https://picsum.photos/300/300", name = "Aguasdn"),
            Star(user = user, imageUrl = "https://picsum.photos/300/300", name = "AmigosCode"),
            Star(user = user, imageUrl = "https://picsum.photos/300/300", name = "LenaAssss"),
            Star(user = user, imageUrl = "https://picsum.photos/300/300", name = "Oasudb"),
            Star(user = user, imageUrl = "https://picsum.photos/300/300", name = "Oilver"),
            Star(user = user, imageUrl = "https://picsum.photos/300/300", name = "Sinu"),
            Star(user = user, imageUrl = "https://picsum.photos/300/300", name = "Xunn"),
            Star(user = user, imageUrl = "https://picsum.photos/300/300", name = "LunaMixx"),
            Star(user = user, imageUrl = "https://picsum.photos/300/300", name = "Mixin"),
            Star(user = user, imageUrl = "https://picsum.photos/300/300", name = "Jssssuuu"),
            Star(user = user, imageUrl = "https://picsum.photos/300/300", name = "Jade Kush"),
        )
        val videos = listOf(
            Video(user = user, title = "Kotlin Video", description = "Learning Kotlin"),
            Video(user = user, title = "Java Video", description = "Learning Java"),
            Video(user = user, title = "Dart Video", description = "Learning Dart")
        )
        val websites = listOf(
            Website(user = user, title = "bilibili", description = "Watch video"),
            Website(user = user, title = "pixel", description = "A website"),
            Website(user = user, title = "steam", description = "Gaming")
        )
        userMapper.save(user)
        configMapper.save(config)
        starMapper.saveAll(stars)
        videoMapper.saveAll(videos)
        websiteMapper.saveAll(websites)
        logger.info("Init database completed.")
    }

}
