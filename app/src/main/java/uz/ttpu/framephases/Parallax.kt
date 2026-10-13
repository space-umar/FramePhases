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
fun ParallaxSlow() {
    val listState = rememberLazyListState()
    val compositions = remember { intArrayOf(0) }
    SideEffect { Log.d("Parallax", "slow: composition #${++compositions[0]}") }

    Box {
        Box(
            Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0xFF6650A4))
                .offset(
                    y = with(LocalDensity.current) {
                        (listState.firstVisibleItemScrollOffset / 2).toDp()
                    }
                )
        )
        LazyColumn(state = listState) {
            items(100) { index ->
                Text("Item $index", Modifier.fillMaxWidth().padding(16.dp))
            }
        }
    }
}

@Composable
fun ParallaxFast() {
    val listState = rememberLazyListState()
    val compositions = remember { intArrayOf(0) }
    SideEffect { Log.d("Parallax", "fast: composition #${++compositions[0]}") }

    Box {
        Box(
            Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0xFF6650A4))
                .offset {
                    IntOffset(x = 0, y = listState.firstVisibleItemScrollOffset / 2)
                }
        )
        LazyColumn(state = listState) {
            items(100) { index ->
                Text("Item $index", Modifier.fillMaxWidth().padding(16.dp))
            }
        }
    }
}
