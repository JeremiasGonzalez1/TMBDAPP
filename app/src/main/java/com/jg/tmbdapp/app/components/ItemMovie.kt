package com.jg.tmbdapp.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jg.tmbdapp.BuildConfig
import com.jg.tmbdapp.R
import com.jg.tmbdapp.app.theme.PlayButton
import com.jg.tmbdapp.app.theme.StarColor
import com.jg.tmbdapp.features.utils.models.MovieItem

@Composable
fun ItemMovie(movieItem: MovieItem, navigate:()->Unit) {
    var visible by remember {
        mutableStateOf(true)
    }
    ElevatedCard(
        onClick = {
                  navigate()
        },
        modifier = Modifier
            .width(182.dp)
            .height(231.dp)
            .padding(horizontal = 12.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(Color.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 12.dp)){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .height(148.dp)
                    .width(148.dp)
                    .clip(RoundedCornerShape(30.dp)),
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(BuildConfig.base_img + movieItem.posterPath).listener(
                            onSuccess = { request, result ->
                                visible = false
                            },
                            onError = { request, result ->
                                visible = false
                            },
                        )
                        .error(R.drawable.ic_launcher_foreground)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 13.dp, start = 12.dp, end = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(2f),
                    text = movieItem.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp
                )
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(8.dp),
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = StarColor
                    )
                    Text(
                        modifier = Modifier,
                        text = movieItem.voteAverage.toString(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(PlayButton),
                    contentAlignment = Alignment.Center
                )
                {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ItemMovieLoading(modifier: Modifier = Modifier) {
    ShimmerAnimation(modifier = Modifier
        .width(182.dp)
        .height(231.dp)
        .padding(horizontal = 12.dp)
        .clip(RoundedCornerShape(30.dp)))
}
