package auditing.service;

import auditing.model.Users;
import auditing.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List  listUsers() {
        Iterable<Users> allUsers = usersRepository.findAll ();
        return Arrays.asList (allUsers);
    }

    @Override
    public Users getUsers(Long id) {
        Optional<Users>  byId = usersRepository.findById (id);
        if (byId.isPresent ()){
            return byId.get ();
        }

        return null;
    }

    @Override
    public Users createUsers(Users users) {
        if (users.getId () != null){
            System.out.println ("Users id must be null");
            users.setId (null);
        }

       return usersRepository.save (users);
    }

    @Override
    public Users updateUsers(Users users, Long id) {
       users.setId (id);
       return usersRepository.save (users);
    }

    @Override
    public void deleteUsers(Long id) {
       Optional<Users> byId = usersRepository.findById (id);
       if (byId.isPresent ()){
           usersRepository.delete (byId.get ());
       }else {
           System.out.println (" No users with id: " + id);
       }
    }
}
