using System;
using System.Collections.Generic;
using System.Text;

namespace MinesWeeper.APIClientLibrary.Model
{
    public class MoveResponse
    {
        public long gameId { set; get; }
        public string gameStatus { set; get; }
        public CellResponse[][] gameField { set; get; }
        public string[] gameFieldView { set; get; }
        public long gameTime { set; get; }
    }
}
