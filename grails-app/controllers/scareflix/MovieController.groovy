package scareflix

import groovy.json.*

class MovieController {

    def index() {
        def baseUrl = "http://localhost:8001/movies/id/"
        def foundResults = false
        def movieInfo

        // Get the movie id
        def movieID = params.id

        // Get the movie info
        if (movieID != null) {
            def feedUrl = baseUrl + movieID

            // Run the query and process its results
            def text = ("curl " + feedUrl).execute().text
            if (!text.equals("") && text != null) {
                movieInfo = new JsonSlurper().parseText(text)

                // Check if a movie was correctly found
                if (movieInfo != null) {
                    foundResults = true
                }
            }
        }

        [foundResults:foundResults, movieInfo:movieInfo]
    }
}
