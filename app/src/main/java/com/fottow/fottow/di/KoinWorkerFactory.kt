package com.fottow.fottow.di

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.fottow.fottow.presentation.RetryUploadPhotoWorker
import org.koin.core.Koin

class KoinWorkerFactory(
    private val koin: Koin
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            RetryUploadPhotoWorker::class.qualifiedName ->
                RetryUploadPhotoWorker(appContext, workerParameters, koin.get(), koin.get())
            else -> null
        }
    }
}
