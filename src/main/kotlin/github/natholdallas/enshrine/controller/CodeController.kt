package github.natholdallas.enshrine.controller

import github.natholdallas.enshrine.common.Body
import github.natholdallas.enshrine.common.Post
import github.natholdallas.enshrine.common.RestCtl
import github.natholdallas.enshrine.data.CodeCreationDto
import github.natholdallas.enshrine.mapper.CodeMapper
import github.natholdallas.enshrine.model.Code
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import org.springframework.web.bind.annotation.RequestMapping

@RestCtl
@RequestMapping("/code")
class CodeController(private val codeMapper: CodeMapper, private val mailSender: MailSender) {

    @Post(headers = ["Accept=application/json"])
    fun create(@Body @Valid data: CodeCreationDto): ResponseEntity<Any> {
        val code = Code(email = data.email!!)
        codeMapper.save(code)
        val message = SimpleMailMessage()
        message.setTo(data.email)
        message.from = "natholdallas@qq.com"
        message.subject = "enshrine"
        message.text = "The verification code is: ${code.id}"
        mailSender.send(message)
        return ResponseEntity(OK)
    }

}

