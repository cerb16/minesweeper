using System;
using System.Collections.Generic;
using System.Text;

namespace MinesWeeper.APIClientLibrary.Model
{
    public class GameResponse
    {
    
        public long id { set; get; }

        public User ser { set; get; }

        public GameStatus gameStatus { set; get; }

        public int rows { set; get; }

        public int columns { set; get; }

        public int mines { set; get; }  

        public bool pause { set; get; }

        public DateTime gameStarDate { set; get; }

        public DateTime lastRestartDate { set; get; }

        public DateTime lastMoveDate { set; get; }

        public long gameTime { set; get; }
    }

    
}
