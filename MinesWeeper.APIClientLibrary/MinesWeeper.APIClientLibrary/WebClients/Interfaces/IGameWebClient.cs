using MinesWeeper.APIClientLibrary.Model;
using System;
using System.Collections.Generic;
using System.Text;

namespace MinesWeeper.APIClientLibrary.WebClients.Interfaces
{
    public interface IGameWebClient
    {
        GameResponse NewGame(GameRequest game);

        GameResponse PauseOrResumeGame(long gameId);

        List<GameResponse> GetAllGames();

        GameResponse GetGameById(long gameId);

        List<GameResponse> GetAllGamesByUser(long userId);

        string DeleteGame(long gameId);

        MoveResponse Move(long gameId, MoveRequest move);

        MoveResponse GetGameField(long gameId);
    }
}
