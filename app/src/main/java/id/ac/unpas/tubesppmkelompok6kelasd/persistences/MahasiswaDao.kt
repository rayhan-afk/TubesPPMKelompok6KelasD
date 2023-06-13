package id.ac.unpas.tubesppmkelompok6kelasd.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.tubesppmkelompok6kelasd.model.Mahasiswa

@Dao
interface MahasiswaDao {
    @Query("SELECT * FROM Mahasiswa ORDER BY npm DESC")
    fun loadAll(): LiveData<List<Mahasiswa>>
    @Query("SELECT * FROM Mahasiswa ORDER BY npm DESC")
    suspend fun getList(): List<Mahasiswa>
    @Query("SELECT * FROM Mahasiswa WHERE id = :id")
    suspend fun find(id: String): Mahasiswa?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: Mahasiswa)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Mahasiswa>)
    @Delete
    fun delete(item: Mahasiswa)
    @Query("DELETE FROM Mahasiswa WHERE id = :id")
    suspend fun delete(id: String)
}