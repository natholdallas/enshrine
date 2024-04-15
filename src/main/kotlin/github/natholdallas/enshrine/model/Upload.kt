package github.natholdallas.enshrine.model

import jakarta.persistence.*

@Entity
data class Upload(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @Column(nullable = false)
    var path: String = "",

    @Column(nullable = false)
    var url: String = "",

    @Column(nullable = false)
    var size: Long = 0,

    @Column(nullable = false)
    var timestamp: Long = System.currentTimeMillis()
)
