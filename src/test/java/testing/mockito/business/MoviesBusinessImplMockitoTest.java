package testing.mockito.business;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import testing.mockito.service.MoviesService;
import utils.MoviesDataGenerator;

// 1. Mocks are objects that simulate the real objects, 
//    unlike Stubs that are full replacements of the real objects during testing.
// 2. They can be dynamically created during runtime for each test scenario, through code.
// 3. They can focus on mocking only the exact method we want to test.
// 4. They offer more functionality than stubbing (e.g. they verify method calls).
public class MoviesBusinessImplMockitoTest {
	
	@Test
	public void getMoviesRelatedToDieShouldBeThreeWhenUsingMockito() {
		MoviesService moviesServiceMock = mock(MoviesService.class);
		when(moviesServiceMock.getMovies()).thenReturn(MoviesDataGenerator.generateMovies());		
		
		MoviesBusinessImpl moviesBusinessImpl = new MoviesBusinessImpl(moviesServiceMock);
		List<String> filterdMovies = moviesBusinessImpl.getMoviesRelatedToDie();
		assertEquals(3, filterdMovies.size());
	}
	
	@Test
	public void getMoviesRelatedToDieShouldBeThreeWhenUsingMockitoBDD() {
		MoviesService moviesServiceMock = mock(MoviesService.class);
		MoviesBusinessImpl moviesBusinessImpl = new MoviesBusinessImpl(moviesServiceMock);
		
		// given
		given(moviesServiceMock.getMovies()).willReturn(MoviesDataGenerator.generateMovies());
		
		// when
		List<String> filterdMovies = moviesBusinessImpl.getMoviesRelatedToDie();
		
		// then
		assertEquals(3, filterdMovies.size());
	}
	
	@Test
	public void deleteMovieShouldBeCalledOnceWhenMovieIsUnofficialUsingMockito() {
		MoviesService moviesServiceMock = mock(MoviesService.class);
		MoviesBusinessImpl moviesBusinessImpl = new MoviesBusinessImpl(moviesServiceMock);
		
		// given 
		given(moviesServiceMock.getMovies()).willReturn(MoviesDataGenerator.generateMovies());
		
		// when
		moviesBusinessImpl.deleteMoviesNotOfficialForTheJamesBondFranchise();
		
		// then verify how many times deleteMovie() was called
		verify(moviesServiceMock, Mockito.times(1)).deleteMovie("Casino royale (1967)");
		// equivalent to verify(mock, Mockito.times(1))
		verify(moviesServiceMock).deleteMovie("Casino royale (1967)");
		
		verify(moviesServiceMock, Mockito.never()).deleteMovie("A view to a kill");
		verify(moviesServiceMock, Mockito.never()).deleteMovie("Casino royale");
	}
	
	@Test
	public void deleteMovieShouldBeCalledOnceWhenMovieIsUnofficialUsingMockitoBDD() {
		MoviesService moviesServiceMock = mock(MoviesService.class);
		MoviesBusinessImpl moviesBusinessImpl = new MoviesBusinessImpl(moviesServiceMock);
		
		// given 
		given(moviesServiceMock.getMovies()).willReturn(MoviesDataGenerator.generateMovies());
		
		// when
		moviesBusinessImpl.deleteMoviesNotOfficialForTheJamesBondFranchise();
		
		// then verify how many times deleteMovie() was called
		then(moviesServiceMock).should().deleteMovie("Casino royale (1967)");
		then(moviesServiceMock).should(Mockito.never()).deleteMovie("A view to a kill");
		then(moviesServiceMock).should(Mockito.never()).deleteMovie("Casino royale");
	}
	
	// 1. Declare Argument Captor - stringArgumentCaptor
	// 2. Define Argument Captor on specific method call - deleteMovie()
	// 3. Capture the argument - during then/assertions
	@Test
	public void deleteMovieShouldBeCalledOnceWhenMovieIsUnofficialUsingMockitoBDDAndArgumentCaptor() {
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		
		MoviesService moviesServiceMock = mock(MoviesService.class);
		MoviesBusinessImpl moviesBusinessImpl = new MoviesBusinessImpl(moviesServiceMock);
		
		// given 
		given(moviesServiceMock.getMovies()).willReturn(MoviesDataGenerator.generateMovies());
		
		// when
		moviesBusinessImpl.deleteMoviesNotOfficialForTheJamesBondFranchise();
		
		// then verify how many times deleteMovie() was called
		then(moviesServiceMock).should(times(3)).deleteMovie(stringArgumentCaptor.capture());
		
		// more then (assertions)
		assertThat(stringArgumentCaptor.getAllValues().size(), is(3));
		assertThat(stringArgumentCaptor.getAllValues(), hasItem("Casino royale (1967)"));
		assertThat(stringArgumentCaptor.getAllValues(), not(hasItem("Casino royale")));
	}
	
}
