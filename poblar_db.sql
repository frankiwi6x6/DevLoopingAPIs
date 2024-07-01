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
INSERT INTO DIFFICULTY (desc_difficulty) VALUES 
    ('Principiante'),
    ('Fácil'),
    ('Intermedio'),
    ('Difícil'),
    ('Experto'),
    ('Prueba técnica');

-- Insertar datos en la tabla USER
INSERT INTO `USER` (username, email, password_hashed, created_at, user_type_id, status, bio) VALUES
    ('admin', 'admin@example.com', 'YWRtaW4xMjM=', '2022-01-01', 1, 'active', null),
    ('pepe', 'pepe@example.com', 'YWRtaW4xMjM=', '2022-01-01', 3, 'active', null),
    ('juanito', 'juanito@example.com', 'YWRtaW4xMjM=', '2022-01-01', 3, 'active', null);

-- Insertar datos en la tabla CHALLENGE_TYPE
INSERT INTO CHALLENGE_TYPE (desc_type) VALUES
    ('Desafío'),
    ('Lección');

-- Insertar datos en la tabla CHALLENGE_CATEGORY
INSERT INTO CHALLENGE_CATEGORY (category_name, category_description) VALUES 
('Consola', 'Retos que involucran en uso de consola'),
('Funciones', 'Retos que involucran la creación de funciones');

-- Insertar datos en la tabla ANSWER_STATUS
INSERT INTO ANSWER_STATUS (status_name, status_desc) VALUES
    ('Borrador', 'La respuesta se ha guardado como borrador y aún no ha sido enviada.'),
    ('Enviado', 'La respuesta ha sido enviada.');

    

-- Insertar datos en la tabla CHALLENGE
INSERT INTO CHALLENGE (title, desc_challenge, content, DIFFICULTY_id_difficulty, CHALLENGE_TYPE_id_type, start_at, end_at, CATEGORY_ID) VALUES
    ('¡Tu primer "Hola Mundo" en JS!', 'Debes lograr que la consola imprima "Hola Mundo"', NULL, 1, 1, '2023-05-01', NULL, 1),
    ('¡Sumar dos números!', 'Tu tarea es crear una función que ingrese dos parámetros y los sume', NULL, 2, 1, '2023-06-01', NULL, 2),
    ('Convertir grados Celsius a Fahrenheit', 'Crear una función que convierta grados Celsius a Fahrenheit', NULL, 2, 1, '2023-06-15', NULL,2),
    ('Calcular el área de un triángulo', 'Crear una función que calcule el área de un triángulo dado su base y altura', NULL, 3, 1, '2023-07-01', NULL,2),
--    ('Encontrar el número mayor', 'Crear una función que encuentre el número mayor en un array dado', NULL,  3, 1, '2023-07-15', NULL,2),
--    ('Ordenar un array', 'Crear una función que ordene un array de números de menor a mayor', NULL, 3, 1, '2023-08-01', NULL, 2)
;


-- Insertar datos en la tabla TIP
INSERT INTO TIP (TIP_TITLE, TIP_DESC, CHALLENGE_id_challenge) VALUES
    ('Tip para el reto 1', 'Recuerda usar console.log() para imprimir en la consola', 1),
    ('Tip para el reto 2', 'Revisa la sintaxis de las funciones en JavaScript', 2),
    ('Tip para el reto 3', 'Utiliza la fórmula (C * 9/5) + 32 para convertir a Fahrenheit', 3),
    ('Tip para el reto 4', 'La fórmula del área de un triángulo es (base * altura) / 2', 4),
--    ('Tip para el reto 5', 'Puedes usar el método Math.max() en JavaScript', 5),
--    ('Tip para el reto 6', 'Investiga sobre los métodos de ordenación de arrays en JavaScript', 6),
--    ('Tip 2 para el reto 6', 'Investiga sobre los métodos de ordenación de arrays en JavaScript', 6)
;


-- Insertar datos en la tabla TEST
INSERT INTO TEST (test_description) VALUES
    ('Prueba para el reto 1: Hola Mundo'),
    ('Prueba para el reto 2: Sumar dos números'),
    ('Prueba para el reto 3: Convertir grados Celsius a Fahrenheit'),
    ('Prueba para el reto 4: Calcular el área de un triángulo'),
--    ('Prueba para el reto 5: Encontrar el número mayor en un array'),
--    ('Prueba para el reto 6: Ordenar un array')
;

-- Insertar datos en la tabla OUTPUT
INSERT INTO OUTPUT (TEST_ID, OUTPUT_VALUE) VALUES
    (1, 'Hola Mundo'), -- Reto 1

    -- Reto 2: Sumar dos números
    (2, '8'),
    (2, '11'),
    (2, '-3'),

    -- Reto 3: Convertir grados Celsius a Fahrenheit
    (3, '32'),
    (3, '212'),
    (3, '-40'),

    -- Reto 4: Calcular el área de un triángulo
    (4, '25'),
/*
    -- Reto 5: Encontrar el número mayor en un array
    (5, '5'),

    -- Reto 6: Ordenar un array
    (6, '[1, 2, 3, 5, 8]')
*/;
-- Insertar datos en la tabla INPUT
INSERT INTO INPUT (TEST_ID, INPUT_VALUE) VALUES
    (1, 'Hola Mundo'), -- Reto 1

    -- Reto 2: Sumar dos números
    (2, '[3, 5]'),
    (2, '[4, 7]'),
    (2, '[4, -7]'),

    -- Reto 3: Convertir grados Celsius a Fahrenheit
    (3, '[0]'),
    (3, '[100]'),
    (3, '[-40]'),

    -- Reto 4: Calcular el área de un triángulo
    (4, '[10, 5]'),
/*
    -- Reto 5: Encontrar el número mayor en un array
    (5, '[1, 2, 3, 4, 5]'),

    -- Reto 6: Ordenar un array
    (6, '[5, 3, 8, 1, 2]')*/
    ;

-- Insertar datos en la tabla CHALLENGE_TESTS
INSERT INTO CHALLENGE_TESTS (CHALLENGE_ID, TEST_ID) VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    /*
    (5, 5),
    (6, 6)*/;

INSERT INTO POST_STATE (state_name) VALUES ('Privado');
INSERT INTO POST_STATE (state_name) VALUES ('Publico');
INSERT INTO POST_STATE (state_name) VALUES ('Deshabilitado');