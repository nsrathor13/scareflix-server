package scareflix

import groovy.json.*

class MovieController {

    def index() {
        def movieBaseUrl = "http://localhost:8001/movies/id/"
        def actorBaseUrl = "http://localhost:8001/actors/movie_id/"
        def foundResults = false
        def foundActors  = false
        def movieInfo
        def actorInfo

        // Get the movie id
        def movieID = params.id

        // Get the movie info
        if (movieID != null) {
            def movieFeedUrl = movieBaseUrl + movieID
            def actorFeedUrl = actorBaseUrl + movieID

            // Run the query and process its results
            def movieText = ("curl " + movieFeedUrl).execute().text
            def actorText = ("curl " + actorFeedUrl).execute().text
            if (!movieText.equals("") && movieText != null) {
                movieInfo = new JsonSlurper().parseText(movieText)
                actorInfo = new JsonSlurper().parseText(actorText)
                print actorInfo

                // Check if a movie was correctly found
                if (movieInfo != null) {
                    foundResults = true
                }

                // Check if any actors were found for the movie
                if (actorInfo.size() > 0) {
                    foundActors = true
                }
            }
        }

        [foundResults:foundResults, movieInfo:movieInfo, foundActors:foundActors, actorInfo:actorInfo]
    }
}
