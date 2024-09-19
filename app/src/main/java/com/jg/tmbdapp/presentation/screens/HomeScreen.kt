package com.jg.tmbdapp.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.sharp.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jg.tmbdapp.BuildConfig
import com.jg.tmbdapp.R
import com.jg.tmbdapp.domain.popular.model.Popular
import com.jg.tmbdapp.domain.utils.MovieItem
import com.jg.tmbdapp.presentation.components.Profile
import com.jg.tmbdapp.presentation.components.SearchBar
import com.jg.tmbdapp.presentation.theme.PlayButton
import com.jg.tmbdapp.presentation.theme.StarColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen( viewModel: HomeViewModel) {
    viewModel.getPopularMovies()
    val listMovies = viewModel.popularList.collectAsState()
    val listSearch = viewModel.searchList.collectAsState()


    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 32.dp)) {
        stickyHeader {
            Profile(name ="Roberto" , img = "/g6MrJxNaHYGYU7Sxo72e5B8gKOV.jpg")
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            SearchBar(listSearch.value){ query->
                viewModel.searchMovies(query = query)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
        item {
            RowPopular()
            Spacer(modifier = Modifier.height(16.dp))
        }
        when(listMovies.value){
            is HomeUIState.Error -> item{Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Red))
            }
            is HomeUIState.Loading -> item{Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow))
            }
            is HomeUIState.Success ->
                item {
                    LazyRow(modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        items((listMovies.value as HomeUIState.Success<Popular>).value.listPopular) { popular ->
                            ItemMovie(movieItem = popular)
                        }
                    }
                }
        }
    }
}

@Composable
fun RowPopular() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
        Text(modifier = Modifier
            .weight(1f)
            .padding(start = 16.dp),
            text = "Popular",
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold)
        TextButton(modifier = Modifier
            .padding(end = 16.dp),
            onClick = {},
        ){
            Text(text = "See all",
                textAlign = TextAlign.End,
                color = PlayButton,
                fontWeight = FontWeight.W500)
            Icon(imageVector = Icons.Sharp.KeyboardArrowRight, contentDescription = null, tint= PlayButton)
        }
    }
}


@Composable
fun ItemMovie(movieItem: MovieItem) {
    var visible by remember {
        mutableStateOf(true)
    }
    ElevatedCard(
        onClick = {},
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
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color.Red),
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(BuildConfig.base_img + movieItem.poster_path).listener(
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
                        text = movieItem.vote_average.toString(),
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
