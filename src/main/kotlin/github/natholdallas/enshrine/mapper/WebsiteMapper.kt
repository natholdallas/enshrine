package github.natholdallas.enshrine.mapper

import github.natholdallas.enshrine.model.User
import github.natholdallas.enshrine.model.Website
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@Transactional
interface WebsiteMapper : JpaRepository<Website, Long> {

    fun findAllByUser(user: User, pageable: Pageable): Page<Website>

    fun findByIdAndUser(id: Long, user: User): Optional<Website>

    fun deleteByIdAndUser(id: Long, user: User)

}