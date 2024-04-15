package github.natholdallas.enshrine.model

import jakarta.persistence.*

@Entity
data class Code(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @Column(nullable = false)
    val email: String = "",

    @Column(nullable = false)
    val timestamp: Long = System.currentTimeMillis()
)
