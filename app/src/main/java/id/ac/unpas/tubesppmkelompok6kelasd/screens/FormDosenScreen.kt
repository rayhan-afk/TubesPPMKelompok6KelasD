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
import id.ac.unpas.tubesppmkelompok6kelasd.ui.theme.Purple700
import id.ac.unpas.tubesppmkelompok6kelasd.ui.theme.Teal200
import kotlinx.coroutines.launch

@Composable
fun FormDosenScreen(navController : NavHostController, id: String? = null, modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<DosenViewModel>()
    val nidn = remember { mutableStateOf(TextFieldValue("")) }
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val gelar_depan = remember { mutableStateOf(TextFieldValue("")) }
    val gelar_belakang = remember { mutableStateOf(TextFieldValue("")) }
    val pendidikanOptions = listOf("--Pilih--", "S2" ,"S3")
    val (pendidikan, setPendidikan) = remember { mutableStateOf(pendidikanOptions[0]) }
    var expandDropdown by remember { mutableStateOf(false) }
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
            label = { Text(text = "NIDN") },
            value = nidn.value,
            onValueChange = {
                nidn.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "2") }
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
            placeholder = { Text(text = "IF231") }
        )
        OutlinedTextField(
            label = { Text(text = "Gelar Depan") },
            value = gelar_depan.value,
            onValueChange = {
                gelar_depan.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "XXXXX") }
        )


        OutlinedTextField(
            label = { Text(text = "Gelar Belakag") },
            value = gelar_belakang.value,
            onValueChange = {
                gelar_belakang.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "XXXXX") }
        )

        Box(
            modifier = Modifier.padding(top = 8.dp)
        ){
            OutlinedTextField(
                onValueChange = {},
                enabled = false,
                value = pendidikan,
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
                pendidikanOptions.forEach { label ->
                    DropdownMenuItem(
                        onClick = {
                            setPendidikan(label)
                            expandDropdown = false
                        },
                        enabled = label != pendidikanOptions[0])
                    {
                        Text(text = label)
                    }
                }
            }
        }

        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                if (id == null) {
                    scope.launch {
                        viewModel.insert(
                            nidn.value.text,
                            nama.value.text,
                            gelar_depan.value.text,
                            gelar_belakang.value.text,
                            pendidikan
                        )
                    }
                } else {
                    scope.launch {
                        viewModel.update(
                            id,
                            nidn.value.text,
                            nama.value.text,
                            gelar_depan.value.text,
                            gelar_belakang.value.text,
                            pendidikan
                        )
                    }
                }
                navController.navigate("dosen")
            }, colors = loginButtonColors) {
                Text(
                    text = buttonLabel,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                nidn.value = TextFieldValue("")
                nama.value = TextFieldValue("")
                gelar_depan.value = TextFieldValue("")
                gelar_belakang.value = TextFieldValue("")
                setPendidikan(pendidikanOptions[0])
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
            viewModel.loadItem(id) { dosen ->
                dosen?.let {
                    nidn.value = TextFieldValue(dosen.nidn)
                    nama.value = TextFieldValue(dosen.nama)
                    gelar_depan.value = TextFieldValue(dosen.gelar_depan)
                    gelar_belakang.value = TextFieldValue(dosen.gelar_belakang)
                    setPendidikan(dosen.pendidikan)
                }
            }
        }
    }
}