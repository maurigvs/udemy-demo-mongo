package br.com.udemydemomongo.resources;

import br.com.udemydemomongo.domain.Post;
import br.com.udemydemomongo.domain.User;
import br.com.udemydemomongo.dto.UserDTO;
import br.com.udemydemomongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){

        List<User> users = service.findAll();
        // Expressão lambda que converte um User para um UserDTO em uma coleção
        List<UserDTO> usersDto = users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){

        UserDTO user = new UserDTO(service.findById(id));
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id){

        List<Post> posts = service.findPosts(id);
        return ResponseEntity.ok().body(posts);
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody UserDTO userDto){

        User user = service.fromDTO(userDto);
        user = service.insert(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(user.getId()).toUri();
        // .created retorna o código de recurso criado
        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody UserDTO userDto){

        User user = service.fromDTO(userDto);
        user.setId(id);
        user = service.update(user);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        // Retorno Void caracteriza resposta vazia
        return ResponseEntity.noContent().build();
    }
}
