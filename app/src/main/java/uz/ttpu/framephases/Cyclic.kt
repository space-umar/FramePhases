package uz.ttpu.framephases

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.*
import kotlinx.coroutines.launch

@Composable
fun CyclicDemo() {
    var imageHeightPx by remember { mutableIntStateOf(0) }
    val heightInThisComposition = imageHeightPx
    val compositions = remember { intArrayOf(0) }
    SideEffect {
        Log.d("Cycle", "composition #${++compositions[0]}: imageHeightPx=$heightInThisComposition")
    }

    Box {
        Box(
            Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0xFF6650A4))
                .onSizeChanged { size ->
                    Log.d("Cycle", "onSizeChanged: height=${size.height}")
                    imageHeightPx = size.height // Don't do this
                }
        )
        Text(
            text = "I'm below the image",
            modifier = Modifier.padding(
                top = with(LocalDensity.current) { heightInThisComposition.toDp() }
            ),
        )
    }
}

@Composable
fun CyclicFixed() {
    val compositions = remember { intArrayOf(0) }
    SideEffect { Log.d("Cycle", "fixed: composition #${++compositions[0]}") }

    Column {
        Box(Modifier.fillMaxWidth().height(120.dp).background(Color(0xFF6650A4)))
        Text("I'm below the image")
    }
}
