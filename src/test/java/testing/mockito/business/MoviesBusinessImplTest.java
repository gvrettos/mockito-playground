package testing.mockito.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import testing.mockito.service.MoviesService;
import testing.mockito.service.MoviesServiceStub;

public class MoviesBusinessImplTest {
	
	// Stub approach disadvantages:
	// 1. Dynamic conditions: testing different scenarios makes the code too complicated
	// 2. Service definition: Creating separate classes just for testing and maintaining them 
	// 	  if the original class is updated
	@Test
	public void test_getMovies_usingStub() {
		MoviesService moviesServiceStub = new MoviesServiceStub();
		MoviesBusinessImpl moviesBusinessImpl = new MoviesBusinessImpl(moviesServiceStub);
		List<String> filteredMovies = moviesBusinessImpl.getMoviesRelatedToDie();
		assertEquals(3, filteredMovies.size());
	}
	
	// 1. Mocks are objects that simulate the real objects, 
	// unlike Stubs that are full replacements of the real objects during testing.
	// 2. They can be dynamically created during runtime for each test scenario, through code.
	// 3. They can focus on mocking only the exact method we want to test.
	// 4. They offer more functionality than stubbing (e.g. they verify method calls).
	@Test
	public void test_getMovies_usingMockito() {
		MoviesBusinessImpl moviesBusinessImpl = new MoviesBusinessImpl(setUpMoviesServiceGetMoviesMock());
		List<String> filterdMovies = moviesBusinessImpl.getMoviesRelatedToDie();
		assertEquals(3, filterdMovies.size());
	}
	
	private MoviesService setUpMoviesServiceGetMoviesMock() {
		MoviesService moviesServiceMock = mock(MoviesService.class);
		List<String> allMovies = Arrays.asList(
				"Dr. No",
				"From Russia with love",
				"Goldfinger",
				"Thunderball",
				"You only live twice",
				"On Her Majesty's secret service",
				"Diamonds are forever",
				"Live and let die",
				"The man with the goldern gun",
				"The spy who loved me",
				"Moonraker",
				"For your eyes only",
				"Octopussy",
				"A view to a kill",
				"The living daylights",
				"License to kill",
				"Goldeneye",
				"Tomorrow never dies",
				"The world is not enough",
				"Die another day",
				"Casino royale",
				"Quantum of solace",
				"Skyfall",
				"Spectre",
				"No time to die"
		);
		when(moviesServiceMock.getMovies()).thenReturn(allMovies);
		
		return moviesServiceMock;
	}
	
}
