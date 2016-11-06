package scareflix

import groovy.json.*

class MovieController {

    def index() {
        def movieBaseUrl = "http://localhost:8001/movies/id/"
        def actorBaseUrl = "http://localhost:8001/actors/movie_id/"
        def relatedMoviesBaseUrl = "http://localhost:8001/movies/related/"
        def foundResults = false
        def foundActors  = false
        def foundRelatedMovies = false
        def movieInfo
        def actorInfo
        def relatedMovies

        // Get the movie id
        def movieID = params.id

        // Get the movie info
        if (movieID != null) {
            def movieFeedUrl = movieBaseUrl + movieID
            def actorFeedUrl = actorBaseUrl + movieID
            def relatedMoviesFeedUrl = relatedMoviesBaseUrl + movieID

            // Run the queries and process their results
            def movieText = ("curl " + movieFeedUrl).execute().text
            def actorText = ("curl " + actorFeedUrl).execute().text
            def relatedMoviesText = ("curl " + relatedMoviesFeedUrl).execute().text
            if (!movieText.equals("") && movieText != null) {
                movieInfo = new JsonSlurper().parseText(movieText)
                actorInfo = new JsonSlurper().parseText(actorText)
                relatedMovies = new JsonSlurper().parseText(relatedMoviesText)

                // Check if a movie was correctly found
                if (movieInfo != null) {
                    foundResults = true
                }

                // Check if any actors were found for the movie
                if (actorInfo.size() > 0) {
                    foundActors = true
                }

                // Check if any related movies were found
                if (relatedMovies.size() > 0) {
                    foundRelatedMovies = true
                }
            }
        }

        [
          foundResults:foundResults
        , movieInfo:movieInfo
        , foundActors:foundActors
        , actorInfo:actorInfo
        , foundRelatedMovies:foundRelatedMovies
        , relatedMovies:relatedMovies
        ]
    }
}
