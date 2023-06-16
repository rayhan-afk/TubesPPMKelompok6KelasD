package id.ac.unpas.tubesppmkelompok6kelasd.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.unpas.tubesppmkelompok6kelasd.ui.theme.Blue
import id.ac.unpas.tubesppmkelompok6kelasd.ui.theme.Green
import id.ac.unpas.tubesppmkelompok6kelasd.ui.theme.White

@Composable
fun HomeScreen() {
    val members = listOf(
        "193040044 - Rayhan Abduhuda",
        "193040045 - Dian Nuryana",
        "193040051 - Usep Syaiful Hidayat",
        "193040065 - Rizal Muhammad Farabi",
        "193040109 - Jamil Yohanes Akbar Naiborhu"
    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            elevation = 8.dp,
        ) {
            Box(
                modifier = Modifier
                    .background(Blue) // Memberikan warna latar belakang pada Box
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "KELOMPOK 6",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = White // Memberikan warna teks putih pada Text
                )
            }
        }
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            elevation = 8.dp
        ) {
            Box(
                modifier = Modifier
                    .background(Green) // Memberikan warna latar belakang pada Box
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Perkuliahan",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    color = White // Memberikan warna teks putih pada Text
                )
            }
        }
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Anggota Kelompok",
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                for (member in members) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Member Icon",
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Box(
                            modifier = Modifier
                                .padding(vertical = 2.dp, horizontal = 4.dp)
                        ) {
                            Text(
                                text = member,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
