package com.fottow.fottow.presentation

import android.content.Context
import android.net.Uri
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.fottow.fottow.FileResolver
import com.fottow.fottow.data.photo.UploadPhotoResponse
import com.fottow.fottow.domain.base.Success
import com.fottow.fottow.domain.photo.usecase.PhotoUseCase

class RetryUploadPhotoWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val updatePhotoUseCase: PhotoUseCase,
    private val fileResolver: FileResolver,
    ) : CoroutineWorker(
        context,
        workerParameters
    ) {

    override suspend fun doWork(): Result {
        val uriString = inputData.getString("failed_uri") ?: return Result.success()
        val uri = Uri.parse(uriString)

        var operationResult : com.fottow.fottow.domain.base.Result<UploadPhotoResponse, com.fottow.fottow.domain.base.Error>? = null
        uri?.let {
            val imagePath = fileResolver.getRealPathFromUri(it)
            if (imagePath != null) {
                operationResult = updatePhotoUseCase.uploadPhoto(imagePath)

            } else return Result.success()
        } ?: return Result.success()

        return  if (operationResult is Success) Result.success() else Result.retry()
    }

    companion object {
        fun enqueue(context: Context, failedUri: Uri) {
            val data = workDataOf("failed_uri" to failedUri.toString())

            val request = OneTimeWorkRequestBuilder<RetryUploadPhotoWorker>()
                .setInputData(data)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()

            WorkManager.getInstance(context).enqueue(request)
        }
    }
}