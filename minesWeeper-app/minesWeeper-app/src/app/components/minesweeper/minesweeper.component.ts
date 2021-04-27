import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Game } from 'src/app/models/game';
import { GameRequest } from 'src/app/models/gameRequest';
import { MoveRequest } from 'src/app/models/moveRequest';
import { Move } from 'src/app/models/move';
import { Cell } from 'src/app/models/cell';
import { User } from 'src/app/models/user';
import { GameStatus } from 'src/app/models/gameStatus.enum';
import { UserServiceService } from 'src/app/services/userService.service';
import { GameServiceService } from 'src/app/services/gameService.service';





@Component({
  selector: 'app-minesweeper',
  templateUrl: './minesweeper.component.html',
  styleUrls: ['./minesweeper.component.scss']
})
export class MinesweeperComponent implements OnInit {
  public game :Game;
  public gameRequest: GameRequest;
  public moveRequest: MoveRequest;
  public move: Move;
  public cell: Cell;
  public cells: Cell[][];
  public user: User;
  public userId: number;
  public isLocal:boolean;
  public isFlag: boolean;
  public gameStatus: any;

  constructor(private userService: UserServiceService, private gameService: GameServiceService) { }

  ngOnInit() {
    this.game = new Game;
    this.gameRequest= new GameRequest;
    this.moveRequest= new MoveRequest;
    this.move= new Move;
    this.cell= new Cell;
    this.user= new User;
    this.isLocal = true;
    this.isFlag = false;
  }

  newGame(){
    this.userService.createUser(this.user,this.isLocal).subscribe(id=>{
        this.userId = id;
        this.gameRequest.userId =this.userId;
        this.gameRequest.pause = false;
        this.gameService.newGame(this.gameRequest,this.isLocal).subscribe(game=>{
          this.game = game;
          this.gameStatus = game.gameStatus.valueOf;
          this.gameService.getGameField(this.game.id,this.isLocal).subscribe(move =>{
            this.move=move;
            this.cells = move.gameField;
          })
        })
       
        
    })
  }

  playCell(cell:Cell){
    this.moveRequest.xposition = cell.xposition;
    this.moveRequest.yposition = cell.yposition;
    this.moveRequest.flag = cell.flag;

    this.gameService.move(this.game.id, this.moveRequest ,this.isLocal).subscribe(move =>{
      this.move = move;
      this.cells = move.gameField;
      this.gameStatus = move.gameStatus.valueOf;
    })

  }

}
