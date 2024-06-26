
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import com.devlooping.api.dao.PostDAO;
import com.devlooping.api.entity.Post;
import com.devlooping.api.entity.PostSummary;
import com.devlooping.api.services.PostService;
import com.devlooping.api.websocket.PostHandler;

@ExtendWith(MockitoExtension.class)
public class PostsServiceTest {

    @Mock
    private PostDAO postDAO;

    @InjectMocks
    private PostService postService;

    private PostHandler postHandler = new PostHandler();

    LocalDateTime date = LocalDateTime.now();


    @DisplayName("Prueba de busqueda correcta correcta del post")
    @Test
    void verifyPostFoundSuccessfully() {
        // Crear un post simulado
        Post post = new Post();
        post.setId(359L);
        post.setPostContent("Test content");
        post.setCreatedAt(date);

        // Simular el comportamiento de postDAO.savePost(post)
        postDAO.savePost(post);

        // Llamar al método que estamos probando
        postService.getPostById(post.getId());

        // Verificar las aserciones
        Assert.notNull(post, "El post no puede ser nulo");
        Assert.isTrue(post.getId() == 359L, "El id del post debe ser 359");
        Assert.isTrue(post.getPostContent().equals("Test content"), "El contenido del post debe ser 'Test content'");
    }

    @DisplayName("Prueba de busqueda incorrecta del post")
    @Test
    void verifyPostNotFound() {
        // Crear un post simulado
        Post post = new Post();
        post.setId(359L);
        post.setPostContent("Test content");
        post.setCreatedAt(date);

        // Simular el comportamiento de postDAO.savePost(post)
        postDAO.savePost(post);

        // Llamar al método que estamos probando

        PostSummary resultado = postService.getPostById(360L);

        // Verificar las aserciones
        Assert.isNull(resultado, "El post no debe existir");

    }

    @DisplayName("Prueba de getAllPosts")
    @Test
    void verifyGetAllPosts() {
        // Crear un post simulado
        Post post = new Post();
        post.setId(359L);
        post.setPostContent("Test content");
        post.setCreatedAt(date);

        Post post2 = new Post();
        post2.setId(360L);
        post2.setPostContent("Test content");
        post2.setCreatedAt(date);

        // Simular el comportamiento de postDAO.savePost(post)
        postDAO.savePost(post);
        postDAO.savePost(post2);

        // Llamar al método que estamos probando
        // Creamos una lista de postSummary para compararla con la lista que devuelve el método
               
        List<PostSummary> resultado = postService.getAllPosts();
        
        // Verificar las aserciones
        Assert.notNull(resultado, "La lista de posts no puede ser nula");
        
    }

    @DisplayName("Prueba de getPostsByUser")
    @Test
    void checkPostByUser() {
        Long USER_ID = 359L;
        // Crear un post simulado
        Post post = new Post();
        post.setId(359L);
        post.setPostContent("Test content");
        post.setCreatedAt(date);
        post.setUserId(USER_ID);

        Post post2 = new Post();
        post2.setId(360L);
        post2.setPostContent("Test content");
        post2.setCreatedAt(date);
        post2.setUserId(USER_ID);


        // Simular el comportamiento de postDAO.savePost(post)
        postDAO.savePost(post);
        postDAO.savePost(post2);

        // Llamar al método que estamos probando
        // Creamos una lista de postSummary para compararla con la lista que devuelve el método
               
        List<PostSummary> resultado = postService.getPostsByUser(359L);
        
        // Verificar las aserciones
        Assert.notNull(resultado, "La lista de posts no puede ser nula");
        
    }

}
