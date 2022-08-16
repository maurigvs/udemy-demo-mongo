package br.com.udemydemomongo.services;

import br.com.udemydemomongo.domain.Post;
import br.com.udemydemomongo.domain.User;
import br.com.udemydemomongo.dto.UserDTO;
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

    public List<Post> findPosts(String id){
        User user = findById(id);
        if(user.getPosts().isEmpty()){
            throw new ObjectNotFoundException("No posts found by User Id: " + id);
        }
        return user.getPosts();
    }

    public User insert(User user){
        return repository.save(user);
    }

    public User update(User user){

        User userToUpdate = findById(user.getId());
        updateData(userToUpdate, user);
        return repository.save(userToUpdate);
    }

    public void delete(String id){

        User user = findById(id);
        repository.deleteById(user.getId());
    }

    public User fromDTO(UserDTO userDto){
        return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
    }

    private void updateData(User userToUpdate, User user) {
        // Previne que o Id e a Senha sejam atualizados
        userToUpdate.setName(user.getName());
        userToUpdate.setEmail(user.getEmail());
    }
}