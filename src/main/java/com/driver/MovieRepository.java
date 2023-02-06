package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    Map<String,Movie> movieMap= new HashMap<>();
    Map<String,Director>directorMap=new HashMap<>();
    Map<String, List<String>> directorMovieMap= new HashMap<>();

    public void addMovie(Movie movie){
        movieMap.put(movie.getName(),movie);
    }
    public void addDirector(Director director){
        directorMap.put(director.getName(),director);
    }
    public String addMovieDirectorPair(String movie,String director) {
        if (movieMap.containsKey(movie) && directorMap.containsKey(director))
        {
            List <String> movielist = new ArrayList<>();
            if(directorMovieMap.containsKey(director))
                movielist=directorMovieMap.get(director);

                movielist.add(movie);
            directorMovieMap.put(movie,movielist);
            return "Added";
        }
        else
            return "Insufficient Information";
    }
    public Movie getMovieByName(String moviename)
    {
        if(!movieMap.containsKey(moviename))
            return null;
        return movieMap.get(moviename);
    }
    public Director getDirectorByName(String directorname)
    {
        if(!directorMap.containsKey(directorname))
            return null;
        return directorMap.get(directorname);
    }
    public List<String> getMoviesByDirectorName(String directorname)
    {
        List<String> movieList=new ArrayList<>();
        if(directorMovieMap.containsKey(directorname))movieList=directorMovieMap.get(directorname);
        return movieList;
    }
    public List<String> findAllMovies()
    {
        return new ArrayList(movieMap.keySet());
    }

    public void deleteDirectorByName(String directorName)
    {
        if(directorMap.containsKey(directorName))
        {
            directorMap.remove(directorName); //delete director
        }
        List<String> movielist=new ArrayList<>();
        if(directorMovieMap.containsKey(directorName))
        {

            movielist=directorMovieMap.get(directorName);
            for(String movie:movielist)
            {
                if (movieMap.containsKey(movie))
                    movieMap.remove(movie);
            }
            directorMovieMap.remove(directorName); //delete all the records of that director
        }
    }
    public void deleteAllDirectors()
    {
        for (String director:directorMovieMap.keySet() ) {
            for(String movie:directorMovieMap.get(director))
            {
                if(movieMap.containsKey(movie))
                movieMap.remove(movie);
            }

        }
    }

}

