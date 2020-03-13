package auditing.controller;


import auditing.model.Users;
import auditing.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("all")
    public ResponseEntity allUsers(){
        return ResponseEntity.ok (usersService.listUsers ());
    }

    @GetMapping("{id}")
    public ResponseEntity<Users> getUsers(@PathVariable("id") Long id){
        return ResponseEntity.ok (usersService.getUsers (id));
    }

    @PostMapping
    public ResponseEntity<Users> createUsers(@RequestBody Users users){
        System.out.println ("Users elave edildi " + users);
        return ResponseEntity.ok (usersService.createUsers(users));
    }

    @PutMapping("{id}")
    public ResponseEntity<Users> updateUsers(@RequestBody Users users, @PathVariable("id") Long id){
        return ResponseEntity.ok (usersService.updateUsers (users,id));
    }

    @DeleteMapping("{id}")
    void deleteUsers(@PathVariable("id") Long id){
        usersService.deleteUsers (id);
    }

}
