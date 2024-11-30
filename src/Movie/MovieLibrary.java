package Movie;

import java.util.List;

public class MovieLibrary {
    private static List<Movie> movies;

    public MovieLibrary(List<Movie> movies) {
        MovieLibrary.movies = movies;
    }

    public static boolean addMovie (Movie movie) {
        return movies.add(movie);
    }

    public static boolean deleteMovie (Movie movie) {
        return movies.remove(movie);
    }

    public static boolean hasMovie(Movie movie) {
        return movies.contains(movie);
    }

    public static List<Movie> getMovies () {
        return movies;
    }
}
