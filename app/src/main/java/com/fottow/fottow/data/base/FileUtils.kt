package com.fottow.fottow.data.base

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import androidx.core.graphics.scale

fun File.compressImage(): File {
    val maxSizeBytes = 6 * 1024 * 1024 // 6MB in bytes

    // If the file is smaller than 6MB, return the original
    if (this.length() <= maxSizeBytes) {
        return this
    }

    try {
        // Create temporary file for the compressed image
        val compressedFile =
            File(this.parent, "${this.nameWithoutExtension}_compressed.${this.extension}")

        // Decode the original image
        val originalBitmap = BitmapFactory.decodeFile(this.absolutePath)
            ?: return this // If it can't be decoded, return original

        var quality = 90 // Initial quality
        var compressedSize: Long

        do {
            // Compress the image with the current quality
            FileOutputStream(compressedFile).use { fos ->
                originalBitmap.compress(Bitmap.CompressFormat.JPEG, quality, fos)
            }

            compressedSize = compressedFile.length()
            quality -= 10 // Reduce quality for the next iteration

        } while (compressedSize > maxSizeBytes && quality > 10)

        // If after compression it's still too large, resize
        if (compressedSize > maxSizeBytes) {
            val scaleFactor = kotlin.math.sqrt(maxSizeBytes.toDouble() / compressedSize.toDouble())
            val newWidth = (originalBitmap.width * scaleFactor).toInt()
            val newHeight = (originalBitmap.height * scaleFactor).toInt()

            val resizedBitmap = originalBitmap.scale(newWidth, newHeight)

            FileOutputStream(compressedFile).use { fos ->
                resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos)
            }

            resizedBitmap.recycle()
        }

        originalBitmap.recycle()

        return if (compressedFile.exists() && compressedFile.length() < this.length()) {
            compressedFile
        } else {
            this // If compression failed, return original
        }

    } catch (e: IOException) {
        e.printStackTrace()
        return this // In case of error, return original file
    } catch (e: OutOfMemoryError) {
        e.printStackTrace()
        return this // In case of out of memory error, return original file
    }
}