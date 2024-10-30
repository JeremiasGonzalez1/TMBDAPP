package com.jg.tmbdapp.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jg.tmbdapp.BuildConfig
import com.jg.tmbdapp.R
import com.jg.tmbdapp.features.utils.models.MovieItem
import com.jg.tmbdapp.app.screens.home.HomeUIState
import com.jg.tmbdapp.app.theme.GraySearch
import com.jg.tmbdapp.app.theme.PlayButton
import com.jg.tmbdapp.app.theme.WhiteFC
import com.jg.tmbdapp.features.utils.models.Movie


@Composable
fun SearchBar(listStatus: HomeUIState<Movie>, search: (String)->Unit) {
    var input by remember{ mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(end = 16.dp, start = 16.dp), verticalAlignment = Alignment.CenterVertically){
            TextField(modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
                .weight(1f)
                .height(48.dp), value =input , onValueChange ={
                input = it
                search(input)
                if(input.isNotEmpty())
                    expanded = true
                else
                    expanded = false
            },
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.search), tint = PlayButton, contentDescription = null) },
                placeholder = {
                    Text(text = "Search...",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        color = GraySearch.copy(alpha = 0.5f),
                    )
                },
                maxLines = 1,
                colors = TextFieldDefaults.colors(unfocusedContainerColor = WhiteFC,
                    focusedContainerColor = WhiteFC,
                    focusedIndicatorColor = WhiteFC,
                    unfocusedIndicatorColor = WhiteFC
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.width(9.dp))
            Button(onClick = {},
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxHeight()
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = PlayButton),
                shape = ShapeDefaults.Large) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(modifier = Modifier
                        .size(20.dp)
                        .weight(1f), painter = painterResource(id = R.drawable.vector),contentDescription = null)
                    Text(modifier = Modifier.weight(1f), text = "Filters", maxLines = 1,)
                }
            }
        }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false  }) {
                when(val movies=listStatus){
                    is HomeUIState.Error -> {
                        Text(text = "Error al buscar pelicula", color = Color.Red)
                    }
                    is HomeUIState.Loading -> {
                        CircularProgressIndicator()
                    }
                    is HomeUIState.Success -> {
                        movies.value.list.onEach {
                            DropdownMenuItem(text = {

                               ItemSearch(movieItem = it)
                            }, onClick = {})
                        }
                    }
                }
            }
            
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .background(Color.Red)) {

        }
    }
}

@Composable
fun ItemSearch(movieItem: MovieItem) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(42.dp)){
        Box(modifier = Modifier
            .fillMaxHeight()
            .width(52.dp) ){
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(BuildConfig.base_img + movieItem.posterPath)
                    .error(R.drawable.ic_launcher_foreground)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Text(text = movieItem.title)
    }
}