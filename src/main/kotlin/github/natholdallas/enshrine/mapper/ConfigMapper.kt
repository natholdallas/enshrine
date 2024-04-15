package github.natholdallas.enshrine.mapper

import github.natholdallas.enshrine.model.Config
import github.natholdallas.enshrine.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ConfigMapper : JpaRepository<Config, Long> {
    
    fun findByUser(user: User): Optional<Config>
    
}
