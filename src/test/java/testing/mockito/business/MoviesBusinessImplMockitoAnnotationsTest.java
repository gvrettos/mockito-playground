package testing.mockito.business;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import testing.mockito.service.MoviesService;
import utils.MoviesDataGenerator;

// 1. Mocks are objects that simulate the real objects, 
//    unlike Stubs that are full replacements of the real objects during testing.
// 2. They can be dynamically created during runtime for each test scenario, through code.
// 3. They can focus on mocking only the exact method we want to test.
// 4. They offer more functionality than stubbing (e.g. they verify method calls).

@RunWith(MockitoJUnitRunner.class)
public class MoviesBusinessImplMockitoAnnotationsTest {
	
	@InjectMocks
	MoviesBusinessImpl moviesBusinessImpl;
	
	@Mock
	MoviesService moviesService;
	
	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;
	
	@Test
	public void getMoviesRelatedToDieShouldBeThreeWhenUsingMockito() {
		when(moviesService.getMovies()).thenReturn(MoviesDataGenerator.generateMovies());		
		
		List<String> filterdMovies = moviesBusinessImpl.getMoviesRelatedToDie();
		
		assertThat(filterdMovies, hasSize(3));
	}
	
	@Test
	public void getMoviesRelatedToDieShouldBeThreeWhenUsingMockitoBDD() {
		// given
		given(moviesService.getMovies()).willReturn(MoviesDataGenerator.generateMovies());
		
		// when
		List<String> filterdMovies = moviesBusinessImpl.getMoviesRelatedToDie();
		
		// then
		assertThat(filterdMovies, hasSize(3));
	}
	
	@Test
	public void deleteMovieShouldBeCalledOnceWhenMovieIsUnofficialUsingMockito() {
		// given 
		given(moviesService.getMovies()).willReturn(MoviesDataGenerator.generateMovies());
		
		// when
		moviesBusinessImpl.deleteMoviesNotOfficialForTheJamesBondFranchise();
		
		// then verify how many times deleteMovie() was called
		verify(moviesService, Mockito.times(1)).deleteMovie("Casino royale (1967)");
		// equivalent to verify(mock, Mockito.times(1))
		verify(moviesService).deleteMovie("Casino royale (1967)");
		
		verify(moviesService, Mockito.never()).deleteMovie("A view to a kill");
		verify(moviesService, Mockito.never()).deleteMovie("Casino royale");
	}
	
	@Test
	public void deleteMovieShouldBeCalledOnceWhenMovieIsUnofficialUsingMockitoBDD() {
		// given 
		given(moviesService.getMovies()).willReturn(MoviesDataGenerator.generateMovies());
		
		// when
		moviesBusinessImpl.deleteMoviesNotOfficialForTheJamesBondFranchise();
		
		// then verify how many times deleteMovie() was called
		then(moviesService).should().deleteMovie("Casino royale (1967)");
		then(moviesService).should(Mockito.never()).deleteMovie("A view to a kill");
		then(moviesService).should(Mockito.never()).deleteMovie("Casino royale");
	}
	
	// 1. Declare Argument Captor using @Captor annotation - stringArgumentCaptor
	// 2. Define Argument Captor on specific method call - deleteMovie()
	// 3. Capture the argument - during then/assertions
	@Test
	public void deleteMovieShouldBeCalledOnceWhenMovieIsUnofficialUsingMockitoBDDAndArgumentCaptor() {
		// given 
		given(moviesService.getMovies()).willReturn(MoviesDataGenerator.generateMovies());
		
		// when
		moviesBusinessImpl.deleteMoviesNotOfficialForTheJamesBondFranchise();
		
		// then verify how many times deleteMovie() was called
		then(moviesService).should(times(3)).deleteMovie(stringArgumentCaptor.capture());
		
		// more assertions
		assertThat(stringArgumentCaptor.getAllValues().size(), is(3));
		assertThat(stringArgumentCaptor.getAllValues(), hasItem("Casino royale (1967)"));
		assertThat(stringArgumentCaptor.getAllValues(), not(hasItem("Casino royale")));
		
		// more assertions to demonstrate some more Hamcrest Matchers
		assertThat(stringArgumentCaptor.getAllValues(), notNullValue());
		assertThat(stringArgumentCaptor.getAllValues(), not(is(emptyCollectionOf(String.class))));
		assertThat(stringArgumentCaptor.getAllValues(), hasSize(3));
		assertThat(
				stringArgumentCaptor.getAllValues(), 
				contains("Casino royale (1954)", "Casino royale (1967)", "Never say never again"));
		assertThat(stringArgumentCaptor.getAllValues(), not(contains("Casino royale (1967)")));
		assertThat(
				stringArgumentCaptor.getAllValues(), 
				containsInAnyOrder("Never say never again", "Casino royale (1967)", "Casino royale (1954)"));
		assertThat(stringArgumentCaptor.getAllValues(), not(containsInAnyOrder("Casino royale")));
		assertThat(stringArgumentCaptor.getAllValues(), not(everyItem(startsWith("Casino"))));
	}
	
}
