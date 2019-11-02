package testing.mockito.business;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import testing.mockito.service.MoviesService;
import testing.mockito.service.MoviesServiceStub;

// Stub approach disadvantages:
// 1. Dynamic conditions: testing different scenarios makes the code too complicated
// 2. Service definition: Creating separate classes just for testing and maintaining them 
// 	  if the original class is updated
public class MoviesBusinessImplStubTest {

	@Test
	public void getMoviesWhenUsingStub() {
		MoviesService moviesServiceStub = new MoviesServiceStub();
		MoviesBusinessImpl moviesBusinessImpl = new MoviesBusinessImpl(moviesServiceStub);
		List<String> filteredMovies = moviesBusinessImpl.getMoviesRelatedToDie();
		assertEquals(3, filteredMovies.size());
	}

}
