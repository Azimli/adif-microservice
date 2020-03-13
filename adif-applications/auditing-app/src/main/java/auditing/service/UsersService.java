package auditing.service;

import auditing.model.Users;

import java.util.List;

public interface UsersService {

    List listUsers();

    Users getUsers(Long id);

    Users createUsers(Users users);

    Users updateUsers(Users users,Long id);

    void deleteUsers(Long id);


}
