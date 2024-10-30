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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jg.tmbdapp.BuildConfig
import com.jg.tmbdapp.R
import com.jg.tmbdapp.features.utils.models.MovieItem
import java.util.Locale


@Preview
@Composable
private fun PreviewFutureMovies() {
    FutureMovies(movie = MovieItem(
        adult = false,
        backdropPath = "",
        genreList = listOf(10, 12, 14),
        id = 0,
        originalLanguage = "",
        originalTitle = "Batman reaparece",
        overview = "8.3",
        popularity = 0.0,
        posterPath = "",
        title = "Barman se escabia",
        voteAverage = 0.0,
        voteCount = 0,
        releaseDate = "2024-10-22"
    ))
}

@Composable
fun FutureMovies(movie: MovieItem) {
    ElevatedCard(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(108.dp)
            .clip(RoundedCornerShape(24.dp))
            .padding(vertical = 4.dp, horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp, pressedElevation = 5.dp)
    ){
        Row(modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                modifier = Modifier
                    .height(95.dp)
                    .width(92.dp)
                    .padding(12.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.Black),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(BuildConfig.base_img + movie.posterPath)
                    .error(R.drawable.ic_launcher_foreground)
                    .build(), contentDescription = null,
                contentScale = ContentScale.Crop)

            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly) {
                //primera seccion de la columna de datos
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start)
                {
                    Text(text = movie.title.uppercase(Locale.getDefault()),
                        modifier = Modifier.weight(1f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                       )
                    Row(modifier = Modifier
                        .weight(1f)
                        .padding(start = 24.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Star, modifier = Modifier.size(18.dp), tint = Color.Yellow, contentDescription =null )
                        Text(text = movie.voteAverage.toString(), modifier = Modifier.padding(start = 5.dp), fontWeight = FontWeight.W500)
                    }

                }
                //segunda seccion de la columna de datos
                Row (modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically){
                    Icon(imageVector = Icons.Default.DateRange, contentDescription =null )
                    Text(text = movie.releaseDate, modifier = Modifier.padding(start = 5.dp))

                }

                //tercera seccion de la columna de datos

                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically){
                    movie.genreList.onEach { item ->
                        Text(when(item){
                            12 ->  "Adventure"
                            14 -> "Fantasy"
                            16 -> "Animation"
                            18 ->  "Drama"
                            27 -> "Horror"
                            28 ->  "Action"
                            35 ->  "Comedy"
                            36 -> "History"
                            37 ->  "Western"
                            53 -> "Thriller"
                            80 -> "Crime"
                            99 -> "Documentary"
                            878 -> "Science Fiction"
                            9648 -> "Mystery"
                            10402 -> "Music"
                            10749 ->  "Romance"
                            10751 -> "Family"
                            10752 -> "War"
                            10770 -> "TV Movie"
                            else -> {
                                "Unknown"
                            }
                        }, fontSize = 14.sp, fontWeight = FontWeight.W300, color = Color.Black.copy(alpha = 0.7f))

                    }
                }
            }
        }
    }
}

@Composable
fun FutureMoviesLoader() {
    ShimmerAnimation(modifier = Modifier
        .fillMaxWidth()
        .height(108.dp)
        .clip(RoundedCornerShape(24.dp))
        .padding(4.dp) )
}