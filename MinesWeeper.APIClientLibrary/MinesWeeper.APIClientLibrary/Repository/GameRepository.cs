using AKSoftware.WebApi.Client;
using MinesWeeper.APIClientLibrary.Model;
using MinesWeeper.APIClientLibrary.Repository.Interfaces;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace MinesWeeper.APIClientLibrary.Repository
{
    public class GameRepository : IGameRepository
    {

        private const string BASE_URL = "http://localhost:8002/minesweeper/api/game/";
        private readonly ServiceClient client;

        public GameRepository()
        {
            client = new ServiceClient();
        }

        public async Task<string> DeleteGame(long gameId)
        {
            string URL = BASE_URL + gameId;
            var response= await client.DeleteAsync<string>(URL);
            return response.Result; 
          
        }

        public async Task<List<GameResponse>> GetAllGames()
        {
            string URL = BASE_URL;
            var response = await client.GetAsync<List<GameResponse>>(URL);
            return response.Result;
        }

        public async Task<List<GameResponse>> GetAllGamesByUser(long userId)
        {
            string URL = BASE_URL + "/user/" + userId;
            var response = await client.GetAsync<List<GameResponse>>(URL);
            return response.Result;
        }

        public async Task<GameResponse> GetGameById(long gameId)
        {
            string URL = BASE_URL + gameId;
            var response = await client.GetAsync<GameResponse>(URL);
            return response.Result;
        }

        public async Task<MoveResponse> GetGameField(long gameId)
        {
            string URL = BASE_URL + gameId + "/gamefield";
            var response = await client.GetAsync<MoveResponse>(URL);
            return response.Result;
        }

        public async Task<MoveResponse> Move(long gameId, MoveRequest move)
        {
            string URL = BASE_URL + gameId + "/move";
            var response = await client.PutAsync<MoveResponse>(URL,move);
            return response.Result;
        }

        public async Task<GameResponse> NewGame(GameRequest game)
        {
            string URL = BASE_URL;
            var response = await client.PostAsync<GameResponse>(URL,game);
            return response.Result;
        }

        public async Task<GameResponse> PauseOrResumeGame(long gameId)
        {
            string URL = BASE_URL + gameId + "/pause-resume";
            var response = await client.PutAsync<GameResponse>(URL, null);
            return response.Result;
        }
    }
}
