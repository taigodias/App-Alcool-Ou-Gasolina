package com.cecilia.appalcoolougasolina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cecilia.appalcoolougasolina.ui.theme.AppAlcoolOuGasolinaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppAlcoolOuGasolinaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun App() {
    var valorGasolina by remember { mutableStateOf("") }
    var valorAlcool by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var corResultado by remember { mutableStateOf(Color.White) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Álcool ou Gasolina?") },
                    navigationIcon = {
                        IconButton(onClick = { /* TODO: Handle navigation icon press */ }) {
                            Icon(Icons.Filled.Menu, contentDescription = null)
                        }
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color(0xFF3949AB),
                        titleContentColor = Color.White
                    )
                )
            }
        ) { paddingValues ->
            Column(
                Modifier
                    .background(color = Color(0xFF8C9EFF))
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pngegg),
                    contentDescription = null,
                    modifier = Modifier.wrapContentSize()
                )
                Text(
                    text = "Álcool ou Gasolina?",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                if (valorAlcool.isNotBlank() && valorGasolina.isNotBlank()) {
                    val ehGasolina = valorAlcool.toDouble() / valorGasolina.toDouble() > 0.7
                    resultado = if (ehGasolina) "Gasolina" else "Álcool"
                    corResultado = if (ehGasolina) Color.Red else Color.Green
                }

                if (resultado.isNotBlank()) {
                    Text(
                        text = resultado,
                        style = TextStyle(
                            color = corResultado,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                TextField(
                    value = valorGasolina,
                    onValueChange = { novoValor ->
                        valorGasolina = novoValor
                        resultado = ""
                    },
                    label = { Text(text = "Gasolina") },
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                TextField(
                    value = valorAlcool,
                    onValueChange = { novoValor ->
                        valorAlcool = novoValor
                        resultado = ""
                    },
                    label = { Text(text = "Álcool") },
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }


    @Preview
    @Composable
    fun AppPreview() {
        AppAlcoolOuGasolinaTheme {
            App()
        }
    }
}
