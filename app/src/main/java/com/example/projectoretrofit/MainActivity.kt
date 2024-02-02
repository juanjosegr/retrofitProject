package com.example.projectoretrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.projectoretrofit.data.RetrofitSerivceFactory
import com.example.projectoretrofit.data.RetrofitService
import com.example.projectoretrofit.ui.theme.ProjectoRetrofitTheme
import kotlinx.coroutines.launch
import coil.compose.rememberImagePainter


class MainActivity : ComponentActivity() {
    private var catList by mutableStateOf<List<RetrofitService.CatResponse>>(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val service = RetrofitSerivceFactory.makeRetrofitService()

        lifecycleScope.launch {
            catList = service.listOfCats()
        }


        setContent {
            ProjectoRetrofitTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android", catList)
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    gatos: List<RetrofitService.CatResponse>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(
                rememberScrollState(30000),
                enabled = true,
                reverseScrolling = true
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        for (gato in gatos) {
            /*Column {
                Text(text = "ID: ${gato.id}")
                Text(text = "URL: ${gato.url}")
                Text(text = "Width: ${gato.width}")
                Text(text = "Height: ${gato.height}")
            }*/
            Image(
                painter = rememberImagePainter(gato.url) ,
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(8.dp)) // redondear las esquinas
            )
        }
    }
}
