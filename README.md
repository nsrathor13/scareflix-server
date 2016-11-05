# Scareflix Server
The website server for Scareflix, a horror move database website.

## Usage
First you should make sure that the movie database api is online, then you can run the server.

```bash
$ cd movie-database-api
$ stack exec movie-database-api -- serve "data/test.db" &
$ cd ..
$ cd scareflix-server
$ grails run-app
```

To change the port that the server is running on, edit the `grails-app/conf/application.yml` file and change the port number in the following section.

```yml
server:
    port: 9999
```
