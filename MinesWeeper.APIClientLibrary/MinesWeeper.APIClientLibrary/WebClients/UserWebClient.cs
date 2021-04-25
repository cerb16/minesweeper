using MinesWeeper.APIClientLibrary.Repository;
using MinesWeeper.APIClientLibrary.Repository.Interfaces;
using MinesWeeper.APIClientLibrary.WebClients.Interfaces;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace MinesWeeper.APIClientLibrary.WebClients
{
    public class UserWebClient : IUserWebClient
    {
        private readonly IUserRepository userRepository;
        public UserWebClient()
        {
            userRepository = new UserRepository();
        }
        public long CreateUser(User user)
        {
            return userRepository.CreateUser(user).Result;
        }

        public string DeleteUser(long userId)
        {
            return userRepository.DeleteUser(userId).Result;
        }

        public List<User> GetAllUsers()
        {
            return userRepository.GetAllUsers().Result;
        }

        public User GetByUserName(string userName)
        {
            return userRepository.GetByUserName(userName).Result;
        }

        public User GetUserById(long userId)
        {
            return userRepository.GetUserById(userId).Result;
        }

        public long UpdateUser(User user)
        {
            return userRepository.UpdateUser(user).Result;
        }
    }
}
