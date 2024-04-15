package github.natholdallas.enshrine.data

import github.natholdallas.enshrine.common.JRegexp
import github.natholdallas.enshrine.constraint.UniqueEmail
import github.natholdallas.enshrine.constraint.Verification
import jakarta.validation.constraints.*
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.Range
import org.hibernate.validator.constraints.URL
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

@Verification
@UniqueEmail
data class RegisterDto(
    @field:[NotNull Email(regexp = JRegexp.EMAIL)]
    val email: String?,
    @field:[NotNull]
    val code: String?,
    @field:[NotNull Pattern(regexp = JRegexp.PASSWORD)]
    val password: String?
)

data class LoginDto(
    @field:[NotNull Email(regexp = JRegexp.EMAIL)]
    val email: String?,
    @field:[NotNull Pattern(regexp = JRegexp.PASSWORD)]
    val password: String?
)

data class CodeCreationDto(
    @field:[NotNull Email(regexp = JRegexp.EMAIL)]
    val email: String?
)

data class UserUpdateDto(
    @field:[Length(max = 20)]
    val nickname: String?,
    @field:[Length(max = 200) URL]
    val avatarUrl: String?
)

data class ConfigUpdateDto(
    @field:[NotNull]
    val editMode: Boolean?,
    @field:[NotNull]
    val darkMode: Boolean?,
    @field:[NotNull]
    val menuReduced: Boolean?,
    @field:[NotNull]
    val avatarCircle: Boolean?
)

@Verification
@UniqueEmail
data class EmailPatchDto(
    @field:[NotNull]
    val code: String?,
    @field:[NotNull Email(regexp = JRegexp.EMAIL)]
    val email: String?
)

@Verification
data class PasswordPatchDto(
    @field:[NotNull]
    val code: String?,
    @field:[NotNull Pattern(regexp = JRegexp.PASSWORD)]
    val password: String?
)

data class StarCreationDto(
    @field:[NotNull NotBlank NotEmpty Length(max = 100)]
    val name: String?,
    @field:[Length(max = 200) URL]
    val imageUrl: String?,
    @field:[Length(max = 200)]
    val description: String?
)

data class StarUpdateDto(
    @field:[NotNull NotBlank NotEmpty Length(max = 100)]
    val name: String?,
    @field:[Length(max = 200) URL]
    val imageUrl: String?,
    @field:[Length(max = 200)]
    val description: String?
)

data class VideoCreationDto(
    @field:[NotNull NotBlank NotEmpty Length(max = 100)]
    val title: String?,
    @field:[Length(max = 200) URL]
    val videoUrl: String?,
    @field:[Length(max = 200)]
    val description: String?
)

data class VideoUpdateDto(
    @field:[NotNull NotBlank NotEmpty Length(max = 100)]
    val title: String?,
    @field:[Length(max = 200) URL]
    val videoUrl: String?,
    @field:[Length(max = 200)]
    val description: String?
)

data class WebsiteCreationDto(
    @field:[NotNull NotBlank NotEmpty Length(max = 100)]
    val title: String?,
    @field:[Length(max = 200) URL]
    val siteUrl: String?,
    @field:[Length(max = 200)]
    val description: String?
)

data class WebsiteUpdateDto(
    @field:[NotNull NotBlank NotEmpty Length(max = 100)]
    val title: String?,
    @field:[Length(max = 200) URL]
    val siteUrl: String?,
    @field:[Length(max = 200)]
    val description: String?
)

data class Pagination(
    @field:[Range(max = 100_000_000)]
    val page: Int = 0,
    @field:[Range(min = 10, max = 200)]
    val size: Int = 20
) : PageRequest(page, size, Sort.unsorted())
