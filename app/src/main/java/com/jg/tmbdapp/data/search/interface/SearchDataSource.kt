import com.jg.tmbdapp.data.search.model.SearchDTO
import com.jg.tmbdapp.domain.utils.StatusResult

interface SearchDataSource {
    suspend fun searchMovies(query:String):StatusResult<SearchDTO>
}