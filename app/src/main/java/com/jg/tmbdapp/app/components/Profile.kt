package com.jg.tmbdapp.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jg.tmbdapp.BuildConfig
import com.jg.tmbdapp.R
import com.jg.tmbdapp.app.theme.PlayButton

@Composable
fun Profile(name: String, img: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        ,
        verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Hello!", color = PlayButton, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(text = name, color = PlayButton, fontWeight = FontWeight.SemiBold, fontSize = 24.sp)
        }
        AsyncImage(modifier = Modifier.size(44.dp).clip(RoundedCornerShape(16.dp)).background(
            PlayButton
        ), model =ImageRequest.Builder(LocalContext.current)
            .data(BuildConfig.base_img + img).listener(
            )
            .error(R.drawable.ic_launcher_foreground)
            .build(),
            contentDescription = null,
            imageLoader = ImageLoader(LocalContext.current),
            contentScale = ContentScale.Crop
        )
    }
}