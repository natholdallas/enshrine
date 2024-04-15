package github.natholdallas.enshrine.mapper

import github.natholdallas.enshrine.model.Code
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CodeMapper : JpaRepository<Code, String> {

    fun existsByIdAndEmail(id: String?, email: String?): Boolean

}