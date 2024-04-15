package github.natholdallas.enshrine.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@JsonIgnoreProperties("user")
data class Config(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @OneToOne(targetEntity = User::class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(nullable = false)
    var user: User = User(),

    @Column
    var editMode: Boolean = false,

    @Column
    var darkMode: Boolean = false,

    @Column
    var menuReduced: Boolean = false,

    @Column
    var avatarCircle: Boolean = false
)