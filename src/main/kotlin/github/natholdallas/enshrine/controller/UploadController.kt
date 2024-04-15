package github.natholdallas.enshrine.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import github.natholdallas.enshrine.common.Param
import github.natholdallas.enshrine.common.Post
import github.natholdallas.enshrine.common.RestCtl
import github.natholdallas.enshrine.mapper.UploadMapper
import github.natholdallas.enshrine.model.Upload
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.multipart.MultipartFile
import java.net.Inet4Address
import kotlin.io.path.Path

@SaCheckLogin
@RestCtl
@RequestMapping("/upload")
class UploadController(private val uploadMapper: UploadMapper) {

    @Value("\${upload.images}")
    private lateinit var imagePath: String

    @Value("\${server.port}")
    private lateinit var port: String

    @Post
    fun uploadImage(@Param file: MultipartFile): ResponseEntity<Upload> {
        val upload = Upload()
        uploadMapper.save(upload)
        val suffix = file.originalFilename?.let { it.substring(it.lastIndexOf('.')) }
        val path = "${imagePath}/${upload.id}${suffix}"
        val url = "http://${Inet4Address.getLocalHost().hostAddress}:${port}/${upload.id}${suffix}"
        upload.size = file.size
        upload.path = path
        upload.url = url
        file.transferTo(Path(path))
        uploadMapper.save(upload)
        return ResponseEntity(upload, OK)
    }

}
