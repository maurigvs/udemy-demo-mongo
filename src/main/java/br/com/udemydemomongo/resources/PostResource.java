package br.com.udemydemomongo.resources;

import br.com.udemydemomongo.domain.Post;
import br.com.udemydemomongo.resources.util.URL;
import br.com.udemydemomongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){

        Post post = service.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(
            @RequestParam(value = "text", defaultValue = "") String text) {

        text = URL.decodeParam(text);
        List<Post> posts = service.findByTitle(text);
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<Post>> findByTitleAndPeriod(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "startDate", defaultValue = "") String startDate,
            @RequestParam(value = "endDate", defaultValue = "") String endDate){

        text = URL.decodeParam(text);
        Date start = URL.convertDate(startDate, new Date(0L));
        Date end = URL.convertDate(endDate, new Date());

        List<Post> posts = service.findByTitleAndPeriod(text, start, end);
        return ResponseEntity.ok().body(posts);
    }


}
