package com.ducpv.movie.shared.data.database

import androidx.room.TypeConverter
import com.ducpv.movie.shared.data.database.entity.GenreEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * Created by pvduc9773 on 09/01/2023.
 */
class AppConverters {
    @TypeConverter
    fun storedStringToStrings(string: String?): List<String?>? {
        if (string.isNullOrEmpty()) return emptyList()
        val type = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun stringsToStoredString(strings: List<String?>?): String? {
        if (strings.isNullOrEmpty()) return null
        return Gson().toJson(strings)
    }

    @TypeConverter
    fun storedStringToGenreObjects(string: String?): List<GenreEntity?>? {
        if (string.isNullOrEmpty()) return emptyList()
        val type: Type = object : TypeToken<List<GenreEntity?>?>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun genreObjectsToStoredString(objects: List<GenreEntity>?): String? {
        if (objects.isNullOrEmpty()) return null
        return Gson().toJson(objects)
    }
}
