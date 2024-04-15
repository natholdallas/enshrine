package github.natholdallas.enshrine.mapper

import github.natholdallas.enshrine.model.Upload
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
@Transactional
interface UploadMapper : JpaRepository<Upload, String>