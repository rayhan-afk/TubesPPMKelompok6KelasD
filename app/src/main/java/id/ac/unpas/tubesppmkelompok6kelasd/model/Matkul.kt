package id.ac.unpas.tubesppmkelompok6kelasd.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Matkul(
    @PrimaryKey val id : String,
    val kode : String,
    val nama : String,
    val sks : Int,
    val praktikum : Int,
    val deskripsi : String
)