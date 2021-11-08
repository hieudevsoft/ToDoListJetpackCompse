package com.devapp.to_docompose.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.devapp.to_docompose.data.models.Priority
import com.devapp.to_docompose.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore:DataStore<Preferences> by preferencesDataStore(name = Constants.PREFERENCE_NAME)

@ViewModelScoped
class DataStoreRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private object PreferenceKeys{
        val sortState = stringPreferencesKey(Constants.PREFERENCE_KEY_SORT_STATE)
    }

    private val dataStore = context.dataStore

    suspend fun persisSortState(priority: Priority){
        dataStore.edit {
            it[PreferenceKeys.sortState] = priority.name
        }
    }

    fun readSortState():Flow<String>
    {
        return dataStore.data
            .catch { e->
                if(e is IOException){
                    emit(emptyPreferences())
                } else throw e
            }.map {
                    pre->
                val sortState = pre[PreferenceKeys.sortState]?:Priority.NONE.name
                sortState
            }
    }
}