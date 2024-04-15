package github.natholdallas.enshrine.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import github.natholdallas.enshrine.common.*
import github.natholdallas.enshrine.data.*
import github.natholdallas.enshrine.mapper.VideoMapper
import github.natholdallas.enshrine.mapper.userNow
import github.natholdallas.enshrine.model.Video
import jakarta.validation.Valid
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@SaCheckLogin
@RestCtl
@RequestMapping("/video")
class VideoController(private val videoMapper: VideoMapper) {

    @Get
    fun list(@Attr @Valid data: Pagination): ResponseEntity<PageVo<Video>> {
        val page = videoMapper.findAllByUser(userNow, PageRequest.of(data.page, data.size))
        return ResponseEntity(PageVo.of(page), OK)
    }

    @Get("/{id}")
    fun retrieve(@Path id: Long): ResponseEntity<Video> {
        val video = videoMapper.findByIdAndUser(id, userNow).get()
        return ResponseEntity(video, OK)
    }

    @Post(headers = ["Accept=application/json"])
    fun create(@Body @Valid data: VideoCreationDto): ResponseEntity<Video> {
        val video = Video(
            user = userNow,
            title = data.title!!,
            videoUrl = data.videoUrl,
            description = data.description
        )
        videoMapper.save(video)
        return ResponseEntity(video, OK)
    }

    @Put(path = ["/{id}"], headers = ["Accept=application/json"])
    fun update(@Path id: Long, @Body @Valid data: VideoUpdateDto): ResponseEntity<Any> {
        val video: Video = videoMapper.findByIdAndUser(id, userNow).get()
        video.title = data.title!!
        video.videoUrl = data.videoUrl
        video.description = data.description
        videoMapper.save(video)
        return ResponseEntity(OK)
    }

    @Delete("/{id}")
    fun destroy(@Path id: Long): ResponseEntity<Any> {
        videoMapper.deleteByIdAndUser(id, userNow)
        return ResponseEntity(OK)
    }

}
