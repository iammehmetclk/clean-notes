package com.compose.data.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.compose.data.cache.dao.NoteDao
import com.compose.data.cache.model.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var instance: ApplicationDatabase? = null
        fun getApplicationDatabase(context: Context): ApplicationDatabase {
            instance?.let { return it }
            synchronized(this) {
                val temp: ApplicationDatabase = Room
                    .databaseBuilder(
                        context.applicationContext,
                        ApplicationDatabase::class.java, "application_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            GlobalScope.launch(Dispatchers.IO) {
                                instance?.noteDao()?.insertNote(
                                    NoteEntity(0, "First Note", "This is content of the first note.")
                                )
                            }
                            super.onCreate(db)
                        }
                    })
                    .build()
                instance = temp
            }
            return instance!!
        }
    }

}