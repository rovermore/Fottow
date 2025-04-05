package com.fottow.fottow.data.user.local

import android.content.Context
import com.fottow.fottow.domain.base.Failure
import com.fottow.fottow.domain.base.Success
import com.fottow.fottow.domain.base.Error
import kotlinx.coroutines.runBlocking
import com.fottow.fottow.domain.base.Result
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "FOTTOW_DATA_STORE"
)


class FottowDataStore(context: Context) {

    private val dataStore = context.dataStore

    @Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
    inline fun <reified T> save(key: String, value: T): Result<Boolean, Error> {
        return try {
            val keyString = getKey<T>(key)
            runBlocking {
                dataStore.edit { data ->
                    data[keyString] = value
                }
            }
            Success(true)
        } catch (e: InterruptedException) {
            Failure(Error.UncompletedOperation(e.message ?: "Error saving data"))
        } catch (e: ClassNotFoundException) {
            Failure(Error.UncompletedOperation(e.message ?: "Error saving data"))
        }
    }

    @Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
    inline fun <reified T> read(key: String): Result<T, Error> {
        return try {
            val keyString = getKey<T>(key)
            val value = runBlocking {
                dataStore.data.map { data ->
                    data[keyString]
                }.firstOrNull()
            }
            if (value == null)
                Failure(Error.UncompletedOperation("Data couldn't be read"))
            else
                Success(value)
        } catch (e: InterruptedException) {
            Failure(Error.UncompletedOperation(e.message ?: "Error reading data"))
        } catch (e: ClassNotFoundException) {
            Failure(Error.UncompletedOperation(e.message ?: "Error reading data"))
        }
    }

    @Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
    inline fun <reified T> update(key: String, value: T): Result<Boolean, Error> {
        return try {
            val keyString = getKey<T>(key)
            runBlocking {
                dataStore.edit { data ->
                    data[keyString] = value
                }
            }
            Success(true)
        } catch (e: InterruptedException) {
            Failure(Error.UncompletedOperation(e.message ?: "Error saving data"))
        } catch (e: ClassNotFoundException) {
            Failure(Error.UncompletedOperation(e.message ?: "Error saving data"))
        }
    }

    @Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
    inline fun <reified T> delete(key: String): Result<Boolean, Error> {
        return try {
            val keyString = getKey<T>(key)
            runBlocking {
                dataStore.edit { data ->
                    data.remove(keyString)
                }
            }
            Success(true)
        } catch (e: InterruptedException) {
            Failure(Error.UncompletedOperation(e.message ?: "Error deleting data"))
        } catch (e: ClassNotFoundException) {
            Failure(Error.UncompletedOperation(e.message ?: "Error deleting data"))
        }
    }

    @Throws(ClassNotFoundException::class)
    inline fun <reified T> getKey(key: String): Preferences.Key<T> {
        return when (T::class) {
            String::class -> stringPreferencesKey(key) as Preferences.Key<T>
            Boolean::class -> booleanPreferencesKey(key) as Preferences.Key<T>
            Float::class -> floatPreferencesKey(key) as Preferences.Key<T>
            Long::class -> longPreferencesKey(key) as Preferences.Key<T>
            else -> throw ClassNotFoundException()
        }
    }

}