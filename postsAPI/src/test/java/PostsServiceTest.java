
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.devlooping.api.dao.PostDAO;
import com.devlooping.api.entity.Comment;
import com.devlooping.api.entity.Post;
import com.devlooping.api.services.PostService;

@ExtendWith(MockitoExtension.class)
public class PostsServiceTest {

    @Mock
    private PostDAO postDAO;

    @InjectMocks
    private PostService postService;

    LocalDateTime date = LocalDateTime.now();

    @DisplayName("Prueba de creacion correcta del post")
    @Test
    void verifyPostCreatedSuccessfully() {
        // Crear un post simulado
        Post post = new Post();
        post.setId(0L);
        post.setPostContent("Test content");
        post.setCreatedAt(date);

        // Simular el comportamiento de postDAO.savePost(post)
        Mockito.when(postDAO.savePost(Mockito.any(Post.class))).thenReturn(post);

        // Llamar al método que estamos probando
        postService.savePost(post);
    }

    @DisplayName("Prueba de eliminacion correcta del post")
    @Test
    void verifyPostDeletedSuccessfully() {
        // Crear un post simulado
        Post post = new Post();
        post.setId(0L);
        post.setPostContent("Test content");
        post.setCreatedAt(date);

        // Simular el comportamiento de postDAO.savePost(post)
        postDAO.savePost(post);

        // Llamar al método que estamos probando
        postService.deletePost(post.getId());

        // Verificar las afirmaciones
        Mockito.verify(postDAO, Mockito.times(1)).deletePost(post.getId());

    }

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
    }


    @DisplayName("Prueba de actualizacion correcta del post")
    @Test
    void verifyPostUpdatedSuccessfully() {
        // Crear un post simulado
        Post post = new Post();
        post.setId(0L);
        post.setPostContent("Test content");
        post.setCreatedAt(date);

        // Simular el comportamiento de postDAO.savePost(post)
        postDAO.savePost(post);

        // Llamar al método que estamos probando
        postService.updatePost(post.getId(), "Test post updated", post.getUserId());

        // Verificar las afirmaciones
        Mockito.verify(postDAO, Mockito.times(1)).updatePost(post.getId(), post.getUserId(), "Test post updated");

    }
    
}
