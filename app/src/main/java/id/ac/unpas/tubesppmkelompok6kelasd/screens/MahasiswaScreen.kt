package id.ac.unpas.tubesppmkelompok6kelasd.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import id.ac.unpas.tubesppmkelompok6kelasd.model.Mahasiswa
import id.ac.unpas.tubesppmkelompok6kelasd.ui.theme.Blue
import id.ac.unpas.tubesppmkelompok6kelasd.ui.theme.White
import kotlinx.coroutines.launch

@Composable
fun MahasiswaScreen(navController : NavHostController, modifier: Modifier = Modifier, snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val viewModel = hiltViewModel<MahasiswaViewModel>()
    val items: List<Mahasiswa> by viewModel.list.observeAsState(initial = listOf())

    Column(modifier = modifier.fillMaxWidth()) {
        Button(modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(),
            onClick = {
                navController.navigate("tambah-mahasiswa")
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Blue, // Warna latar belakang biru
                contentColor = White // Warna teks putih
            )) {
            Text(text = "Tambah Data Mahasiswa")
        }

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                MahasiswaItem(item = item, navController = navController ) {
                    scope.launch {
                        viewModel.delete(it)    //Delete
                    }
                }
            })
        }
    }
    LaunchedEffect(scope) {
        viewModel.loadItems()
    }
    viewModel.success.observe(LocalLifecycleOwner.current) {
        if (it) {
            scope.launch {
                viewModel.loadItems()
            }
        }
    }

    viewModel.toast.observe(LocalLifecycleOwner.current) {
        scope.launch {
            snackbarHostState.showSnackbar(it, actionLabel = "Tutup", duration = SnackbarDuration.Long)
        }
    }
}