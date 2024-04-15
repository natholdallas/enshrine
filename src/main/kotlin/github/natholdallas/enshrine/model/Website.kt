package github.natholdallas.enshrine.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@JsonIgnoreProperties("user")
data class Website(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(targetEntity = User::class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(nullable = false)
    var user: User = User(),

    @Column(nullable = false)
    var title: String = "",

    @Column(nullable = true)
    var siteUrl: String? = null,

    @Column(nullable = true)
    var description: String? = null,

    @Column(nullable = false)
    var createTime: Long = System.currentTimeMillis(),

    @Column(nullable = false)
    var latestUpdate: Long = System.currentTimeMillis()
)
