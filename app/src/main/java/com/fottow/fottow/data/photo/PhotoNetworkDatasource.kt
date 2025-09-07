package com.fottow.fottow.data.photo

import com.fottow.fottow.data.base.APIError
import com.fottow.fottow.data.base.CallExecutor
import com.fottow.fottow.data.base.FottowKtorClient
import com.fottow.fottow.data.base.FottowURL
import com.fottow.fottow.data.base.compressImage
import com.fottow.fottow.data.user.register.RegisterRequest
import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Result
import com.fottow.fottow.domain.photo.model.FottowImage
import com.fottow.fottow.domain.photo.model.FottowImageResponse
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.http.headers
import kotlinx.serialization.json.Json
import java.io.File

class PhotoNetworkDatasource(
    private val client: FottowKtorClient,
    private val callExecutor: CallExecutor
) {
    suspend fun uploadPhoto(imagePath: String): Result<UploadPhotoResponse, APIError> {
        val file = File(imagePath).compressImage()
        val call : suspend () -> HttpResponse = {
            client.getClient().submitFormWithBinaryData(
                url = FottowURL.IMAGES_UPLOAD,
                formData = formData {
                    append("file", file.readBytes(), Headers.build {
                        append(HttpHeaders.Accept, "application/json")
                        append(HttpHeaders.ContentType, "multipart/form-data")
                        append(HttpHeaders.ContentDisposition, "filename=${file.name}")
                    })
                }
            )
        }
        return callExecutor.executeKtorCall<UploadPhotoResponse>(call)
    }

    suspend fun getImages(): Result<FottowImageResponse, APIError> {
        val call : suspend () -> HttpResponse = {
            client.getClient().request(FottowURL.IMAGES) {
                method = HttpMethod.Get
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Accept, "application/json")
                    append(HttpHeaders.ContentType, "application/json")
                }
            }
        }
        return callExecutor.executeKtorCall<FottowImageResponse>(call)
    }

    suspend fun uploadIdentificationSelfie(imagePath: String): Result<UploadPhotoResponse, APIError> {
        val file = File(imagePath).compressImage()
        val call : suspend () -> HttpResponse = {
            client.getClient().submitFormWithBinaryData(
                url = FottowURL.IDENTIFICATION_SELFIE_UPLOAD,
                formData = formData {
                    append("file", file.readBytes(), Headers.build {
                        append(HttpHeaders.Accept, "application/json")
                        append(HttpHeaders.ContentType, "multipart/form-data")
                        append(HttpHeaders.ContentDisposition, "filename=${file.name}")
                    })
                }
            )
        }
        return callExecutor.executeKtorCall<UploadPhotoResponse>(call)
    }
}