using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using MinesWeeper.APIClientLibrary.Model;

namespace MinesWeeper.APIClientLibrary.Repository.Interfaces
{
    public interface IGameRepository
    {
        Task<GameResponse> NewGame(GameRequest game);

        Task<GameResponse> PauseOrResumeGame(long gameId);

        Task<List<GameResponse>> GetAllGames();

        Task<GameResponse> GetGameById(long gameId);

        Task<List<GameResponse>> GetAllGamesByUser(long userId);

        Task<string> DeleteGame(long gameId);

        Task<MoveResponse> Move(long gameId, MoveRequest move);

        Task<MoveResponse> GetGameField(long gameId);
    }
}
