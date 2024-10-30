package com.jg.tmbdapp.app.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jg.tmbdapp.app.components.FutureMovies
import com.jg.tmbdapp.app.components.FutureMoviesLoader
import com.jg.tmbdapp.app.components.ItemMovie
import com.jg.tmbdapp.app.components.ItemMovieLoading
import com.jg.tmbdapp.app.components.Profile
import com.jg.tmbdapp.app.components.SearchBar
import com.jg.tmbdapp.app.theme.PlayButton
import com.jg.tmbdapp.app.utils.visible
import com.jg.tmbdapp.features.utils.models.Movie
import com.jg.tmbdapp.features.utils.models.MovieItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navigate:(MovieItem)->Unit) {

    val viewModel : HomeViewModel = hiltViewModel()
    viewModel.getPopularMovies()
    viewModel.getUpComingMovies()
    val listMovies = viewModel.popularList.collectAsState()
    val listSearch = viewModel.searchList.collectAsState()
    val listUpComing = viewModel.upComing.collectAsState()

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
            RowTittle("Popular", true)
            Spacer(modifier = Modifier.height(16.dp))
        }
        when(listMovies.value){
            is HomeUIState.Error -> item{Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Red))
            }
            is HomeUIState.Loading -> item{
                LazyRow(modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ){
                    items(3) {count ->
                        for (count in 1..3){
                            ItemMovieLoading()
                        }
                    }
                }
            }
            is HomeUIState.Success ->{
                item {
                    LazyRow(modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        items((listMovies.value as HomeUIState.Success<Movie>).value.list) { popular ->
                            ItemMovie(movieItem = popular){
                                navigate(popular)
                            }
                        }
                    }
                }
            }
        }

        when(listUpComing.value){
            is HomeUIState.Error -> {}
            is HomeUIState.Loading -> {
                items(3) {count ->
                    for (count in 1..3){
                        FutureMoviesLoader()
                    }
                }
            }
            is HomeUIState.Success -> {
                item {
                    Spacer(modifier = Modifier.height(32.dp))
                    RowTittle("Future Movies", false)
                    Spacer(modifier = Modifier.height(16.dp))
                    (listMovies.value as HomeUIState.Success<Movie>).value.list.onEach {
                        FutureMovies(movie = it)
                    }
                }
            }
        }
    }
}

@Composable
fun RowTittle(text:String, visible:Boolean) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
        Text(modifier = Modifier
            .weight(1f)
            .padding(start = 16.dp),
            text = text,
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold)
        TextButton(modifier = Modifier
            .padding(end = 16.dp)
            .visible(visible),
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


