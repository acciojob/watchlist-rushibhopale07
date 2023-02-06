package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movierepo;

    public void addMovie(Movie movie){
        movierepo.addMovie(movie);
    }
    public void addDirector(Director director){
        movierepo.addDirector(director);
    }
    public String addMovieDirectorPair(String movie,String director) {
        return movierepo.addMovieDirectorPair(movie,director);
    }
    public Movie getMovieByName(String moviename)
    {
        return movierepo.getMovieByName(moviename);
    }
    public Director getDirectorByName(String directorname)
    {
        return movierepo.getDirectorByName(directorname);
    }
    public List getMoviesByDirectorName(String directorname)
    {
        return movierepo.getMoviesByDirectorName(directorname);
    }
    public List findAllMovies()
    {
        return movierepo.findAllMovies();
    }
    public String deleteDirectorByName(String directorName) {
        movierepo.deleteDirectorByName(directorName);
        return "Success deleted";
    }
    public String deleteAllDirectors()
    {
        movierepo.deleteAllDirectors();
        return "Success Deleted";
    }
}
