package com.devapp.to_docompose.ui.screens.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devapp.to_docompose.R
import com.devapp.to_docompose.ui.theme.SPLASH_ICON_HEIGHT
import com.devapp.to_docompose.ui.theme.ToDoComposeTheme
import com.devapp.to_docompose.ui.theme.splashBackGround
import com.devapp.to_docompose.util.Constants
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToListScreen:()->Unit
) {

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val offsetState by animateDpAsState(
        targetValue = if(startAnimation) 0.dp else SPLASH_ICON_HEIGHT,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing
        )
    )

    val alphaState by animateFloatAsState(
        targetValue =if(startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        )

    )


    LaunchedEffect(key1 = true ){
        startAnimation = true
        delay(Constants.SPLASH_SCREEN_DELAY.toLong())
        navigateToListScreen()
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.splashBackGround),
        contentAlignment = Alignment.Center
        ) {
        Icon(
            painter = painterResource(
                id = getLogo(),
            ),
            tint= Color.Unspecified,
            contentDescription = "To-Do",
            modifier = Modifier.offset(y=offsetState).alpha(alphaState)
        )
    }
}

@Composable
fun getLogo():Int{
    return if(isSystemInDarkTheme()){
        R.drawable.ic_logo_dark
    }else R.drawable.ic_logo_light

}

@Composable
@Preview
fun PreviewSplashScreen(){
    SplashScreen {

    }
}

@Composable
@Preview
fun PreviewSplashScreenDark(){
    ToDoComposeTheme(darkTheme = true) {
        SplashScreen {

        }
    }
}