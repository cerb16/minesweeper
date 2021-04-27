import { GameStatus } from "./gameStatus.enum";
import { User } from "./user";

export class Game {
    id: number;
    user: User;
    gameStatus: GameStatus;
    rows: number;
    columns: number;
    mines: number;
    pause: boolean;
    gameStartDate: Date;
    lastStartDate: Date;
    lastMoveDate: Date;
    gameTime: number;
}
