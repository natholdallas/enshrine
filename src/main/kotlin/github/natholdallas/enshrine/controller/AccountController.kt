package github.natholdallas.enshrine.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import cn.dev33.satoken.stp.StpUtil
import github.natholdallas.enshrine.common.*
import github.natholdallas.enshrine.data.LoginDto
import github.natholdallas.enshrine.data.RegisterDto
import github.natholdallas.enshrine.mapper.ConfigMapper
import github.natholdallas.enshrine.mapper.UserMapper
import github.natholdallas.enshrine.model.Config
import github.natholdallas.enshrine.model.User
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping

@RestCtl
@RequestMapping
class AccountController(private val userMapper: UserMapper, private val configMapper: ConfigMapper) {

    @Post(path = ["/register"], headers = ["Accept=application/json"])
    fun register(@Body @Valid data: RegisterDto): ResponseEntity<Any> {
        val user = User(email = data.email!!, password = data.password!!)
        userMapper.save(user)
        configMapper.save(Config(user = user))
        return ResponseEntity(OK)
    }

    @Post(path = ["/login"], headers = ["Accept=application/json"])
    fun login(@Body @Valid data: LoginDto): ResponseEntity<Any> {
        val user: User = userMapper.findByEmailAndPassword(data.email, data.password).get()
        StpUtil.login(user.id)
        return ResponseEntity(OK)
    }

    @SaCheckLogin
    @Post("/logout")
    fun logout(): ResponseEntity<Any> {
        StpUtil.logout()
        return ResponseEntity(OK)
    }

}
