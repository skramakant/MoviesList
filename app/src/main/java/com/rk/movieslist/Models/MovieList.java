package com.rk.movieslist.Models;

/**
 * Created by ramakant on 13/9/16.
 */
public class MovieList {

    private Search[] Search;

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public Search[] getSearches() {
        return Search;
    }

    public void setSearches(Search[] searches) {
        this.Search = searches;
    }

    private String totalResults;
    private String Response;

    public static class Search{
        private String Title;
        private String Year;

        public String getImdbID() {
            return imdbID;
        }

        public void setImdbID(String imdbID) {
            this.imdbID = imdbID;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getYear() {
            return Year;
        }

        public void setYear(String year) {
            Year = year;
        }

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }

        public String getPoster() {
            return Poster;
        }

        public void setPoster(String poster) {
            Poster = poster;
        }

        private String imdbID;
        private String Type;
        private String Poster;
    }
}
