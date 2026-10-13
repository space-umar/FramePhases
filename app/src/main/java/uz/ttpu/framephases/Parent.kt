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

// ---- Version 1: value passed to Child ----
@Composable
fun Parent() {
    var count by remember { mutableIntStateOf(0) }
    SideEffect { Log.d("Skip", "Parent: composition") }

    Column {
        Button(onClick = { count++ }) { Text("Add") }
        Child(count)
        Sibling()
    }
}

@Composable
fun Child(count: Int) {
    SideEffect { Log.d("Skip", "Child: composition") }
    Text("Count: $count")
}

@Composable
fun Sibling() {
    SideEffect { Log.d("Skip", "Sibling: composition") }
    Text("I never change")
}

// ---- Version 2: lambda passed to Child ----
@Composable
fun ParentLambda() {
    var count by remember { mutableIntStateOf(0) }
    SideEffect { Log.d("Skip", "ParentLambda: composition") }

    Column {
        Button(onClick = { count++ }) { Text("Add") }
        ChildLambda(count = { count }) // pass a lambda that returns the value
        Sibling()
    }
}

@Composable
fun ChildLambda(count: () -> Int) {
    SideEffect { Log.d("Skip", "ChildLambda: composition") }
    Text("Count: ${count()}") // the state is read here, not in ParentLambda
}
