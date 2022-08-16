package br.com.udemydemomongo.repositories;

import br.com.udemydemomongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    // Usando o recurso Query Methods
    List<Post> findByTitleContainingIgnoreCase(String text);

    @Query("{'title':{$regex: ?0, $options: 'i'} }")
    List<Post> findByTitle(String text);

    @Query("{ $and: [ { date: {$gte: ?1} }, { date: {$lte: ?2} }, { or: [ {'title':{$regex: ?0, $options: 'i'} }, {'body':{$regex: ?0, $options: 'i'} }, {'comments.text':{$regex: ?0, $options: 'i'} } ] } ] }")
    List<Post> findByTitleAndPeriod(String text, Date startDate, Date endDate);
}