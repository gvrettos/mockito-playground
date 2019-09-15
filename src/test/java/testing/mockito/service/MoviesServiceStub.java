package testing.mockito.service;

import java.util.Arrays;
import java.util.List;

public class MoviesServiceStub implements MoviesService {

	@Override
	public List<String> getMovies() {
		return Arrays.asList(
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
	}

}
