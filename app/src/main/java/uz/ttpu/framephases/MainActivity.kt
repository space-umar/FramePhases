package uz.ttpu.framephases

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MaterialTheme { DemoHost() } }
    }
}

/** One demo at a time. Pick a chip; the demo starts fresh each time. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoHost() {
    val demos: List<Pair<String, @Composable () -> Unit>> = listOf(
        "Q4 Tree" to { LectureTree() },
        "Q5 Padding" to { PaddingDemo() },
        "Q6 Offset" to { OffsetDemo() },
        "Q6 Offset exp" to { OffsetDemoExperiment() },
        "Q7 Color" to { ColorDemo() },
        "Q7 Color box" to { ColorDemoBox() },
        "Q9 Slow" to { ParallaxSlow() },
        "Q9 Fast" to { ParallaxFast() },
        "Q10 Naive" to { ScrollToTopNaive() },
        "Q10 Derived" to { ScrollToTopDerived() },
        "Q11 Parent" to { Parent() },
        "Q11 Lambda" to { ParentLambda() },
        "Q13 Cyclic" to { CyclicDemo() },
        "Q13 Fixed" to { CyclicFixed() },
        "Q14 Stack" to { StackBelowDemo() },
    )
    var selected by remember { mutableIntStateOf(0) }

    Column(Modifier.fillMaxSize().statusBarsPadding()) {
        LazyRow(
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(demos.size) { i ->
                FilterChip(
                    selected = selected == i,
                    onClick = { selected = i },
                    label = { Text(demos[i].first) },
                )
            }
        }
        key(selected) { Box(Modifier.weight(1f)) { demos[selected].second() } }
    }
}