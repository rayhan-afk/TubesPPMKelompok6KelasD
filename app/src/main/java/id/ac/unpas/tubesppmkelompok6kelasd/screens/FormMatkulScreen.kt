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
import id.ac.unpas.tubesppmkelompok6kelasd.ui.theme.Green
import id.ac.unpas.tubesppmkelompok6kelasd.ui.theme.Purple700
import id.ac.unpas.tubesppmkelompok6kelasd.ui.theme.Red
import id.ac.unpas.tubesppmkelompok6kelasd.ui.theme.Teal200
import kotlinx.coroutines.launch

@Composable
fun FormMatkulScreen(navController : NavHostController, id: String? = null, modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<MatkulViewModel>()
    val kode = remember { mutableStateOf(TextFieldValue("")) }
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val sksOptions = listOf(99, 1, 2, 3, 4)
    val (sks, setSks) = remember { mutableStateOf(sksOptions[0]) }
    var expandDropdown1 by remember { mutableStateOf(false) }
    val praktikumOptions = listOf(99, 1, 0)
    val (praktikum, setPraktikum) = remember { mutableStateOf(praktikumOptions[0]) }
    var expandDropdown2 by remember { mutableStateOf(false) }
    val deskripsi = remember { mutableStateOf(TextFieldValue("")) }
    val scope = rememberCoroutineScope()
    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..." else "Simpan"
    val icon1 = if (expandDropdown1)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    val icon2 = if (expandDropdown2)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "Kode") },
            value = kode.value,
            onValueChange = {
                kode.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "IF231") }
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

        Box(
            modifier = Modifier.padding(top = 8.dp)
        ){
            OutlinedTextField(
                onValueChange = {},
                enabled = false,
                value = if (sks == 99) "--SKS--" else sks.toString(),
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .clickable { expandDropdown1 = !expandDropdown1 },
                trailingIcon = {
                    Icon(icon1, "dropdown icon")
                },
                textStyle = TextStyle(color = Color.Black)
            )

            DropdownMenu(
                expanded = expandDropdown1,
                onDismissRequest = { expandDropdown1 = false },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                sksOptions.forEach { label ->
                    DropdownMenuItem(
                        onClick = {
                            setSks(label)
                            expandDropdown1 = false
                        },
                        enabled = label != sksOptions[0])
                    {
                        Text(text = if (label == 99) "--SKS--" else label.toString())
                    }
                }
            }
        }

        Box(
            modifier = Modifier.padding(top = 8.dp)
        ){
            OutlinedTextField(
                onValueChange = {},
                enabled = false,
                value = when (praktikum) {
                    1 -> "Ya"
                    0 -> "Tidak"
                    else -> "--Praktikum--"
                },
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .clickable { expandDropdown2 = !expandDropdown2 },
                trailingIcon = {
                    Icon(icon2, "dropdown icon")
                },
                textStyle = TextStyle(color = Color.Black)
            )

            DropdownMenu(
                expanded = expandDropdown2,
                onDismissRequest = { expandDropdown2 = false },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                praktikumOptions.forEach { label ->
                    DropdownMenuItem(
                        onClick = {
                            setPraktikum(label)
                            expandDropdown2 = false
                        },
                        enabled = label != praktikumOptions[0])
                    {
                        Text(text = when (label) {
                            1 -> "Ya"
                            0 -> "Tidak"
                            else -> "--Praktikum--"
                        })
                    }
                }
            }
        }

        OutlinedTextField(
            label = { Text(text = "Deskripsi") },
            value = deskripsi.value,
            onValueChange = {
                deskripsi.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Matkul ini...") }
        )

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
                            kode.value.text,
                            nama.value.text,
                            sks.toInt(),
                            praktikum.toInt(),
                            deskripsi.value.text
                        )
                    }
                } else {
                    scope.launch {
                        viewModel.update(
                            id,
                            kode.value.text,
                            nama.value.text,
                            sks.toInt(),
                            praktikum.toInt(),
                            deskripsi.value.text
                        )
                    }
                }
                navController.navigate("matkul")
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
                kode.value = TextFieldValue("")
                nama.value = TextFieldValue("")
                setSks(sksOptions[0])
                setPraktikum(praktikumOptions[0])
                deskripsi.value = TextFieldValue("")
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
            viewModel.loadItem(id) { matkul ->
                matkul?.let {
                    kode.value = TextFieldValue(matkul.kode)
                    nama.value = TextFieldValue(matkul.nama)
                    setSks(matkul.sks)
                    setPraktikum(matkul.praktikum)
                    deskripsi.value = TextFieldValue(matkul.deskripsi)
                }
            }
        }
    }
}