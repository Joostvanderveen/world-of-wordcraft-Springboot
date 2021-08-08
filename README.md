# world-of-wordcraft-Springboot
Game of worldwordhero's

### How to start the app:
Run the **WorldOfWordcraftApplication** class

### How to view the app:
The application is configured to use port 9080 to call on the endpoints use url: <br>
http://localhost:9080/

### list of endpoints:
**GET**     `api/{language}/count`
Get the size of the word-pair list for given language

**GET**     `api/{language}/random` 
Get random word-pair for given language

**PUT**     `api/{language}/word`
Add a WordPair to specified language list

**GET**     `api/{language}/list`
Get whole list for given language

**PUT**       `api/{language}/list`
Add a WordPair list to specified language list

###List of Languages:
`EN` this will load the Dutch questions with English answers <br>
`DE` this will load the Dutch questions with German answers