package br.com.udemydemomongo.config;

import br.com.udemydemomongo.domain.Post;
import br.com.udemydemomongo.domain.User;
import br.com.udemydemomongo.dto.AuthorDTO;
import br.com.udemydemomongo.dto.CommentDTO;
import br.com.udemydemomongo.repositories.PostRepository;
import br.com.udemydemomongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        postRepository.deleteAll();
        userRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post p1 = new Post(null, sdf.parse("16/08/2022"), "Partiu viagem!", "Vou viajar para São Paulo, abraços!", new AuthorDTO(maria));
        Post p2 = new Post(null, sdf.parse("15/08/2022"), "Bom dia!", "Acordei feliz hoje!", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("22/03/2018"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));

        p1.getComments().addAll(Arrays.asList(c1, c2));
        p2.addComment(c3);

        postRepository.saveAll(Arrays.asList(p1, p2));

        maria.getPosts().addAll(Arrays.asList(p1, p2));

        userRepository.save(maria);

    }
}