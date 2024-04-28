package com.DEVLooping.userAPI.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.DEVLooping.userAPI.entity.ErrorResponse;
import com.DEVLooping.userAPI.entity.User;
import com.DEVLooping.userAPI.entity.UserType;
import com.DEVLooping.userAPI.service.EncryptService;
import com.DEVLooping.userAPI.service.UserService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserService userService;
    private EncryptService encryptService = new EncryptService();

    public UserRestController(UserService theUserService) {
        userService = theUserService;
    }
    // exponer "/users" y retornar todos los usuarios

    @GetMapping("/users")
    public List<User> findAll() {
        List<User> theUsers = userService.findAll();

        return theUsers;
    }

    @PostMapping("/users/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");
        String hashedPassword = encryptService.encrypt(password);

        User theUser = userService.loginUser(email, hashedPassword);
        if (theUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                            HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                            "Invalid email or password. Please try again.",
                            1));
        } else if (theUser.getStatus().equals("inactive")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                            HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                            "User is inactive. Please contact the administrator.",
                            2));
        } else if (theUser.getStatus().equals("banned")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                            HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                            "User is banned. Please contact the administrator.",
                            3));

        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        theUser
                
                );
    }

    @GetMapping("/users/{userId}")
    public User findById(@PathVariable int userId) {
        User theUser = userService.findById(userId);
        if (theUser == null) {
            throw new UserNotFoundException("User id not found - " + userId);
        }
        return theUser;
    }

    @GetMapping("/users/username/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable String username) {
        User theUser = userService.findByUsername(username);
        if (theUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                            HttpStatus.NOT_FOUND.getReasonPhrase(),
                            "User not found with username: " + username,
                            1));
        }
        return ResponseEntity.ok(theUser);

    }

    @PostMapping("/users/register")
    public ResponseEntity<?> addUser(@RequestBody User theUser) {
        if (theUser.getEmail() == null || theUser.getUsername() == null || theUser.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Missing required fields. Please provide email, username, and password.",
                            1));
        }

        // Verificar si ya existe un usuario con el mismo nombre de usuario o correo
        // electrónico
        User existingUserByUsername = userService.findByUsername(theUser.getUsername());
        User existingUserByEmail = userService.findByEmail(theUser.getEmail());
        if (existingUserByUsername != null && existingUserByEmail != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Username and email already exist.\nPlease provide different username and email.",
                            2));
        } else if (existingUserByUsername != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Username already exists.\nPlease provide a different username.",
                            3));
        } else if (existingUserByEmail != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Email already exists.\nPlease provide a different email.",
                            4));
        }

        // Inicializar UserType si es null
        if (theUser.getUserType() == null) {
            theUser.setUserType(new UserType(3)); // Otra forma de inicializarlo podría ser: new UserType(id);
        }

        // Resto del código para guardar el usuario
        theUser.setId(0);
        // Inicializamos la hora de creación del usuario con el huso horario de Chile
        TimeZone tz = TimeZone.getTimeZone("America/Santiago");
        Calendar cal = Calendar.getInstance(tz);
        Date date = cal.getTime();
        theUser.setCreated_at(date);
        theUser.setStatus("active");

        theUser.getUserType().setId(3);
        String encryptedPassword = encryptService.encrypt(theUser.getPassword());
        theUser.setPassword(encryptedPassword);
        User dbUser = userService.save(theUser);

        return ResponseEntity.ok(dbUser);
    }

    @PutMapping("/users/{userId}")
    public User updateUser(@PathVariable int userId, @RequestBody User updatedUser) {
        User existingUser = userService.findById(userId);
        if (existingUser == null) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setUserType(updatedUser.getUserType());

        // Guardar el usuario actualizado en la base de datos
        User savedUser = userService.save(existingUser);
        return savedUser;
    }

    @DeleteMapping("/users/{userId}")
    public User softDeleteUser(@PathVariable int userId) {
        User existingUser = userService.findById(userId);
        if (existingUser == null) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }

        // Actualizar los atributos del usuario para "eliminarlo" de forma lógica
        existingUser.setDeactivated_at(new Date());
        existingUser.setStatus("inactive");

        // Guardar el usuario desactivado en la base de datos
        User updatedUser = userService.save(existingUser);
        return updatedUser;
    }

    @RestControllerAdvice
    class UserRestControllerAdvice {
        @ExceptionHandler
        public ResponseEntity<String> handleNotFoundException(UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{ status : " + HttpStatus.NOT_FOUND.value() + ", message:" + ex.getMessage() + "}");
        }

        @ExceptionHandler
        public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    "{ status : " + HttpStatus.INTERNAL_SERVER_ERROR.value() + ", message:" + ex.getMessage() + "}");
        }

    }

    class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}
