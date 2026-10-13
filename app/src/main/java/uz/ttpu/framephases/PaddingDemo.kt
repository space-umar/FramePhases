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
fun PaddingDemo() {
    var padding by remember { mutableStateOf(8.dp) }
    SideEffect { Log.d("Phases", "PaddingDemo: composition") }

    Column {
        Button(onClick = { padding = if (padding == 8.dp) 32.dp else 8.dp }) {
            Text("Toggle padding")
        }
        Text(
            text = "Padding demo",
            modifier = Modifier
                .padding(padding) // read in the composition phase
                .logPhases("padding demo"),
        )
    }
}
