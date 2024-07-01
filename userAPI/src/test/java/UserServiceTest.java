
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.DEVLooping.userAPI.dao.UserDAO;
import com.DEVLooping.userAPI.entity.User;
import com.DEVLooping.userAPI.entity.UserType;
import com.DEVLooping.userAPI.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserServiceImpl userService; // Usar la implementación concreta

    @DisplayName("Prueba de creacion correcta del usuario")
    @Test
    void verifyUserCreatedSuccessfully() {
        // Crear un objeto UserType simulado para usar en la prueba
        UserType userType = new UserType();
        userType.setId(3); // Establecer el ID del tipo de usuario

        // Crear un usuario simulado
        User user = new User();
        user.setId(0);
        user.setUsername("test");
        user.setEmail("test@email.com");
        user.setPassword("password");
        user.setUserType(userType); // Establecer el tipo de usuario

        // Simular el comportamiento de userDAO.save(user)
        Mockito.when(userDAO.save(Mockito.any(User.class))).thenReturn(user);

        // Llamar al método que estamos probando
        User resultado = userService.save(user);

        // Verificar las afirmaciones
        Assertions.assertThat(resultado).isNotNull();
        Assertions.assertThat(resultado.getUsername()).isEqualTo("test");
    }

    @DisplayName("Prueba de eliminacion correcta del usuario")
    @Test
    void verifyUserDeletedSuccessfully() {
        // Crear un objeto UserType simulado para usar en la prueba
        UserType userType = new UserType();
        userType.setId(3); // Establecer el ID del tipo de usuario

        // Crear un usuario simulado
        User user = new User();
        user.setId(0);
        user.setUsername("test");
        user.setEmail("test@email.com");
        user.setPassword("password");
        user.setUserType(userType); // Establecer el tipo de usuario

        // Simular el comportamiento de userDAO.save(user)
        Mockito.when(userDAO.save(Mockito.any(User.class))).thenReturn(user);

        // Llamar al método que estamos probando
        User resultado = userService.save(user);

        // Verificar las afirmaciones
        Assertions.assertThat(resultado).isNotNull();
        Assertions.assertThat(resultado.getUsername()).isEqualTo("test");

        // Simular el comportamiento de userDAO.deleteById(user.getId())
        Mockito.doNothing().when(userDAO).deleteById(user.getId());

        // Llamar al método que estamos probando
        userService.deleteById(user.getId());

        // Verificar las afirmaciones
        Mockito.verify(userDAO, Mockito.times(1)).deleteById(user.getId());

    }

    @DisplayName("Prueba de actualizacion correcta del usuario")
    @Test
    void verifyUserFoundById() {
        // Crear un objeto UserType simulado para usar en la prueba
        UserType userType = new UserType();
        userType.setId(3); // Establecer el ID del tipo de usuario

        // Crear un usuario simulado
        User user = new User();
        user.setId(0);
        user.setUsername("test");
        user.setEmail("test@email.com");
        user.setPassword("password");
        user.setUserType(userType); // Establecer el tipo de usuario

        // Simular el comportamiento de userDAO.save(user)
        Mockito.when(userDAO.save(Mockito.any(User.class))).thenReturn(user);

        // Llamar al método que estamos probando
        User resultado = userService.save(user);

        // Verificar las afirmaciones
        Assertions.assertThat(resultado).isNotNull();
        Assertions.assertThat(resultado.getUsername()).isEqualTo("test");

        // Simular el comportamiento de userDAO.findById(user.getId())
        Mockito.when(userDAO.findById(user.getId())).thenReturn(user);

        // Llamar al método que estamos probando
        User resultado2 = userService.findById(user.getId());

        // Verificar las afirmaciones
        Assertions.assertThat(resultado2).isNotNull();
        Assertions.assertThat(resultado2.getUsername()).isEqualTo("test");

    }

    @DisplayName("Prueba de busqueda correcta del usuario por nombre de usuario")  
    @Test
    void verifyUserFoundByUsername() {
        // Crear un objeto UserType simulado para usar en la prueba
        UserType userType = new UserType();
        userType.setId(3); // Establecer el ID del tipo de usuario

        // Crear un usuario simulado
        User user = new User();
        user.setId(0);
        user.setUsername("test");
        user.setEmail("test@email.com");
        user.setPassword("password");
        user.setUserType(userType); // Establecer el tipo de usuario

        // Simular el comportamiento de userDAO.save(user)
        Mockito.when(userDAO.save(Mockito.any(User.class))).thenReturn(user);

        // Llamar al método que estamos probando
        User resultado = userService.save(user);

        // Verificar las afirmaciones
        Assertions.assertThat(resultado).isNotNull();
        Assertions.assertThat(resultado.getUsername()).isEqualTo("test");

        // Simular el comportamiento de userDAO.findByUsername(user.getUsername())
        Mockito.when(userDAO.findByUsername(user.getUsername())).thenReturn(user);
         
        // Llamar al método que estamos probando 
        User resultado2 = userService.findByUsername(user.getUsername());

        // Verificar las afirmaciones
        Assertions.assertThat(resultado2).isNotNull();
        Assertions.assertThat(resultado2.getUsername()).isEqualTo("test");

    }

}
