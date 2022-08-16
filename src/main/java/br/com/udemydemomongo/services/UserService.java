package br.com.udemydemomongo.services;

import br.com.udemydemomongo.domain.User;
import br.com.udemydemomongo.repositories.UserRepository;
import br.com.udemydemomongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(String id){
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("User not found by Id: " + id));
    }
}
