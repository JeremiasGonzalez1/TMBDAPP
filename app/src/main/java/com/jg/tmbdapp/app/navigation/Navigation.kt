package com.jg.tmbdapp.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jg.tmbdapp.app.screens.detail.DetailsScreen
import com.jg.tmbdapp.app.screens.home.HomeScreen
import com.jg.tmbdapp.features.utils.models.MovieItem
import kotlinx.serialization.Serializable

@Composable
fun Navigation(modifier: Modifier = Modifier, navHostController: NavHostController) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = AppsScreen.HomeScreen.route
    ) {
        composable(route = AppsScreen.HomeScreen.route) {
            HomeScreen() { movie ->
                navHostController.navigate(DetailScreenData(
                    adult =  movie.adult,
                    backdrop_path = movie.backdropPath,
                    id = movie.id,
                    original_language = movie.originalLanguage,
                    original_title = movie.originalTitle,
                    overview = movie.overview,
                    popularity = movie.popularity.toString(),
                    poster_path = movie.posterPath,
                    title = movie.title,
                    vote_average = movie.voteAverage.toString(),
                    vote_count = movie.voteCount,
                    genre_list = movie.genreList
                ))
            }
        }
        composable<DetailScreenData> {
            val args = it.toRoute<DetailScreenData>()
            DetailsScreen(movie = MovieItem(
                adult = args.adult,
                backdropPath = args.backdrop_path,
                id =  args.id,
                originalLanguage = args.original_language,
                originalTitle = args.original_title,
                overview = args.overview,
                popularity = args.popularity.toDouble(),
                posterPath = args.poster_path,
                title = args.title,
                voteAverage = args.vote_average.toDouble(),
                voteCount = args.vote_count,
                genreList = args.genre_list,
                releaseDate ="2022-01-01",
            )
            ){
                navHostController.navigateUp()
            }
        }
    }
}

@Serializable
data class DetailScreenData(
    val adult: Boolean,
    val backdrop_path: String,
    val id : Int ,
    val original_language :String,
    val original_title: String,
    val overview:String ,
    val popularity: String ,
    val poster_path: String ,
    val title:String ,
    val vote_average:String ,
    val vote_count:Int ,
    val genre_list:List<Int> ,
)