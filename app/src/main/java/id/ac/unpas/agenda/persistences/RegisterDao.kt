package id.ac.unpas.agenda.persistences

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ac.unpas.agenda.models.Register

@Dao
interface RegisterDao {

    @Query("select * from register")
    fun loadAll(): LiveData<List<Register>>

    @Query("select * from register where id = :id")
    fun load(id: String): LiveData<Register>

    @Query("select * from register where id = :id")
    suspend fun getById(id: String): Register?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg items: Register)

    @Query("delete from register where id = :id")
    suspend fun delete(id: String)

    @Delete
    suspend fun delete(item: Register)
}