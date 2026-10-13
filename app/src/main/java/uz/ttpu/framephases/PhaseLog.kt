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

/** Logs the layout phase (measure and placement) and the drawing phase. */
fun Modifier.logPhases(name: String): Modifier = this
    .layout { measurable, constraints ->
        Log.d("Phases", "$name: layout/measure")
        val placeable = measurable.measure(constraints)
        layout(placeable.width, placeable.height) {
            Log.d("Phases", "$name: layout/place")
            placeable.place(0, 0)
        }
    }
    .drawBehind { Log.d("Phases", "$name: draw") }
