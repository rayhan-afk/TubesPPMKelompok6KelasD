package id.ac.unpas.tubesppmkelompok6kelasd.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import id.ac.unpas.tubesppmkelompok6kelasd.ui.theme.Green
import id.ac.unpas.tubesppmkelompok6kelasd.ui.theme.Purple700
import id.ac.unpas.tubesppmkelompok6kelasd.ui.theme.Red
import id.ac.unpas.tubesppmkelompok6kelasd.ui.theme.Teal200
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

@Composable
fun FormMahasiswaScreen(navController : NavHostController, id: String? = null, modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<MahasiswaViewModel>()
    val npm = remember { mutableStateOf(TextFieldValue("")) }
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val tanggal_lahir = remember { mutableStateOf(TextFieldValue("")) }
    val jenisKelaminOptions = listOf("--Jenis Kelamin--", "Laki-laki" ,"Perempuan")
    val (jenis_kelamin, setJenisKelamin) = remember { mutableStateOf(jenisKelaminOptions[0]) }
    var expandDropdown by remember { mutableStateOf(false) }
    val tanggalDialogState = rememberMaterialDialogState()
    val scope = rememberCoroutineScope()
    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..." else "Simpan"
    val icon = if (expandDropdown)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "NPM") },
            value = npm.value,
            onValueChange = {
                npm.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "1930*****") },
        )
        OutlinedTextField(
            label = { Text(text = "Nama") },
            value = nama.value,
            onValueChange = {
                nama.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Nama") }
        )
        OutlinedTextField(
            label = { Text(text = "Tanggal Lahir") },
            value = tanggal_lahir.value,
            onValueChange = {
                tanggal_lahir.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .clickable {
                    tanggalDialogState.show()
                },
            placeholder = { Text(text = "yyyy-mm-dd") },
            enabled = false
        )

        Box(
            modifier = Modifier.padding(top = 8.dp)
        ){
            OutlinedTextField(
                onValueChange = {},
                enabled = false,
                value = jenis_kelamin,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .clickable { expandDropdown = !expandDropdown },
                trailingIcon = {
                    Icon(icon, "dropdown icon")
                },
                textStyle = TextStyle(color = Color.Black)
            )

            DropdownMenu(
                expanded = expandDropdown,
                onDismissRequest = { expandDropdown = false },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                jenisKelaminOptions.forEach { label ->
                    DropdownMenuItem(
                        onClick = {
                            setJenisKelamin(label)
                            expandDropdown = false
                        },
                        enabled = label != jenisKelaminOptions[0])
                    {
                        Text(text = label)
                    }
                }
            }
        }

        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Green,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Red,
            contentColor = Purple700
        )
        Row (modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                if (id == null) {
                    scope.launch {
                        viewModel.insert(
                            npm.value.text,
                            nama.value.text,
                            tanggal_lahir.value.text,
                            jenis_kelamin
                        )
                    }
                } else {
                    scope.launch {
                        viewModel.update(
                            id,
                            npm.value.text,
                            nama.value.text,
                            tanggal_lahir.value.text,
                            jenis_kelamin
                        )
                    }
                }
                navController.navigate("mahasiswa")
            }, colors = loginButtonColors) {
                Text(
                    text = buttonLabel,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Spacer(modifier = Modifier.padding(6.dp))
            Button(modifier = Modifier.weight(5f), onClick = {
                npm.value = TextFieldValue("")
                nama.value = TextFieldValue("")
                tanggal_lahir.value = TextFieldValue("")
                setJenisKelamin(jenisKelaminOptions[0])
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
    viewModel.isLoading.observe(LocalLifecycleOwner.current) {
        isLoading.value = it
    }

    if (id != null) {
        LaunchedEffect(scope) {
            viewModel.loadItem(id) { mahasiswa ->
                mahasiswa?.let {
                    npm.value = TextFieldValue(mahasiswa.npm)
                    nama.value = TextFieldValue(mahasiswa.nama)
                    tanggal_lahir.value = TextFieldValue(mahasiswa.tanggal_lahir)
                    setJenisKelamin(mahasiswa.jenis_kelamin)
                }
            }
        }
    }

    MaterialDialog(dialogState = tanggalDialogState, buttons = {
        positiveButton("OK")
        negativeButton("Batal")
    }) {
        datepicker {date ->
            tanggal_lahir.value = TextFieldValue(date.format(DateTimeFormatter.ISO_LOCAL_DATE))
        }
    }
}