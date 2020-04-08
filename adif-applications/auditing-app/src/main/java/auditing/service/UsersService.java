package auditing.service;

import auditing.model.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsersService extends UserDetailsService {

    List listUsers();

    Users getUsers(Long id);

    Users createUsers(Users users);

    Users updateUsers(Users users,Long id);

    void deleteUsers(Long id);


}
