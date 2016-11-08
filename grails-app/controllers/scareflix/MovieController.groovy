package scareflix

import groovy.json.*

class MovieController {

    def index() {
        def movieBaseUrl = "http://localhost:8001/movies/id/"
        def actorBaseUrl = "http://localhost:8001/actors/movie_id/"
        def relatedMoviesBaseUrl = "http://localhost:8001/movies/related/"
        def genresBaseUrl = "http://localhost:8001/genres/movie_id/"
        def foundResults = false
        def foundActors  = false
        def foundRelatedMovies = false
        def foundGenres = false
        def movieInfo
        def actorInfo
        def relatedMovies
        def genreInfo

        // Get the movie id
        def movieID = params.id

        // Get the movie info
        if (movieID != null) {
            def movieFeedUrl = movieBaseUrl + movieID
            def actorFeedUrl = actorBaseUrl + movieID
            def relatedMoviesFeedUrl = relatedMoviesBaseUrl + movieID
            def genresFeedUrl = genresBaseUrl + movieID

            // Run the queries and process their results
            def movieText = ("curl " + movieFeedUrl).execute().text
            if (!movieText.equals("") && movieText != null) {
                movieInfo = new JsonSlurper().parseText(movieText)

                // Check if a movie was correctly found
                if (movieInfo != null) {
                    foundResults = true
                }

                def actorText = ("curl " + actorFeedUrl).execute().text
                if(!actorText.equals("") && actorText != null) {
                    actorInfo = new JsonSlurper().parseText(actorText)

                    // Check if any actors were found for the movie
                    if (actorInfo.size() > 0) {
                        foundActors = true
                    }
                }

                def relatedMoviesText = ("curl " + relatedMoviesFeedUrl).execute().text
                if(!relatedMoviesText.equals("") && relatedMoviesText != null) {
                    relatedMovies = new JsonSlurper().parseText(relatedMoviesText)

                    // Check if any related movies were found
                    if (relatedMovies.size() > 0) {
                        foundRelatedMovies = true
                    }
                }

                def genresText = ("curl " + genresFeedUrl).execute().text
                if(!genresText.equals("") && genresText != null) {
                    genreInfo = new JsonSlurper().parseText(genresText)

                    // Check if any genres were found
                    if (genreInfo.size() > 0) {
                        foundGenres = true
                    }
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
        , foundGenres:foundGenres
        , genreInfo:genreInfo
        ]
    }
}
