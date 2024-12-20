package com.fottow.fottow.data.photo

import com.fottow.fottow.data.base.APIError
import com.fottow.fottow.data.base.CallExecutor
import com.fottow.fottow.data.base.FottowKtorClient
import com.fottow.fottow.domain.base.Result
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.statement.HttpResponse
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import java.io.File

class PhotoNetworkDatasource(
    private val client: FottowKtorClient,
    private val callExecutor: CallExecutor
) {
    suspend fun uploadPhoto(imagePath: String): Result<UploadPhotoResponse, APIError> {
        val file = File(imagePath)
        val call : suspend () -> HttpResponse = {
            client.getClient().submitFormWithBinaryData(
                url = "www.test.url.com",
                formData = formData {
                    append("image", file.readBytes(), Headers.build {
                        append(HttpHeaders.ContentType, "image/*")
                        append(HttpHeaders.ContentDisposition, "filename=${file.name}")
                    })
                }
            )
        }
        return callExecutor.executeKtorCall<UploadPhotoResponse>(call)
    }
}