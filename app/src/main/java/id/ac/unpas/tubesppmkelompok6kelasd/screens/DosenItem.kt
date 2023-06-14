package id.ac.unpas.tubesppmkelompok6kelasd.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
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
import id.ac.unpas.tubesppmkelompok6kelasd.model.Dosen

@Composable
fun DosenItem(item: Dosen, navController: NavHostController, onDelete: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val subMenus = listOf("Edit", "Delete")
    val confirmationDialogState = rememberMaterialDialogState()

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()) {
            Column(modifier = Modifier.weight(4f)) {
                Row {
                    Column {
                        Text(text = "NIDN", fontSize = 14.sp)
                        Text(item.nidn, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Column {
                        Text(text = "Pendidikan", fontSize = 14.sp)
                        Text(text = item.pendidikan, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
            Column(modifier = Modifier.weight(8f)) {
                Text(text = "Nama", fontSize = 14.sp)
                Text("${item.gelar_depan} ${item.nama} ${item.gelar_belakang}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Icon(
                Icons.Default.MoreVert,
                modifier = Modifier.clickable {expanded = true },
                contentDescription = null,
                tint = Color.Unspecified
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(x = (-50).dp, y = (-75).dp)
        ) {
            subMenus.forEachIndexed { _, s ->
                DropdownMenuItem(onClick = {
                    expanded = false

                    when (s) {
                        "Edit" -> {
                            navController.navigate("edit-dosen/${item.id}")
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