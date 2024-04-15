package github.natholdallas.enshrine.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import cn.dev33.satoken.stp.StpUtil
import github.natholdallas.enshrine.common.*
import github.natholdallas.enshrine.data.EmailPatchDto
import github.natholdallas.enshrine.data.PasswordPatchDto
import github.natholdallas.enshrine.data.UserUpdateDto
import github.natholdallas.enshrine.mapper.UserMapper
import github.natholdallas.enshrine.model.User
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping

@SaCheckLogin
@RestCtl
@RequestMapping("/user")
class UserController(private val userMapper: UserMapper) {

    @Get
    fun retrieve(): ResponseEntity<User> {
        val user: User = userMapper.findById(StpUtil.getLoginIdAsString()).get()
        return ResponseEntity(user, OK)
    }

    @Put(headers = ["Accept=application/json"])
    fun update(@Body @Valid data: UserUpdateDto): ResponseEntity<User> {
        val user: User = userMapper.findById(StpUtil.getLoginIdAsString()).get()
        user.nickname = data.nickname
        user.avatarUrl = data.avatarUrl
        user.latestUpdate = System.currentTimeMillis()
        userMapper.save(user)
        return ResponseEntity(user, OK)
    }

    @Patch(path = ["/email"], headers = ["Accept=application/json"])
    fun patchEmail(@Body @Valid data: EmailPatchDto): ResponseEntity<Any> {
        val user: User = userMapper.findById(StpUtil.getLoginIdAsString()).get()
        user.email = data.email!!
        userMapper.save(user)
        return ResponseEntity(OK)
    }

    @Patch(path = ["/password"], headers = ["Accept=application/json"])
    fun patchPassword(@Body @Valid data: PasswordPatchDto): ResponseEntity<Any> {
        val user: User = userMapper.findById(StpUtil.getLoginIdAsString()).get()
        user.password = data.password!!
        userMapper.save(user)
        return ResponseEntity(OK)
    }

    @Delete
    fun destroy(): ResponseEntity<Any> {
        userMapper.deleteById(StpUtil.getLoginIdAsString())
        return ResponseEntity(OK)
    }

}
