 package com.example.lemonadeapp

import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonImageAndText(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun LemonImageAndText(modifier: Modifier = Modifier) {
    var stage by remember { mutableStateOf(1) }
    var tapsRequired by remember { mutableStateOf((2..4).random()) }
    var taps by remember { mutableStateOf(1) }

    val imageResource = when (stage) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val imageText = when (stage) {
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.glass_of_lemonade
        else -> R.string.empty_glass
    }
    val contentDescription = when (stage) {
        1 -> R.string.lemon_tree_content_description
        2 -> R.string.lemon_content_description
        3 -> R.string.glass_of_lemonade_content_description
        else -> R.string.empty_glass_content_description
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            if (stage == 4) taps = 1 else taps++

            stage = when (taps) {
                1 -> {
                    tapsRequired = (2..4).random()
                    1
                }
                in (2..tapsRequired) -> 2
                tapsRequired + 1 -> 3
                else -> 4
            }
        },
            colors = ButtonDefaults.buttonColors(containerColor = Color(195, 236, 210)),
            shape = RoundedCornerShape(15),
            modifier = Modifier.padding(16.dp)
        ) {
            Image(painter = painterResource(id = imageResource),
                contentDescription = stringResource(id = contentDescription),
                Modifier.clickable {
                    if (stage == 4) taps = 1 else taps++

                    stage = when (taps) {
                        1 -> {
                            tapsRequired = (2..4).random()
                            1
                        }
                        in (2..tapsRequired) -> 2
                        tapsRequired + 1 -> 3
                        else -> 4
                    }
                }.background(Color(195, 236, 210), RoundedCornerShape(15))
            )
        }
        Text(text = stringResource(id = imageText), fontSize = 18.sp)
    }
}
