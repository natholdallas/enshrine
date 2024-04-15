package github.natholdallas.enshrine.controller

import github.natholdallas.enshrine.common.Body
import github.natholdallas.enshrine.common.Get
import github.natholdallas.enshrine.common.Put
import github.natholdallas.enshrine.common.RestCtl
import github.natholdallas.enshrine.data.ConfigUpdateDto
import github.natholdallas.enshrine.mapper.ConfigMapper
import github.natholdallas.enshrine.mapper.userNow
import github.natholdallas.enshrine.model.Config
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping

@RestCtl
@RequestMapping("/config")
class ConfigController(private val configMapper: ConfigMapper) {

    @Get
    fun retrieve(): ResponseEntity<Config> {
        val config = configMapper.findByUser(userNow).get()
        return ResponseEntity(config, OK)
    }

    @Put
    fun update(@Body @Valid data: ConfigUpdateDto): ResponseEntity<Config> {
        val config = configMapper.findByUser(userNow).get()
        config.editMode = data.editMode!!
        config.darkMode = data.darkMode!!
        config.menuReduced = data.menuReduced!!
        config.avatarCircle = data.avatarCircle!!
        configMapper.save(config)
        return ResponseEntity(config, OK)
    }

}