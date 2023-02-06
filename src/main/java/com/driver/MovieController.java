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

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        movieservice.addMovie(movie);
        return new ResponseEntity<>("Successfully Added", HttpStatus.CREATED);
    }
    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        movieservice.addDirector(director);
        return new ResponseEntity<>("Successfully Added", HttpStatus.CREATED);
    }
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movie") String movie,@RequestParam("director") String director) {
        String msg=movieservice.addMovieDirectorPair(movie,director);
        if(msg=="Insufficient Information")
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(msg, HttpStatus.CREATED);

    }
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String moviename)
    {
        Movie movie = movieservice.getMovieByName(moviename);
        if(movie==null)
            return new ResponseEntity<>("Enter Listed Movie Name",HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(movie,HttpStatus.FOUND);
    }
    @GetMapping("get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String directorname)
    {
        Director director= movieservice.getDirectorByName(directorname);
        if(director==null)
            return new ResponseEntity<>("Director Not Found",HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(director,HttpStatus.FOUND);
    }
    @GetMapping("get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String directorname)
    {
        List<String> list=movieservice.getMoviesByDirectorName(directorname);
        if(list.isEmpty())
            return new ResponseEntity<>("Empty List",HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(list,HttpStatus.FOUND);
    }
    @GetMapping("get-all-movies")
    public ResponseEntity findAllMovies()
    {
        List<String> list=movieservice.findAllMovies();
        if(list.isEmpty())
            return new ResponseEntity<>("Empty List",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list,HttpStatus.FOUND);
    }
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("director") String directorName) {
        String msg=movieservice.deleteDirectorByName(directorName);
        return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors() {
        String msg=movieservice.deleteAllDirectors();
        return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
    }

}
