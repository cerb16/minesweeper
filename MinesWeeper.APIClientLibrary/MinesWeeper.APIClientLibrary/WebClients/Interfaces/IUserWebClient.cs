using System;
using System.Collections.Generic;
using System.Text;

namespace MinesWeeper.APIClientLibrary.WebClients.Interfaces
{
    public interface IUserWebClient
    {
        long CreateUser(User user);

        long UpdateUser(User user);

        List<User> GetAllUsers();

        User GetUserById(long userId);

        User GetByUserName(string userName);

        string DeleteUser(long userId);
    }
}
