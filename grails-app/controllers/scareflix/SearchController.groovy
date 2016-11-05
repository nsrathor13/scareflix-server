package scareflix

import groovy.json.*

class SearchController {

    def index() {
        def baseUrl = "http://localhost:8001/movies/"
        def foundResults = false
        def resultsList

        // Get the search term
        def title = params.title
        def actor = params.actor

        // Perform the search
        if (title != null || actor != null) {
            def feedUrl

            // Construct the API request URL.
            if (title != null) {
                feedUrl = baseUrl + "title/" + title
            } else {
                feedUrl = baseUrl + "actor/" + actor
            }

            // Run the query and process its results
            def text = ("curl " + feedUrl).execute().text
            if (!text.equals("")) {
                resultsList = new JsonSlurper().parseText(text)
                foundResults = true
            }
        }

        [foundResults:foundResults, resultsList:resultsList]
    }
}
