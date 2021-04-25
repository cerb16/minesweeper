using System;
using System.Collections.Generic;
using System.Text;

namespace MinesWeeper.APIClientLibrary.Model
{
    public class MoveRequest
    {
        public long id { set; get; }

        public int xposition { set; get; }

        public int yposition { set; get; }

        public bool flag { set; get; }
    }
}
