using System;
using System.Collections.Generic;
using System.Text;

namespace MinesWeeper.APIClientLibrary.Model
{
    public class GameRequest
    {
        public long? id { set; get; }

        public long userId { set; get; }

        public int rows { set; get; }

        public int columns { set; get; }

        public int mines { set; get; }

        public bool pause { set; get; }
    }
}
