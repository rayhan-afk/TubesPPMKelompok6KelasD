package id.ac.unpas.tubesppmkelompok6kelasd.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.message
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.vanpra.composematerialdialogs.title
import id.ac.unpas.tubesppmkelompok6kelasd.model.Matkul

@Composable
fun MatkulItem(item: Matkul, navController:
NavHostController, onDelete: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val subMenus = listOf("Edit", "Delete")
    val confirmationDialogState = rememberMaterialDialogState()
    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            elevation = 4.dp
        ) {
            Column {
                Row(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()) {
                    Column(modifier = Modifier.weight(2f)) {
                        Text(text = "Kode", fontSize = 14.sp)
                        Text(item.kode, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Nama", fontSize = 14.sp)
                        Text(text = item.nama, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "SKS", fontSize = 14.sp)
                        Text(text = "${item.sks}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(2f), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Praktikum", fontSize = 14.sp)
                        Icon(
                            imageVector = if (item.praktikum == 1) Icons.Default.Check else Icons.Default.Clear,
                            contentDescription = "Status Praktikum",
                            tint = if (item.praktikum == 1) Color.Green else Color.Red,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Icon(
                        Icons.Default.MoreVert,
                        modifier = Modifier.clickable {expanded = true },
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier.weight(2f)) {
                        Text(text = "Deskripsi", fontSize = 14.sp)
                        Text(item.deskripsi, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(x = (-50).dp, y = (-125).dp)
        ) {
            subMenus.forEachIndexed { _, s ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    when (s) {
                        "Edit" -> {
                            navController.navigate("edit-matkul/${item.id}")
                        }
                        "Delete" -> {
                            confirmationDialogState.show()
                        }
                    }
                }) {
                    Text(text = s)
                }
            }
        }
    }
    Divider(modifier = Modifier.fillMaxWidth())
    MaterialDialog(dialogState = confirmationDialogState,
        buttons = {
            positiveButton("Ya", onClick = {
                onDelete(item.id)
            })
            negativeButton("Tidak")
        }) {
        title(text = "Konfirmasi")
        message(text = "Apakah anda yakin ingin menghapus data?")
    }
}