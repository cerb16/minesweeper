import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { User} from 'src/app/models/user';

const API_URL_LOCAL = "http://localhost:8002/minesweeper/api/user/";
const API_URL_AWS = "http://ec2-18-217-192-129.us-east-2.compute.amazonaws.com:8002/minesweeper/api/user/";

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient) { }
  createUser(user:User, isLocal: boolean) : Observable<number>{
    if(isLocal){
      return this.http.post<number>(API_URL_LOCAL,user);
    }
    else{
      return this.http.post<number>(API_URL_AWS,user);
    }
  }
}
