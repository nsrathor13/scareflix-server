<!DOCTYPE html>
<html lang="en">

<head>

    <meta name="layout" content="main"/>

    <title>Scareflix - Search</title>

</head>

<body>

        <div class="row">

            <div class="col-md-3">
                <p class="lead">Search Results</p>
                <div class="list-group">
                    <a href="#" class="list-group-item active">Category 1</a>
                    <a href="#" class="list-group-item">Category 2</a>
                    <a href="#" class="list-group-item">Category 3</a>
                </div>
            </div>

            <div class="col-md-9">
                <g:if test="${foundResults}">
                    <ul>
                        <g:each in="${resultsList}" var="movie">
                            <li>
                                <a href="movie?id=${movie.movie_id}">
                                    ${movie.movie_title}
                                </a>
                            </li>
                        </g:each>
                    </ul>
                </g:if>
                <g:else>
                    <p>No results found.</p>
                </g:else>
            </div>

        </div>
</body>

</html>
