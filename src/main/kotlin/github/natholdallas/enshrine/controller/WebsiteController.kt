package github.natholdallas.enshrine.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import github.natholdallas.enshrine.common.*
import github.natholdallas.enshrine.data.*
import github.natholdallas.enshrine.mapper.WebsiteMapper
import github.natholdallas.enshrine.mapper.userNow
import github.natholdallas.enshrine.model.Website
import jakarta.validation.Valid
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@SaCheckLogin
@RestCtl
@RequestMapping("/website")
class WebsiteController(private val websiteMapper: WebsiteMapper) {

    @Get
    fun list(@Attr @Valid data: Pagination): ResponseEntity<PageVo<Website>> {
        val page = websiteMapper.findAllByUser(userNow, PageRequest.of(data.page, data.size))
        return ResponseEntity(PageVo.of(page), OK)
    }

    @Get("/{id}")
    fun retrieve(@Path id: Long): ResponseEntity<Website> {
        val website = websiteMapper.findByIdAndUser(id, userNow).get()
        return ResponseEntity(website, OK)
    }

    @Post(headers = ["Accept=application/json"])
    fun create(@Body @Valid data: WebsiteCreationDto): ResponseEntity<Website> {
        val website = Website(
            user = userNow,
            title = data.title!!,
            siteUrl = data.siteUrl,
            description = data.description
        )
        websiteMapper.save(website)
        return ResponseEntity(website, OK)
    }

    @Put(path = ["/{id}"], headers = ["Accept=application/json"])
    fun update(@Path id: Long, @Body @Valid data: WebsiteUpdateDto): ResponseEntity<Any> {
        val website = websiteMapper.findByIdAndUser(id, userNow).get()
        website.title = data.title!!
        website.siteUrl = data.siteUrl
        website.description = data.description
        websiteMapper.save(website)
        return ResponseEntity(OK)
    }

    @Delete("/{id}")
    fun destroy(@Path id: Long): ResponseEntity<Any> {
        websiteMapper.deleteByIdAndUser(id, userNow)
        return ResponseEntity(OK)
    }

}
