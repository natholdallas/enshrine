package github.natholdallas.enshrine.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import github.natholdallas.enshrine.common.*
import github.natholdallas.enshrine.data.*
import github.natholdallas.enshrine.mapper.StarMapper
import github.natholdallas.enshrine.mapper.userNow
import github.natholdallas.enshrine.model.Star
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@SaCheckLogin
@RestCtl
@RequestMapping("/star")
class StarController(private val starMapper: StarMapper) {

    @Get
    fun list(@Attr @Valid data: Pagination): ResponseEntity<PageVo<Star>> {
        val page: Page<Star> = starMapper.findAllByUser(userNow, data)
        return ResponseEntity(PageVo.of(page), OK)
    }

    @Get("/{id}")
    fun retrieve(@Path @Valid id: Long): ResponseEntity<Star> {
        val star: Star = starMapper.findByIdAndUser(id, userNow).get()
        return ResponseEntity(star, OK)
    }

    @Post(headers = ["Accept=application/json"])
    fun create(@Body @Valid data: StarCreationDto): ResponseEntity<Star> {
        val star = Star(
            user = userNow,
            name = data.name!!,
            imageUrl = data.imageUrl,
            description = data.description
        )
        starMapper.save(star)
        return ResponseEntity(star, OK)
    }

    @Put(path = ["/{id}"], headers = ["Accept=application/json"])
    fun update(@Path id: Long, @Body @Valid data: StarUpdateDto): ResponseEntity<Any> {
        val star: Star = starMapper.findByIdAndUser(id, userNow).get()
        star.name = data.name!!
        star.imageUrl = data.imageUrl
        star.description = data.description
        star.latestUpdate = System.currentTimeMillis()
        starMapper.save(star)
        return ResponseEntity(OK)
    }

    @Delete("/{id}")
    fun destroy(@Path id: Long): ResponseEntity<Any> {
        starMapper.deleteByIdAndUser(id, userNow)
        return ResponseEntity(OK)
    }

}