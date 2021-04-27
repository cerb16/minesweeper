import { Cell } from "./cell";
import { GameStatus } from "./gameStatus.enum";

export class Move {
    gameId: number;
    gameStatus: GameStatus;
    gameField: Cell[][];
    gameTime:number;
}
