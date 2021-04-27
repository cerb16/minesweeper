import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Game} from 'src/app/models/game';
import { GameRequest } from 'src/app/models/gameRequest';
import { MoveRequest } from 'src/app/models/moveRequest';
import { Move } from 'src/app/models/move';




const API_URL_LOCAL = "http://localhost:8002/minesweeper/api/game/";
const API_URL_AWS = "http://ec2-18-217-192-129.us-east-2.compute.amazonaws.com:8002/minesweeper/api/game/";


@Injectable({
  providedIn: 'root'
})
export class GameServiceService {

  constructor(private http: HttpClient) { }

   
  newGame(game:GameRequest, isLocal: boolean) : Observable<Game>{
    if(isLocal){
      return this.http.post<Game>(API_URL_LOCAL,game);
    }
    else{
      return this.http.post<Game>(API_URL_AWS,game);
    }
  }

  pauseResume(gameId:number, isLocal: boolean):Observable<Game>{
  if(isLocal){
      return this.http.put<Game>(API_URL_LOCAL + gameId + "/pause-resume",null);
    }
    else{
      return this.http.put<Game>(API_URL_AWS + gameId + "/pause-resume",null);
    }
  }

  move(gameId:number, move:MoveRequest, isLocal: boolean):Observable<Move>{
    if(isLocal){
        return this.http.put<Move>(API_URL_LOCAL + gameId + "/move",move);
      }
      else{
        return this.http.put<Move>(API_URL_AWS + gameId + "/move",move);
      }
    }

  getGameField(gameId:number, isLocal: boolean) :Observable<Move>{
    if(isLocal){
      return this.http.get<Move>(API_URL_LOCAL + gameId + "/gamefield");
    }
    else{
      return this.http.get<Move>(API_URL_AWS + gameId + "/gamefield");
    }
  }

}
