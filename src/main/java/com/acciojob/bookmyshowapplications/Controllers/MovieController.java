package com.acciojob.bookmyshowapplications.Controllers;

import com.acciojob.bookmyshowapplications.Models.Movie;
import com.acciojob.bookmyshowapplications.Service.MovieService;
import com.acciojob.bookmyshowapplications.Requests.UpdateMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addMovie")
    public String addMovie(@RequestBody Movie movie){

        String response = movieService.addMovie(movie);
        return response;
    }


    @PutMapping("/updateMovieAttributes")
    public String updateMovieAttributes(@RequestBody UpdateMovieRequest movieRequest){
                //You have made sure that only relevant attributes
                //are expose to the client

        String result = movieService.updateMovieAttributes(movieRequest);
        return result;

    }




}
