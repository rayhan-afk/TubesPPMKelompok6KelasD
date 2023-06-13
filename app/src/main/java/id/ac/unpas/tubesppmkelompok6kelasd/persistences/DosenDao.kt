package id.ac.unpas.tubesppmkelompok6kelasd.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.tubesppmkelompok6kelasd.model.Dosen

@Dao
interface DosenDao {
    @Query("SELECT * FROM Dosen ORDER BY nidn DESC")
    fun loadAll(): LiveData<List<Dosen>>
    @Query("SELECT * FROM Dosen ORDER BY nidn DESC")
    suspend fun getList(): List<Dosen>
    @Query("SELECT * FROM Dosen WHERE id = :id")
    suspend fun find(id: String): Dosen?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: Dosen)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Dosen>)
    @Delete
    fun delete(item: Dosen)
    @Query("DELETE FROM Dosen WHERE id = :id")
    suspend fun delete(id: String)
}