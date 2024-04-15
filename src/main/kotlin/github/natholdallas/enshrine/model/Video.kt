package github.natholdallas.enshrine.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@JsonIgnoreProperties("user")
data class Video(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(targetEntity = User::class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(nullable = false)
    var user: User = User(),

    @ManyToOne(targetEntity = Star::class)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(nullable = true)
    var star: Star? = null,

    @Column(nullable = false)
    var title: String = "",

    @Column(nullable = true)
    var videoUrl: String? = null,

    @Column(nullable = true)
    var description: String? = null,

    @Column(nullable = false)
    var createTime: Long = System.currentTimeMillis(),

    @Column(nullable = false)
    var latestUpdate: Long = System.currentTimeMillis()
)
