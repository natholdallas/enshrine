package github.natholdallas.enshrine.constraint

import github.natholdallas.enshrine.data.EmailPatchDto
import github.natholdallas.enshrine.data.PasswordPatchDto
import github.natholdallas.enshrine.data.RegisterDto
import github.natholdallas.enshrine.mapper.CodeMapper
import github.natholdallas.enshrine.mapper.userNow
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [VerificationImpl::class])
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Verification(
    val message: String = "验证码与邮箱不匹配",

    val groups: Array<KClass<*>> = [],

    val payload: Array<KClass<out Payload>> = []
)

class VerificationImpl(private val codeMapper: CodeMapper) : ConstraintValidator<Verification, Any> {

    override fun isValid(value: Any?, context: ConstraintValidatorContext?): Boolean = when (value) {
        is RegisterDto -> codeMapper.existsByIdAndEmail(value.code, value.email)
        is EmailPatchDto -> codeMapper.existsByIdAndEmail(value.code, value.email)
        is PasswordPatchDto -> codeMapper.existsByIdAndEmail(value.code, userNow.email)
        else -> true
    }

}
