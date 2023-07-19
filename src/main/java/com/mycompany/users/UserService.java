package com.mycompany.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<User> ListAll(){
        return  (List<User>) repo.findAll();
    }
    public void save(User user){
        repo.save(user);
    }
    public User get(int id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("could not find any users with ID"+id);
    }

}
