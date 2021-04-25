using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace MinesWeeper.APIClientLibrary.Repository.Interfaces
{
    public interface IUserRepository
    {
        Task<long> CreateUser(User user);

        Task<long> UpdateUser(User user);

        Task<List<User>> GetAllUsers();

        Task<User> GetUserById(long userId);

        Task<User> GetByUserName(string userName);

        Task<string> DeleteUser(long userId);
    }
}
