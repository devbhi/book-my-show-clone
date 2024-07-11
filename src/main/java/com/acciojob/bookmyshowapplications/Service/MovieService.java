package com.acciojob.bookmyshowapplications.Service;

import com.acciojob.bookmyshowapplications.Models.Movie;
import com.acciojob.bookmyshowapplications.Repository.MovieRepository;
import com.acciojob.bookmyshowapplications.Requests.UpdateMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(Movie movie){

        movie = movieRepository.save(movie);
        return "The movie has been saved to the DB with movieId"+movie.getMovieId();
    }

    public String updateMovieAttributes(UpdateMovieRequest movieRequest){

        Movie movie = movieRepository.findById(movieRequest.getMovieId()).get();

        double rating = movieRequest.getRating();
        double duration = movieRequest.getDuration();

        movie.setDuration(duration);
        movie.setRating(rating);

        movieRepository.save(movie);
        return "Attributes are modified";


    }

}
