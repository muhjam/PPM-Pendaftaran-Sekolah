package id.ac.unpas.agenda.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.agenda.models.Register


@Database(entities = [Register::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun registerDao(): RegisterDao

    companion object {
        const val DATABASE_NAME = "pendaftaran-sekolah"
    }
}