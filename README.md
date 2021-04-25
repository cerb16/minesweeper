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

