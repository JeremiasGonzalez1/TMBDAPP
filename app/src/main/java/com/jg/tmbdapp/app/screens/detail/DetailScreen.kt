package com.jg.tmbdapp.app.screens.detail

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jg.tmbdapp.BuildConfig
import com.jg.tmbdapp.R
import com.jg.tmbdapp.app.theme.PlayButton
import com.jg.tmbdapp.features.utils.models.MovieItem
import java.util.Locale


@Composable
fun DetailsScreen(movie:MovieItem, backPress:()->Unit) {

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        item {
            BackdropPoster(movie.backdropPath){
               backPress()
            }
        }
        item {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)){
                DescriptionMovie(movie, Modifier)
            }
        }
    }
}

@Composable
private fun BackdropPoster(
    backDrop: String,
    navigation:()->Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .blur(3.dp)
            ,
            model = ImageRequest.Builder(LocalContext.current)
                .data(BuildConfig.base_img + backDrop)
                .error(R.drawable.the_batman_unmask)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.9f,
        )
        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color.White),
                    startY = 1f,
                )
            ))

        IconButton(modifier = Modifier.align(Alignment.TopStart), onClick = { navigation()}) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Default.KeyboardArrowLeft,
                tint = Color.White,
                contentDescription = null
            )
        }
    }
}

@Composable
fun DescriptionMovie(movie: MovieItem, modifier: Modifier) {
    val horizontalScroll = rememberScrollState()

    Column(modifier = modifier
        .clip(RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp))
        .background(PlayButton)
        .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        HorizontalDivider(modifier = Modifier
            .width(180.dp)
            .padding(top = 20.dp), thickness = 1.dp, color = Color.White)
        TittleMovie(modifier = Modifier.padding(top = 24.dp), tittle =movie.title)

        Text(modifier= Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp),
            color = Color.Gray,
            fontSize = 20.sp,
            fontWeight = FontWeight.W300,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center,
            text = movie.overview)

        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            items(movie.genreList.size){ item ->
                GenresRender(genre = movie.genreList[item])
            }
        }

        Spacer(modifier =Modifier.height(32.dp))
    }
}

@Composable
fun GenresRender(genre: Int) {
        Column(
            Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(Color.White)
                .border(2.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp))
                ,horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Icon(imageVector = Icons.Default.Person, contentDescription = null)
                    Text(text = when(genre){
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
                    },
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(horizontal = 8.dp))

        }
}

@Composable
fun TittleMovie(modifier: Modifier,tittle:String) {
    Box(modifier = modifier.fillMaxWidth()){
        Text(text = tittle.uppercase(Locale.getDefault()),
            modifier= Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.W400,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center)
    }
}

@Preview
@Composable
private fun PrevDetailScreen() {
    var notScroll by remember {
        mutableStateOf(false)
    }
    val size by animateDpAsState(targetValue = if(notScroll) 500.dp else 400.dp)
    val scrollState = rememberScrollState()
    LaunchedEffect(scrollState.value) {
        if (scrollState.value>0) notScroll =true
    }
    val movie = MovieItem(
        adult = false,
        backdropPath = "",
        id = 268,
        originalLanguage = "en",
        originalTitle = "Batman",
        overview = "Batman must face his most ruthless nemesis when a deformed madman calling himself \"The Joker\" seizes control of Gotham's criminal underworld.",
        popularity = 48.909,
        posterPath = "/oYWUsIVoMl2H6Xl3FTBi9R9Y4OS.jpg",
        title = "Batman",
        voteAverage = 7.231,
        genreList = listOf(12, 14, 16, 18),
        voteCount = 7684,
        releaseDate = "2022-03-01")

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        item{
            BackdropPoster(backDrop = movie.backdropPath){}
        }
        item {
            DescriptionMovie(movie, modifier = Modifier.size(size))
        }
    }
}
