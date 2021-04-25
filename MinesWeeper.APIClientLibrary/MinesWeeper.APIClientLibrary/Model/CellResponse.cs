using System;
using System.Collections.Generic;
using System.Text;

namespace MinesWeeper.APIClientLibrary.Model
{
    public class CellResponse
    {
        public long id { set; get; }
        public int xPosition { set; get; }
        public int yPosition { set; get; }
        public int value { set; get; }
        public bool mine { set; get; }
        public bool flag { set; get; }
        public bool revealed { set; get; }
    }
}
