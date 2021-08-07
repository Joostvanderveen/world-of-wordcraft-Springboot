# world-of-wordcraft-Springboot
Game of worldwordhero's

### How to start the app:
Run the **WorldOfWordcraftApplication** class

### How to view the app:
The application is configured to use port 9080 to call on the endpoints use url: <br>
http://localhost:9080/

### list of endpoints:
`api/{gameType}/count`
Get the size of the word-pair list for given game type

`api/{gameType}/random` 
Get random word-pair for given game type

`api/{gameType}/list`
Get whole list for given game type

###List of GameType's:
`EN` this will load the Dutch questions with English answers <br>
`DE` this will load the Dutch questions with German answers