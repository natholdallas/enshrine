package github.natholdallas.enshrine.data

import org.springframework.data.domain.Page
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException

data class PageVo<T>(val total: Long, val pages: Int, val content: List<T>) {

    companion object {
        fun <T> of(page: Page<T>): PageVo<T> {
            println(page.toList())
            return PageVo(page.totalElements, page.totalPages, page.content)
        }
    }

}

data class InvalidVo(val count: Int, val errors: List<InvalidMsg>) {

    companion object {
        fun of(e: MethodArgumentNotValidException): InvalidVo {
            val errors: List<InvalidMsg> = e.allErrors.map {
                if (it is FieldError) return@map InvalidMsg(it.field, it.defaultMessage)
                InvalidMsg(it.objectName, it.defaultMessage)
            }
            return InvalidVo(e.errorCount, errors)
        }
    }

}

data class InvalidMsg(val name: String?, val message: String?)
