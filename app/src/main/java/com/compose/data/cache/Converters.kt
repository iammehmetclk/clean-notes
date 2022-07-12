package com.compose.data.cache

import androidx.room.TypeConverter
import com.compose.data.cache.model.NoteEntity
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun listToJson(list: List<NoteEntity>): String = Gson().toJson(list)

    @TypeConverter
    fun jsonToList(json: String) = Gson().fromJson(json, Array<NoteEntity>::class.java).toList()

}