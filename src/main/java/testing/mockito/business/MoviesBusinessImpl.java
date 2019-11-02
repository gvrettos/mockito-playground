package testing.mockito.business;

import java.util.ArrayList;
import java.util.List;

import testing.mockito.service.MoviesService;

public class MoviesBusinessImpl {

	private MoviesService moviesService;

	public MoviesBusinessImpl(MoviesService moviesService) {
		this.moviesService = moviesService;
	}

	public List<String> getMoviesRelatedToDie() {
		List<String> filteredMovies = new ArrayList<>();
		List<String> allMovies = moviesService.getMovies();
		for (String movie: allMovies) {
			if (movie.contains("die")) {
				filteredMovies.add(movie);
			}
		}
		return filteredMovies;
	}
	
	public void deleteMoviesNotOfficialForTheJamesBondFranchise() {
		List<String> allMovies = moviesService.getMovies();
		for (String movie: allMovies) {
			if (movie.contains("(") || movie.contains("again")) {
				moviesService.deleteMovie(movie);
			}
		}
	}

}
