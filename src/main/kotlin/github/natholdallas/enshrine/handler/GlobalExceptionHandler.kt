package github.natholdallas.enshrine.common

import cn.dev33.satoken.exception.NotLoginException
import cn.dev33.satoken.exception.NotPermissionException
import cn.dev33.satoken.stp.StpUtil
import github.natholdallas.enshrine.data.InvalidMsg
import github.natholdallas.enshrine.data.InvalidVo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler

@Advice
class GlobalExceptionHandler {

    @ExceptionHandler(NotLoginException::class)
    fun notLogin(): ResponseEntity<Any> {
        StpUtil.logout()
        return ResponseEntity(HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(NotPermissionException::class)
    fun notPermission(): ResponseEntity<Any> {
        return ResponseEntity(HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun noSuchElement(): ResponseEntity<InvalidVo> {
        val msgs = listOf(
            InvalidMsg("noSuchElement", "Can not found any datas")
        )
        return ResponseEntity(InvalidVo(msgs.size, msgs), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValid(e: MethodArgumentNotValidException): ResponseEntity<InvalidVo> {
        return ResponseEntity(InvalidVo.of(e), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadable(): ResponseEntity<InvalidVo> {
        val msgs = listOf(
            InvalidMsg("resolver", "Faild to resolve body content, please type '/swagger' to reference our api doc."),
            InvalidMsg("required", "Required parameters are not filled in.")
        )
        return ResponseEntity(InvalidVo(msgs.size, msgs), HttpStatus.OK)
    }

}
