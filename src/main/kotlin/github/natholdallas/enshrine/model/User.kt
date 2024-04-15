package github.natholdallas.enshrine.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*

@Entity
@JsonIgnoreProperties("password")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @Column(nullable = false)
    var email: String = "",

    @Column(nullable = false)
    var password: String = "",

    @Column(nullable = true)
    var nickname: String? = null,

    @Column(nullable = true)
    var avatarUrl: String? = null,

    @Column(nullable = false)
    var createTime: Long = System.currentTimeMillis(),

    @Column(nullable = false)
    var latestUpdate: Long = System.currentTimeMillis()
)