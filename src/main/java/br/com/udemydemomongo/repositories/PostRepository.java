package br.com.udemydemomongo.repositories;

import br.com.udemydemomongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    // Usando o recurso Query Methods
    List<Post> findByTitleContainingIgnoreCase(String text);
}