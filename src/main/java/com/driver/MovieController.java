package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieservice;

    @PostMapping("/add_movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        movieservice.addMovie(movie);
        return new ResponseEntity<>("Successfully Added", HttpStatus.CREATED);
    }
    @PostMapping("/add_director")
    public ResponseEntity addDirector(@RequestBody Director director){
        movieservice.addDirector(director);
        return new ResponseEntity<>("Successfully Added", HttpStatus.CREATED);
    }
    @PutMapping("/add_moviedirector")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movie") String movie,@RequestParam("director") String director) {
        String msg=movieservice.addMovieDirectorPair(movie,director);
        if(msg=="Insufficient Information")
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
    @GetMapping("/getMovie/{movie}")
    public ResponseEntity getMovieByName(@PathVariable("movie") String moviename)
    {
        Movie movie = movieservice.getMovieByName(moviename);
        if(movie==null)
            return new ResponseEntity<>(movie,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(movie,HttpStatus.FOUND);
    }
    @GetMapping("/getdirector/{director}")
    public ResponseEntity getDirectorByName(@PathVariable("director") String directorname)
    {
        Director director= movieservice.getDirectorByName(directorname);
        if(director==null)
            return new ResponseEntity<>("Director Not Found",HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(director,HttpStatus.FOUND);
    }
    @GetMapping("list_of_movie")
    public ResponseEntity getMoviesByDirectorName(@RequestParam("director") String directorname)
    {
        List<String> list=movieservice.getMoviesByDirectorName(directorname);
        if(list.isEmpty())
            return new ResponseEntity<>("Empty List",HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(list,HttpStatus.FOUND);
    }
    @GetMapping("list_of_movies")
    public ResponseEntity findAllMovies()
    {
        List<String> list=movieservice.findAllMovies();
        if(list.isEmpty())
            return new ResponseEntity<>("Empty List",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list,HttpStatus.FOUND);
    }


}
