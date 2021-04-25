using MinesWeeper.APIClientLibrary.Model;
using MinesWeeper.APIClientLibrary.Repository;
using MinesWeeper.APIClientLibrary.Repository.Interfaces;
using MinesWeeper.APIClientLibrary.WebClients.Interfaces;
using System.Collections.Generic;

namespace MinesWeeper.APIClientLibrary.WebClients
{
    public class GameWebClient : IGameWebClient
    {
        private readonly IGameRepository gameRepository;
        public GameWebClient()
        {
            gameRepository = new GameRepository();
        }
        public string DeleteGame(long gameId)
        {
            return gameRepository.DeleteGame(gameId).Result;
        }

        public List<GameResponse> GetAllGames()
        {
            return gameRepository.GetAllGames().Result;
        }

        public List<GameResponse> GetAllGamesByUser(long userId)
        {
            return gameRepository.GetAllGamesByUser(userId).Result;
        }

        public GameResponse GetGameById(long gameId)
        {
            return gameRepository.GetGameById(gameId).Result;
        }

        public MoveResponse GetGameField(long gameId)
        {
            return gameRepository.GetGameField(gameId).Result;
        }

        public MoveResponse Move(long gameId, MoveRequest move)
        {
            return gameRepository.Move(gameId,move).Result;
        }

        public GameResponse NewGame(GameRequest game)
        {
            return gameRepository.NewGame(game).Result;
        }

        public GameResponse PauseOrResumeGame(long gameId)
        {
            return gameRepository.PauseOrResumeGame(gameId).Result;
        }
    }
}
