import com.jg.tmbdapp.domain.popular.model.Popular
import com.jg.tmbdapp.domain.utils.StatusResult

interface PopularRepository {
    suspend fun getPopularMovies(): StatusResult<Popular>
}