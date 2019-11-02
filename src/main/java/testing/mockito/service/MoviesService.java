package testing.mockito.service;

import java.util.List;

public interface MoviesService {
	List<String> getMovies();
	
	void deleteMovie(String movie);
}
