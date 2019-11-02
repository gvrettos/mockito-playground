package testing.mockito.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

import testing.mockito.service.MoviesService;
import utils.MoviesDataGenerator;

// 1. Mocks are objects that simulate the real objects, 
//    unlike Stubs that are full replacements of the real objects during testing.
// 2. They can be dynamically created during runtime for each test scenario, through code.
// 3. They can focus on mocking only the exact method we want to test.
// 4. They offer more functionality than stubbing (e.g. they verify method calls).
public class MoviesBusinessImplMockitoTest {
	
	@Test
	public void getMoviesWhenUsingMockito() {
		MoviesService moviesServiceMock = mock(MoviesService.class);
		when(moviesServiceMock.getMovies()).thenReturn(MoviesDataGenerator.generateMovies());		
		
		MoviesBusinessImpl moviesBusinessImpl = new MoviesBusinessImpl(moviesServiceMock);
		List<String> filterdMovies = moviesBusinessImpl.getMoviesRelatedToDie();
		assertEquals(3, filterdMovies.size());
	}
	
}