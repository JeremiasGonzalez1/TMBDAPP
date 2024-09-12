package com.jg.tmbdapp.presentation

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout

fun Modifier.visible(visible :Boolean): Modifier =this.then(
    if (visible){
        Modifier
    }else{
        Modifier.layout{ _, constraints ->
            layout(0,0){}
        }
    }
)