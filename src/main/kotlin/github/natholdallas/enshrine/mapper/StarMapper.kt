package github.natholdallas.enshrine.mapper

import github.natholdallas.enshrine.model.Star
import github.natholdallas.enshrine.model.User
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@Transactional
interface StarMapper : JpaRepository<Star, Long> {

    fun findAllByUser(user: User, pageable: Pageable): Page<Star>

    fun findByIdAndUser(id: Long, user: User): Optional<Star>

    fun deleteByIdAndUser(id: Long, user: User)

}