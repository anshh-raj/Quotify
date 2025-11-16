package com.example.quotify

import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.quotify.components.BottomNavigationBar
import com.example.quotify.navigation.AppNavGraph
import com.example.quotify.screens.viewModel.FavouriteViewModel
import com.example.quotify.ui.theme.QuotifyTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuotifyTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    BottomNavigationBar {route ->
                        navController.navigate(route)
                    }

                },

            )
        }
    ) {innerPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
//                .windowInsetsPadding(WindowInsets.statusBars)
        ) {
            AppNavGraph(navController)

        }

    }

}






@Composable
fun SideEffectsMain() {
    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {

//        Derived()
//        Loader()

//        ProducedState()

//        DisposableEffect2()
//        TextField(value = "", onValueChange = {})

//        DisposableEffect()
//        RememberUpdatedState()
//        CoroutineScopeComposable()
//        LaunchEffectComposable()

//        Counter()
//
    }
}

@Composable
fun Counter() {
    val count = remember { mutableIntStateOf(0) }
    val key = count.intValue % 3 == 0
    LaunchedEffect(key1 = key) {
        Log.d("counter", "Counter: ${count.intValue}")
    }
    Button(
        onClick = { count.intValue++ }
    ) {
        Text("Increment count")
    }

//    LaunchedEffect is a side-effect API
//    that lets you run a suspend function (like a coroutine)/ anything once or when the key changes
}

@Composable
fun LaunchEffectComposable() {

    val counter = remember { mutableIntStateOf(0) }
//    val key = counter.intValue % 3 == 0

    LaunchedEffect(key1 = Unit) {
        Log.d("LaunchEffectComposable", "LaunchEffectComposable: Started...")

        try {
            for (i in 1..10) {
                counter.intValue++
//                Log.d("LaunchEffectComposable", "LaunchEffectComposable: ${counter.intValue}")
                delay(1000)
            }
        } catch (e: Exception) {
            Log.d("LaunchEffectComposable", "LaunchEffectComposableError: ${e.message.toString()}")
        }
    }

    var text = "Counter is running ${counter.intValue}"
    if (counter.intValue == 10) {
        text = "counter stopped"
    }

    Text(text)

    //launched effect launches a coroutine in the background as we have passed unit here as key
    // it will only run at the initial composition and and will not be affected by recompostion
    // which will occur when counter is updated but if we use key as key1 then every time value of key
    // changes the previous coroutine of launched effect will be canceled and the new coroutine will
    // be executed but the counter value wouldn't start from zero as it will be remembered and will
    // continue increasing non stop as each time a new coroutine will start


    //whenever configuration changes happens like rotation entire activity is destroyed and recreated
    //again hence previous couroutine started by launched effect is also destroyed(to avoid memory leaks and save resources) and created again
    // the values will also start from 0 as the composition is created from start

    //problems with launched effect and solution
    /*
    * 1) launched effect is a composable function and can't be launched without composable function
    * like it cannot be called inside a event such as onClick of a button
    *
    * 2)the entire management of coroutines launched inside the coroutine scope of launched effect
    * will be managed by launched effect itself we won't have control over it like when we want to
    * start, cancel, join or delay certain coroutines
    *
    * 3) it is btw a feature but it only runs at start or when its key changes
    *
    * 1) to solve this we have rememberCoroutineScope() which runs independently and dosen't have any
    * impact of recomposition changes same as launched effect, so all the side effects will be run in controlled
    * environment
    *
    * 2)lifecycle of rememberCoroutineScope is attached to the lifecycle of composable it in run in
    * so when the composable will be removed from the screen all the coroutine launched inside the
    * rememberCoroutineScope will also be cancelled
    *
    * 3) this rememberCoroutineScope can be used anywhere inside the composable function even on events
    *
    * so basically to run side effects we have 2 ways
    * lauched effect and rememberCoroutineScope
    *
    * */
}

@Composable
fun CoroutineScopeComposable() {

    val counter = remember { mutableIntStateOf(0) }
    var scope = rememberCoroutineScope()

    var text = "Counter is running ${counter.intValue}"
    if (counter.intValue == 10) {
        text = "Counter Stopped"
    }

    Column {
        Text(text)
        Button(
            onClick = {
                scope.launch {
                    Log.d("CoroutineComposable", "CoroutineScopeComposable: Started...")
                    try {
                        for (i in 1..10) {
                            counter.intValue++
                            delay(1000)
                        }
                    } catch (e: Exception) {
                        Log.d(
                            "CoroutineComposable",
                            "CoroutineScopeComposable: ${e.message.toString()}"
                        )
                    }
                }
            }
        ) {
            Text("Start")
        }
    }

    //this also gets cancelled by configuration changes and value of counter starts from 0 as
    // new scope of rememberCoroutineScope is created

    //onne note point ...here when we press start twice there will be 2 coroutine scope launched in the
    // background which will run independently and both will increase the value of counter by 1 hence
    // counter will increase by 2 ....it is not like one will get cancelled when other starts
    // but will run ten times and total value will be remembered by counter

}

@Composable
fun RememberUpdatedState() {
    var counter = remember { mutableIntStateOf(0) }
    LaunchedEffect(key1 = Unit) {
        delay(2000)
        counter.intValue = 10
    }

    DisplayCount(counter.intValue)
}

@Composable
fun DisplayCount(value: Int) {
//    LaunchedEffect(key1 = Unit) {
//        delay(5000) // long running task
//        Log.d("display_Count", "DisplayCount: $value")//consider we need to use the value here
//        //this will print 0 and not 10 as when value will be updated to 10 ...launched effect won't be called again during recomposition
//    }

    //    sol 1 to solve above problem

//    LaunchedEffect(key1 = value) {
//        delay(5000) // long running task
//        Log.d("display_Count", "DisplayCount: $value")//consider we need to use the value here
//    // this will call the launched effect again when value changes but the long running task will start again from its start hence not very optimised
//    }

    //sol 2
    val state = rememberUpdatedState(value)
    LaunchedEffect(key1 = Unit) {
        delay(5000) // long running task
        Log.d(
            "display_Count",
            "DisplayCount: ${state.value}"
        )//consider we need to use the value here
        // state will hold the latest value
    }

    Text(value.toString())

}

@Composable
fun DisposableEffect() {
    var state = remember { mutableStateOf(false) }

    DisposableEffect(key1 = state.value) {
        Log.d("disposable effect", "DisposableEffect: Disposable Effect started")
        onDispose {
            Log.d("disposable effect", "DisposableEffect: cleaning up side effects")
            // onDispose will be called when the composable leaves composition (is removed from the UI tree)
//            The key changes → old effect is disposed, and a new one is started.
        }
    }

    Button(onClick = {
        state.value = !state.value
    }) {
        Text("change state")
    }

    //this is also a type of side effect handler used when we want to do some cleanup after
    //a composition ends
}

//eg for disposable effect
@Composable
fun DisposableEffect2() {
    val view = LocalView.current // give access to all the visible views on the current screen
    DisposableEffect(key1 = Unit) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            // whenever there is a change in layout this code is executed

            val insets =
                ViewCompat.getRootWindowInsets(view) // this will find all the rectangles on the current screen
            val isKeyboardVisible =
                insets?.isVisible(WindowInsetsCompat.Type.ime()) //checks if keyboard is visible or not
            Log.d("Keyboard_check", "DisposableEffect2: ${isKeyboardVisible.toString()}")

        }

        view.viewTreeObserver.addOnGlobalLayoutListener(listener) // whenever there is a change in layout this will call the listener
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
            Log.d("Keyboard_check", "DisposableEffect2: event deleted")
            //when the composable will go out of screen it will stop/remove the listener to avoid memory leak and save resources
        }
    }
}


//produced state
// it is a utility method for launched effect(where we have to create a state separately and then call the coroutine to update the state)
// which combines both the state creation and updation
// it is helpful at places where we have live data or flow ..and we have to create and maintain state for this

@Composable
fun ProducedState() {
    val state = produceState(initialValue = 0) {
        for (i in 1..10) {
            delay(1000)
            value += 1
        }
    }

    Text(state.value.toString())
}

//implementation of loader using produced state
@Composable
fun Loader() {
    val degree = produceState(initialValue = 0) {
        while (true) {
            delay(1000)
            value = (value + 10) % 360
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Reload",
                modifier = Modifier
                    .size(60.dp)
                    .rotate(degree.value.toFloat())
            )
            Text("Loading")
        }
    }
}

//derivedStateOf
//it is used to derive a new state from existing multiple states
@Composable
fun Derived() {
    val tableOf = remember { mutableIntStateOf(5) }
    val index = produceState(1) {
        repeat(10) {
            delay(1000)
            value += 1
        }
    }

    val message = remember {
        derivedStateOf {
            "${tableOf.intValue} * ${index.value} = ${tableOf.intValue * index.value}"
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(1f)
    ) {
        Text(
            message.value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
