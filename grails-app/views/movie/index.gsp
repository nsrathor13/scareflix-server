<!DOCTYPE html>
<html lang="en">

<head>

    <meta name="layout" content="main"/>

    <g:if test="${foundResults}">
        <title>Scareflix - ${movieInfo.movie_title} (${movieInfo.movie_year})</title>
    </g:if>
    <g:else>
        <title>Scareflix - Movie Not Found</title>
    </g:else>

</head>

<body>

        <div class="row">

            <div class="col-md-9">
                <g:if test="${foundResults}">
                    <h3>${movieInfo.movie_title} (${movieInfo.movie_year})</h3>
                    <ul>
                        <li><b>Title:</b> ${movieInfo.movie_title}</li>
                        <li><b>Year:</b> ${movieInfo.movie_year}</li>
                        <li><b>Rating:</b> ${movieInfo.movie_rating} / 10</li>
                        <li><b>Director:</b> ${movieInfo.movie_director}</li>
                        <g:if test="${foundActors}">
                            <li>
                                <b>Actors:</b>
                                <ul>
                                    <g:each in="${actorInfo}" var="actor">
                                        <li>${actor.actor_name}</li>
                                    </g:each>
                                </ul>
                            </li>
                        </g:if>
                        <g:if test="${foundGenres}">
                            <li>
                                <b>Genres:</b>
                                <ul>
                                    <g:each in="${genreInfo}" var="genre">
                                        <li>${genre.genre_name}</li>
                                    </g:each>
                                </ul>
                            </li>
                        </g:if>
                        <g:if test="${foundRelatedMovies}">
                            <li>
                                <b>Related Movies:</b>
                                <ul>
                                    <g:each in="${relatedMovies}" var="movie">
                                        <li>
                                            <a href="/movie?id=${movie.movie_id}">
                                                ${movie.movie_title} (${movie.movie_year})
                                            </a>
                                        </li>
                                    </g:each>
                                </ul>
                            </li>
                        </g:if>
                    </ul>
                </g:if>
                <g:else>
                    <div class="alert alert-danger" role="alert">
                        <p>Movie not found.</p>
                    </div>
                </g:else>
            </div>

        </div>
</body>

</html>
