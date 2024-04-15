package github.natholdallas.enshrine.constraint

import github.natholdallas.enshrine.data.EmailPatchDto
import github.natholdallas.enshrine.data.RegisterDto
import github.natholdallas.enshrine.mapper.UserMapper
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [UniqueEmailImpl::class])
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class UniqueEmail(
    val message: String = "邮箱已被注册",

    val groups: Array<KClass<*>> = [],

    val payload: Array<KClass<out Payload>> = []
)

class UniqueEmailImpl(private val userMapper: UserMapper) : ConstraintValidator<UniqueEmail, Any> {

    override fun isValid(value: Any?, context: ConstraintValidatorContext?): Boolean = when (value) {
        is RegisterDto -> !userMapper.existsByEmail(value.email)
        is EmailPatchDto -> !userMapper.existsByEmail(value.email)
        else -> true
    }

}