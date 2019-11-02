package testing.mockito.service;

import java.util.List;

import utils.MoviesDataGenerator;

public class MoviesServiceStub implements MoviesService {

	@Override
	public List<String> getMovies() {
		return MoviesDataGenerator.generateMovies();
	}

	@Override
	public void deleteMovie(String movie) {
		
	}

}
