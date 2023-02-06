package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {

    HashMap<String, Movie> movieDb = new HashMap<>();
    HashMap<String, Director> directorDb = new HashMap<>();
    HashMap<Director, List<String>> db = new HashMap<>();

    public String addMovie(Movie movie){
        String movieName = movie.getName();
        movieDb.put(movieName,movie);
        return "success";
    }

    public String addDirector(Director director){
        String directorName = director.getName();
        directorDb.put(directorName, director);
        return "success";
    }

    public String addMovieDirectorPair(String movieName, String directorName){
        if(movieDb.containsKey(movieName) && directorDb.containsKey(directorName)){
            Director director = directorDb.get(directorName);
            List<String> movieList = db.getOrDefault(director, new ArrayList<>());
            movieList.add(movieName);
            db.put(director, movieList);
            return "success";
        }
        return "no existing movies or directors";
    }

    public Movie getMovieByName(String movieName){
        if(movieDb.containsKey(movieName)){
            return movieDb.get(movieName);
        }
        return null;
    }

    public Director getDirectorByName(String directorName){
        if(directorDb.containsKey(directorName)){
            return directorDb.get(directorName);
        }
        return null;
    }

    public List<String> getMoviesByDirectorName(String directorName){
        Director director = null;
        if(directorDb.containsKey(directorName)){
            director = directorDb.get(directorName);
        }
        if(db.containsKey(director)){
            return db.get(director);
        }
        return new ArrayList<>();
    }

    public List findAllMovies(){
        List<String> movieList = new ArrayList<>();
        for(String movie : movieDb.keySet()){
            movieList.add(movieDb.get(movie).getName());
        }
        return movieList;
    }

    public String deleteDirectorByName(String directorName){
        if(!directorDb.containsKey(directorName)){
            return "No director in the Database";
        }
        Director director = directorDb.get(directorName);
        if(db.containsKey(director)){
            List<String> movieList = db.get(director);
            db.remove(director);
            for(String movie : movieList){
                movieDb.remove(movie);
            }
        }
        directorDb.remove(directorName);
        return "Director removed from Database";
    }

    public String deleteAllDirectors(){
        for(Director director : directorDb.values()){
            List<String>movieList = new ArrayList<>();
            if (db.containsKey(director)) {
                movieList = db.get(director);
            }
            for(String movie : movieList){
                movieDb.remove(movie);
            }
        }
        directorDb.clear();
        db.clear();
        return "removed all directors and movies related to directors";
    }

}