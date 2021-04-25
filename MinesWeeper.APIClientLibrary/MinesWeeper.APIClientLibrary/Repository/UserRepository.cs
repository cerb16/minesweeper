using AKSoftware.WebApi.Client;
using MinesWeeper.APIClientLibrary.Repository.Interfaces;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace MinesWeeper.APIClientLibrary.Repository
{
    public class UserRepository : IUserRepository
    {

        private const string BASE_URL = "http://localhost:8002/minesweeper/api/user/";

        private readonly ServiceClient client;

        public UserRepository()
        {
            client = new ServiceClient();
        }
        public async Task<long> CreateUser(User user)
        {
            string URL = BASE_URL;
            var response = await client.PostAsync<long>(URL, user);
            return response.Result;
        }

        public async Task<string> DeleteUser(long userId)
        {
            string URL = BASE_URL + userId;
            var response = await client.DeleteAsync<string>(URL);
            return response.Result;
        }

        public async Task<List<User>> GetAllUsers()
        {
            string URL = BASE_URL;
            var response = await client.GetAsync<List<User>>(URL);
            return response.Result;
        }

        public async Task<User> GetByUserName(string userName)
        {
            string URL = BASE_URL + "username?username=" + userName;
            var response = await client.GetAsync<User>(URL);
            return response.Result;
        }

        public async Task<User> GetUserById(long userId)
        {
            string URL = BASE_URL + userId;
            var response = await client.GetAsync<User>(URL);
            return response.Result;
        }

        public async Task<long> UpdateUser(User user)
        {
            string URL = BASE_URL;
            var response = await client.PutAsync<long>(URL, user);
            return response.Result;
        }
    }
}
