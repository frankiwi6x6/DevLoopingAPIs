package com.DEVLooping.userAPI.rest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.DEVLooping.userAPI.entity.ErrorResponse;
import com.DEVLooping.userAPI.entity.User;
import com.DEVLooping.userAPI.entity.UserType;
import com.DEVLooping.userAPI.service.EmailService;
import com.DEVLooping.userAPI.service.EncryptService;
import com.DEVLooping.userAPI.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserService userService;
    private EncryptService encryptService = new EncryptService();
    @Autowired
    private EmailService emailService;

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
                            "Correo electrónico o contraseña inválidos. Por favor, inténtalo de nuevo.",
                            1));
        } else if (theUser.getStatus().equals("inactive")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                            HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                            "El usuario está desactivado. Por favor, contacta al administrador.",
                            2));
        } else if (theUser.getStatus().equals("banned")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                            HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                            "El usuario está baneado. Por favor, contacta al administrador.",
                            3));
        } else if (theUser.getStatus().equals("pending")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                            HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                            "El usuario aún no ha sido confirmado. Por favor, revisa tu correo electrónico para confirmar tu cuenta.",
                            4));
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

    String DOMINIO = "http://localhost:8080";
    String URL = DOMINIO + "/api/users/confirm/";

    String FRONTEND_URL = "http://localhost:5173";
    String URL_FRONTEND = FRONTEND_URL + "/password-recovery/";

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
        theUser.setStatus("pending");
        theUser.setBio(null);

        theUser.getUserType().setId(3);
        String encryptedPassword = encryptService.encrypt(theUser.getPassword());
        theUser.setPassword(encryptedPassword);

        //Enviar correo de confirmación
        try {

            User dbUser = userService.save(theUser);
            emailService.sendEmail(theUser.getEmail(), "Bienvenido a DEVLooping", "Hello " + dbUser.getUsername()
                    + ",\n\nBienvenido a DEVLooping! Estamos muy felices de que te hayas unido a nuestra plataforma. Comienza a compartir tu conocimiento con el mundo!.\n\nPara confirmar tu cuenta y comenzar a utilizar nuestra plataforma, por favor haz click en el siguiente enlace:" + URL + dbUser.getId() + "\n\nSi no has creado una cuenta en DEVLooping, por favor ignora este mensaje.\n\nGracias por unirte a DEVLooping!\n\nA desarrollar,\nDEVLooping Team");

            return ResponseEntity.ok(dbUser);
        } catch (Exception e) {
            System.out.println("Error sending email: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                        "Error sending email. Please try again later.",
                        5));
    }

    // Endpoint para confirmar usuario
    @GetMapping("/users/confirm/{userId}")
    public ResponseEntity<?> confirmUser(@PathVariable int userId) {
        User existingUser = userService.findById(userId);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                            HttpStatus.NOT_FOUND.getReasonPhrase(),
                            "User not found with id: " + userId,
                            1));
        }

        existingUser.setStatus("active");
        existingUser.setVerified_at(new Date());
        User savedUser = userService.save(existingUser);
        return ResponseEntity.ok("Usuario confirmado correctamente. Puedes cerrar esta ventana.");
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
        existingUser.setBio(updatedUser.getBio());

        // Guardar el usuario actualizado en la base de datos
        User savedUser = userService.save(existingUser);
        return savedUser;
    }

    @PutMapping("/users/{userId}/profile-pic")
    public User updateProfilePic(@PathVariable int userId, @RequestBody User updatedUser) {
        User existingUser = userService.findById(userId);
        if (existingUser == null) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }

        existingUser.setProfile_pic_url(updatedUser.getProfile_pic_url());

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

    @PostMapping("/users/request-email")
    public ResponseEntity<?> recoveryPasswordMail(@RequestBody Map<String, String> request) {
        // Rescatamos el correo electrónico del usuario
        String email = request.get("email");
        // Buscamos el usuario por correo electrónico
        User user = userService.findByEmail(email);
        // Si el usuario no existe, retornamos un error
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                            HttpStatus.NOT_FOUND.getReasonPhrase(),
                            "User not found with email: " + email,
                            1));

        }
        // Si el usuario existe, enviamos un correo con un enlace para recuperar la contraseña
        try {
            emailService.sendEmail(user.getEmail(), "Recuperación de contraseña", "Hola " + user.getUsername() + ",\n\nHemos recibido una solicitud para recuperar tu contraseña. Si no has solicitado este cambio, por favor ignora este mensaje.\n\nPara recuperar tu contraseña, haz click en el siguiente enlace: " + URL_FRONTEND + user.getId() + "\n\nGracias por utilizar DEVLooping!\n\nA desarrollar,\nDEVLooping Team");

        } catch (Exception e) {
            System.out.println("Error sending email: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                            "Error sending email. Please try again later.",
                            2));
        }
        return ResponseEntity.ok("Correo enviado correctamente. Por favor revisa tu bandeja de entrada.");
    }

    @PutMapping("/users/recovery-password/{userId}")
    public ResponseEntity<?> recoveryPassword(@PathVariable int userId, @RequestBody Map<String, String> request) {

        // Rescatamos la nueva contraseña del usuario
        String password = request.get("password");
        // Buscamos el usuario por id
        User user = userService.findById(userId);
        // Si el usuario no existe, retornamos un error
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                            HttpStatus.NOT_FOUND.getReasonPhrase(),
                            "User not found with id: " + userId,
                            1));
        }
        // Encriptamos la nueva contraseña
        String encryptedPassword = encryptService.encrypt(password);
        // Actualizamos la contraseña del usuario
        user.setPassword(encryptedPassword);
        // Guardamos el usuario con la nueva contraseña
        User updatedUser = userService.save(user);
        return ResponseEntity.ok(updatedUser);
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
