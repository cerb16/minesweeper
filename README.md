# minesweeper
minesweeper api

# Overview
This repository contains an API which expose all the endpoints requiere to play the game "minesweeper".

# Steps to play:
1- Create or select your user.<br />
2- Create a new game from scratch or select one of your previous and active games.<br />
3- Select one cell by cellId or by (X,Y) values to perform one of this actions.<br />
  a- Flag: this will return the game field with the values for revealed cells and the flag ones, to do this you need to send the value "flag" as true.<br />
  b- Revealed: this will reveal this cell with this possible results: <br />
    i- If is a mine: You lose the game, return the revealed game field.<br />
    ii- If not mine but adjacent to one: the game will continue as InProcess, return the game field with the selected cell revealed and show the value for adjacent mines to it, and the gamefield updated as well.<br />
    iii- If not mine and not adjacent mines: the game will continue as InProcess, return the game field with the selected cell and all the cell around it with no adjacent mines as  revealed  and show the value for adjacent mines to them, and the gamefield updated as well.<br />
    
4- Win: you win when all no mine cells are being revealed <br />

# Technical decisions
1- Use list instead of arrays: I used list for some logic to avoid many transformation between list to arrays to modified and save data.<br />
2- Recursive funtion to reveal cells when it is not adjacent to a mine: best aproach to execute this kind of requirement.<br />
3- Allow user to play a cell by id or (X,Y) values: flexibility for ui implementations, developers use differents data structures in UI.<br />

# Tech stack
Lenguage : Java 8<br />
Framework: Spring boot 2.4.5,JPA<br /> 
Project build type: Maven<br />
DB: Mysql<br />
API-ui: Swagger<br />

# API Endpoints
User-Controller
1- Get /minesweeper/api/user/ Get all de users <br />
2- Put /minesweeper/api/user/ Update one user<br />
3- Post /minesweeper/api/user/ Create user <br />
4- Get /minesweeper/api/user/{useriId} get a user by Id<br />
5- Get /minesweeper/api/user/username?username={username} get a user by userName<br />
6- Delete /minesweeper/api/user/{userId} delete a user by userId<br />

Game-controller
1- Put /minesweeper/api/game/{gameId}/pause-resume: Pause the game <br />
2- Put /minesweeper/api/game/{gameId}/move: Generate a move this can be reveal a cell or flag it<br />
3- Post /minesweeper/api/game/ Create a new game and the game field <br />
4- Get /minesweeper/api/game/{gameId} get a game by Id<br />
5- Get /minesweeper/api/game/{gameId}/gamefield get the updated gamefield and information about the game like status and game time<br />
6- Get /minesweeper/api/game/ get all the games <br />
7- Get /minesweeper/api/game/{userId} get all the games for a given user<br />
8- Delete /minesweeper/api/user/{gameId} delete a game by gameId<br />


# Accesing the API:
1- Create your own client pointing to http://{server}:8002/minesweeper/api/....<br />
2- Swagger-ui  http://{server}:8002/swagger-ui.html<br />
3- .Net client library: you can get the Nuget or dll for the library MinesWeeper.APIClientLibrary (.Net project in this repo)<br />

# AWS
This API was  deployed to ec2-18-217-192-129.us-east-2.compute.amazonaws.com (this is an EC2 instance), is pause till you request access (this because cost, I am out of free usage time), normaly I use elastic beanstalk to implement this kind of application but because cost I used a regular EC2 on demand instace 
