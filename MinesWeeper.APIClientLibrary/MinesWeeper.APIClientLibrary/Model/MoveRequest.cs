using System;
using System.Collections.Generic;
using System.Text;

namespace MinesWeeper.APIClientLibrary.Model
{
    public class MoveRequest
    {
        public long id { set; get; }

        public int xPosition { set; get; }

        public int yPosition { set; get; }

        public bool flag { set; get; }
    }
}
