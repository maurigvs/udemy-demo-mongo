package br.com.udemydemomongo.resources;

import br.com.udemydemomongo.domain.User;
import br.com.udemydemomongo.dto.UserDTO;
import br.com.udemydemomongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
