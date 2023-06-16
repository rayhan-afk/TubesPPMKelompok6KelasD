package id.ac.unpas.tubesppmkelompok6kelasd.screens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import id.ac.unpas.tubesppmkelompok6kelasd.R

enum class Menu (
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
){
    HOME(R.string.home, Icons.Default.Home, "home"),
    DOSEN(R.string.dosen, Icons.Default.AccountBox, "dosen"),
    MAHASISWA(R.string.mahasiswa, Icons.Default.Person, "mahasiswa"),
    MATKUL(R.string.matkul, Icons.Default.AutoStories, "matkul");

    companion object {
        fun getTabFromResource(@StringRes resource: Int) : Menu
        {
            return when (resource) {
                R.string.home -> HOME
                R.string.dosen -> DOSEN
                R.string.mahasiswa -> MAHASISWA
                else -> MATKUL
            }
        }
    }
}