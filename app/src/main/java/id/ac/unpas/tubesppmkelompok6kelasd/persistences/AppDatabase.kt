package id.ac.unpas.tubesppmkelompok6kelasd.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.tubesppmkelompok6kelasd.model.Dosen
import id.ac.unpas.tubesppmkelompok6kelasd.model.Mahasiswa
import id.ac.unpas.tubesppmkelompok6kelasd.model.Matkul

@Database(entities = [Matkul::class, Dosen::class, Mahasiswa::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    //    dosen
    abstract fun dosenDao(): DosenDao
    //    mahasiswa
    abstract fun mahasiswaDao(): MahasiswaDao
    //    matkul
    abstract fun matkulDao(): MatkulDao

}