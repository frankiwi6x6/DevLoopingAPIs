-- Insertar datos en la tabla USER_TYPE
INSERT INTO USER_TYPE (name_type, desc_type) VALUES
    ('Administrador', 'Acceso total al sistema'),
    ('Moderador', 'Acceso limitado a ciertas funciones'),
    ('Usuario', 'Acceso básico al sistema');

-- Insertar datos en la tabla PERMISSION
INSERT INTO PERMISSION (name_permission, desc_permission) VALUES
    ('Gestionar usuarios', 'Permiso para crear, editar y eliminar usuarios'),
    ('Moderar contenido', 'Permiso para moderar publicaciones y comentarios'),
    ('Publicar contenido', 'Permiso para crear publicaciones'),
    ('Comentar', 'Permiso para comentar en publicaciones');

-- Insertar datos en la tabla ACHIEVEMENT
INSERT INTO ACHIEVEMENT (name_achievement, desc_achievement) VALUES
    ('Recién llegado', 'Logro obtenido al registrarse'),
    ('Participante activo', 'Logro obtenido por participar activamente'),
    ('Resolvedor de desafíos', 'Logro obtenido por resolver desafíos'),
    ('Maestro de publicaciones', 'Logro obtenido por hacer muchas publicaciones'),
    ('Rey de los comentarios', 'Logro obtenido por hacer muchos comentarios');

-- Insertar datos en la tabla DIFFICULTY
INSERT INTO DIFFICULTY (id_difficulty, desc_difficulty) VALUES 
	(1, 'Principiante'),
    (2, 'Fácil'),
    (3, 'Intermedio'),
    (4, 'Difícil'),
    (5, 'Experto'),
    (6, 'Prueba técnica');
-- Insertar datos en la tabla CHALLENGE
INSERT INTO CHALLENGE (title, desc_challenge, DIFFICULTY_id_difficulty, start_at, end_at) VALUES
    ('¡Tu primer "Hola Mundo" en JS!', 'Debes lograr que la consola imprima "Hola Mundo"', 1, '2023-05-01', NULL),
    ('¡Sumar dos números!', 'Tu tarea es crear una función que ingrese dos parámetros y los sume', 2, '2023-06-01', NULL),
    ('Convertir grados Celsius a Fahrenheit', 'Crear una función que convierta grados Celsius a Fahrenheit', 2, '2023-06-15', NULL),
    ('Calcular el área de un triángulo', 'Crear una función que calcule el área de un triángulo dado su base y altura', 3, '2023-07-01', NULL),
    ('Encontrar el número mayor', 'Crear una función que encuentre el número mayor en un array dado', 3, '2023-07-15', NULL),
    ('Ordenar un array', 'Crear una función que ordene un array de números de menor a mayor', 3, '2023-08-01', NULL);


-- Insertar datos en la tabla USER
INSERT INTO `USER` (username, email, password_hashed, created_at, user_type_id, status) VALUES
    ('admin', 'admin@example.com', 'admin123', '2022-01-01', 1, 'active'),
    ('moderador1', 'moderador1@example.com', 'mod123', '2022-02-15', 2, 'active'),
    ('moderador2', 'moderador2@example.com', 'mod456', '2022-03-20', 2, 'active'),
    ('usuario1', 'usuario1@example.com', 'user123', '2022-04-10', 3, 'active'),
    ('usuario2', 'usuario2@example.com', 'user456', '2022-05-05', 3, 'active'),
    ('usuario3', 'usuario3@example.com', 'user789', '2022-06-15', 3, 'active'),
    ('usuario4', 'usuario4@example.com', 'userABC', '2022-07-20', 3, 'active'),
    ('usuario5', 'usuario5@example.com', 'userDEF', '2022-08-25', 3, 'active');

-- Insertar datos en la tabla PERMISSION_USER
INSERT INTO PERMISSION_USER (PERMISSION_id_permission, USER_id_user) VALUES
    (1, 1), -- El admin tiene permiso de gestionar usuarios
    (2, 2), (2, 3), -- Los moderadores tienen permiso de moderar contenido
    (3, 4), (3, 5), (3, 6), (3, 7), (3, 8), -- Los usuarios tienen permiso de publicar contenido
    (4, 4), (4, 5), (4, 6), (4, 7), (4, 8); -- Los usuarios tienen permiso de comentar

-- Insertar datos en la tabla ARCHIEVEMENT_USER
INSERT INTO ARCHIEVEMENT_USER (USER_id_user, ACHIEVEMENT_id_achievement, created_at) VALUES
    (1, 1, '2022-01-01'), (2, 1, '2022-02-15'), (3, 1, '2022-03-20'), (4, 1, '2022-04-10'),
    (5, 1, '2022-05-05'), (6, 1, '2022-06-15'), (7, 1, '2022-07-20'), (8, 1, '2022-08-25'),
    (4, 2, '2022-05-01'), (5, 2, '2022-06-01'), (6, 2, '2022-07-01'), (7, 2, '2022-08-01'),
    (1, 3, '2023-01-01'), (2, 3, '2023-02-01'), (3, 3, '2023-03-01');

