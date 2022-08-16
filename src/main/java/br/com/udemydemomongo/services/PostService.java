package br.com.udemydemomongo.services;

import br.com.udemydemomongo.domain.Post;
import br.com.udemydemomongo.repositories.PostRepository;
import br.com.udemydemomongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post findById(String id){

        Optional<Post> post = repository.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Post not found by Id: " + id));
    }
}