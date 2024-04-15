package github.natholdallas.enshrine.mapper

import cn.dev33.satoken.stp.StpUtil
import github.natholdallas.enshrine.context
import github.natholdallas.enshrine.model.User
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@Transactional
interface UserMapper : JpaRepository<User, String> {

    fun existsByEmail(email: String?): Boolean

    fun findByEmailAndPassword(email: String?, password: String?): Optional<User>

}

val userNow get() = context.getBean(UserMapper::class.java).findById(StpUtil.getLoginIdAsString()).get()
