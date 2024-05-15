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

-- Insertar datos en la tabla CHALLENGE_TYPE
INSERT INTO CHALLENGE_TYPE (id_type, desc_type) VALUES
(1, 'Desafío'),
(2, 'Lección');

INSERT INTO ANSWER_STATUS (STATUS_ID, status_name, status_desc) VALUES
(1, 'Borrador', 'La respuesta se ha guardado como borrador y aún no ha sido enviada.'),
(2, 'Enviado', 'La respuesta ha sido enviada.');



    
-- Insertar datos en la tabla CHALLENGE
INSERT INTO CHALLENGE (title, desc_challenge, content, DIFFICULTY_id_difficulty, CHALLENGE_TYPE_id_type, start_at, end_at) VALUES
    ('¡Tu primer "Hola Mundo" en JS!', 'Debes lograr que la consola imprima "Hola Mundo"', NULL, 1, 1, '2023-05-01', NULL),
    ('¡Sumar dos números!', 'Tu tarea es crear una función que ingrese dos parámetros y los sume', NULL, 2, 1, '2023-06-01', NULL),
    ('Convertir grados Celsius a Fahrenheit', 'Crear una función que convierta grados Celsius a Fahrenheit', NULL, 2, 1, '2023-06-15', NULL),
    ('Calcular el área de un triángulo', 'Crear una función que calcule el área de un triángulo dado su base y altura', NULL, 3, 1, '2023-07-01', NULL),
    ('Encontrar el número mayor', 'Crear una función que encuentre el número mayor en un array dado', NULL,  3, 1, '2023-07-15', NULL),
    ('titulo', 'descripcion', '[
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Cómo crear tu primer código en Javascript. Hello world!"
  },
  {
    "attributes": {
      "header": 1
    },
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Última actualización: 5 mar 2022"
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Hello world usando editor online (codepen)"
  },
  {
    "attributes": {
      "header": 2
    },
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "La manera más rápida y sencilla de hacer un hola mundo es usar una web en la que puedes programar sin instalar nada."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Una de las que recomiendo es "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "var(--color-primary)",
      "link": "https://codepen.io/"
    },
    "insert": "Codepen"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": ", simplemente le das a "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff",
      "bold": true
    },
    "insert": "Start coding"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": " y ya te abre una pantalla tal que así:"
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "alt": "En la imagen se ve la página de codepen abierta y 3 secciones para escribir código",
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": {
      "image": "https://i.imgur.com/KmZvoE8.png"
    }
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Como ves se te abre un editor muy simple, con el que puedes escribir "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "var(--color-primary)",
      "link": "https://codingpotions.com/html"
    },
    "insert": "HTML"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": ", "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "var(--color-primary)",
      "link": "https://codingpotions.com/css"
    },
    "insert": "CSS"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": " Y "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "var(--color-primary)",
      "link": "https://codingpotions.com/javascript"
    },
    "insert": "Javascript"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": " (JS) que es lo que nos interesa para este artículo. Si no sabes lo que son estos lenguajes pásate por el post de "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "var(--color-primary)",
      "link": "https://codingpotions.com/frontend"
    },
    "insert": "Frontend"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Por cierto, en codepen puedes crearte una cuenta para ir guardando los proyectillos que vayas creando."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Vale, pues para escribir el hola mundo tan solo tienes que escribir lo siguiente en la caja de JS:"
  },
  {
    "insert": "\nconsole.log(\"Hello world!\");"
  },
  {
    "attributes": {
      "code-block": "js"
    },
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Si ahora le das al botón de abajo a la izquierda que pone \""
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff",
      "bold": true
    },
    "insert": "Console"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "\", podrás ver que en la pantalla pone "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff",
      "bold": true
    },
    "insert": "Hello world!"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "En eso consiste hacer un hello world, en crear un programita o web que simplemente pinte "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff",
      "bold": true
    },
    "insert": "hello world"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": " en la pantalla, así de fácil."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Hello world en tu ordenador usando ficheros"
  },
  {
    "attributes": {
      "header": 2
    },
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "¿Pero es así de fácil crear un hello world? Pues la realidad es que estás páginas precisamente lo que hacen es facilitarnos la vida para no tener que montar un entorno para desarrollar páginas web."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Ahora que has creado tu primer código te recomiendo que des el salto y empieces a desarrollar en ficheros de tu ordenador, así te vas familiarizando con las herramientas, ya que normalmente la gente programa y trabaja en ficheros locales y no en la nube."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Para programar en local solo necesitas un editor de textos. Hay gente que empieza con el notepad (o cualquier programa que permita escribir en el ordenador), pero yo te recomiendo que empieces directamente con el "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "var(--color-primary)",
      "link": "https://code.visualstudio.com/"
    },
    "insert": "Vscode"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": ", un editor de código totalmente gratuito para programar."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "En el artículo de "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "var(--color-primary)",
      "link": "https://codingpotions.com/entorno-desarrollo-frontend"
    },
    "insert": "Entorno de desarrollo para frontend"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": " explico con más detalle todas las opciones que tienes para programar."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Una vez descargado tienes que crear una carpeta en tu ordenador y abrirla con el editor de código. Dentro de esta carpeta crea un fichero llamado "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff",
      "bold": true
    },
    "insert": "index.html"
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "En realidad lo puedes llamar como quieras, pero al fichero principal de una web se le suele llamar "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff",
      "bold": true
    },
    "insert": "index"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": " por convención."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "El contenido del fichero "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff",
      "bold": true
    },
    "insert": "index.html"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": " es el siguiente:"
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n"
  },
  {
    "insert": ""
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n"
  },
  {
    "insert": "  "
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n"
  },
  {
    "insert": "    "
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n"
  },
  {
    "insert": "    "
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n"
  },
  {
    "insert": "    "
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n"
  },
  {
    "insert": "  "
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n\n"
  },
  {
    "insert": "  "
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n"
  },
  {
    "insert": "    "
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n"
  },
  {
    "insert": "  "
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n"
  },
  {
    "insert": ""
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "No me voy a parar mucho en explicar HTML, simplemente tienes que saber que con la etiqueta "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "var(--color-primary)",
      "link": "https://codingpotions.com/html"
    },
    "insert": "HTML"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": " de "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff",
      "code": true
    },
    "insert": "script"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": " lo que se puede hacer es ejecutar código Javascript, en este caso pues el "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff",
      "bold": true
    },
    "insert": "console.log"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": " que lo que hace es escribirlo en la consola del navegador."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Si ahora abres este fichero en el navegador (botón derecho y abrir con Firefox o Chrome o el que uses) verás que no aparece nada, es normal."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "El código que hemos escrito se ejecuta y se imprime en la consola del navegador, y no en la propia página web."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Para abrir la consola del navegador dale a botón derecho en la página y selecciona la opción de "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff",
      "bold": true
    },
    "insert": "inspeccionar elemento"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": " o "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff",
      "bold": true
    },
    "insert": "inspect"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": ", también puedes usar la combinación de teclas de "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff",
      "code": true
    },
    "insert": "Control + Shift + I"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": " (en MAC ni idea, supongo que Comando + Shift + I)."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Se te ha debido de abrir una ventana, bienvenido a las "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "var(--color-text-shade)"
    },
    "insert": "Developers tools del navegador 🚧"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": " (o herramientas para desarrolladores). Si quieres ser desarrollador web te recomiendo que te habitúes a usar esta herramienta porque la vas a usar mucho."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Con esta herramienta puedes ver el código fuente de una página web, puedes ver lo que se está ejecutando, hacer análisis de la ejecución, ver las llamadas que se hacen a servidor, etc."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Para nuestro caso nos interesa la pestaña de la "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff",
      "bold": true
    },
    "insert": "consola"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": " (console). En esta pestaña puedes ver algunos errores y también el código que se ejecuta en los "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff",
      "bold": true
    },
    "insert": "console.log"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Si has seguido bien los pasos deberías poder ver el "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff",
      "bold": true
    },
    "insert": "Hello world"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": {
      "image": "https://i.imgur.com/x1SXSQq.png"
    }
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Código Javascript en fichero independiente"
  },
  {
    "attributes": {
      "header": 2
    },
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Esto está muy bien, pero la realidad es que escribir código Javascript dentro de los ficheros "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff",
      "bold": true
    },
    "insert": ".html"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": " se considera mala práctica, lo mejor es no mezclar lenguajes y separar las cosas en distintos ficheros."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Para el Hello World lo que puedes hacer es crear en la carpeta un fichero llamado "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff",
      "bold": true
    },
    "insert": "index.js"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": " (o como quieras llamarlo pero que tenga extensión .js)."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Dentro del fichero "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff",
      "bold": true
    },
    "insert": "index.html"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": " tienes que modificar la etiqueta de script para que llame al fichero en lugar de ejecutar el código Javascript directamente."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n"
  },
  {
    "insert": ""
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n"
  },
  {
    "insert": "  "
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n"
  },
  {
    "insert": "    "
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n"
  },
  {
    "insert": "    "
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n"
  },
  {
    "insert": "    "
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n"
  },
  {
    "insert": "  "
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n\n"
  },
  {
    "insert": "  "
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n"
  },
  {
    "insert": "    "
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n"
  },
  {
    "insert": "  "
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n"
  },
  {
    "insert": ""
  },
  {
    "attributes": {
      "code-block": "html"
    },
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Y dentro del fichero "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff",
      "bold": true
    },
    "insert": ".js"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": " tan solo tienes que escribir:"
  },
  {
    "insert": "\nconsole.log(\"Hello world!\");"
  },
  {
    "attributes": {
      "code-block": "js"
    },
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Y listo, si ahora guardas ambos ficheros y vuelves a abrir las developers tools en el navegador podrás ver que el resultado es el mismo."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Prueba ahora a cambiar el texto entre las comillas dobles y a guardar el fichero. Si vuelves al navegador verás que no ha ocurrido nada, eso es porque tienes que recargar la página para que el navegador refresque los cambios, simplement4e pulsa F5 en la página o dale al botón de recargar web."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Y poco más la verdad. Si eres un programador o programadora que está empezando te recomiendo que tengas paciencia, es normal al principio sentirse abrumado por la cantidad de información que hay y que tienes que aprender, con práctica al final todo sale, buena suerte!"
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Este blog ha sido archivado y no se subirán artículos nuevos. Si te gustan mis artículos sígueme en mi "
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "var(--color-primary)",
      "link": "https://diegologs.com/"
    },
    "insert": "nuevo blog personal"
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Artículos que mencionan a este"
  },
  {
    "attributes": {
      "header": 2
    },
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "var(--color-primary)",
      "link": "https://codingpotions.com/javascript-variables/"
    },
    "insert": "Variables en Javascript"
  },
  {
    "attributes": {
      "header": 3
    },
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Descubre cómo funcionan las variables en JavaScript y cómo puedes utilizarlas en tus proyectos web. Aprende los diferentes tipos de variables, cómo declararlas, asignarles valores y manipularlas."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "var(--color-primary)",
      "link": "https://codingpotions.com/entorno-desarrollo-frontend/"
    },
    "insert": "Entorno de desarrollo para frontend"
  },
  {
    "attributes": {
      "header": 3
    },
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Todas las opciones que tienes a la hora de escribir código para frontend, tanto en local como en la nube"
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "var(--color-primary)",
      "link": "https://codingpotions.com/javascript/"
    },
    "insert": "Guía completa para aprender Javascript"
  },
  {
    "attributes": {
      "header": 3
    },
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "#fdfdff"
    },
    "insert": "Javascript es el lenguaje que usan las páginas web, y además es uno de los más usados en el mundo, aprende a usarlo con estos artículos."
  },
  {
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "var(--color-primary)",
      "link": "https://codingpotions.com/"
    },
    "insert": "Inicio"
  },
  {
    "attributes": {
      "list": "bullet"
    },
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "var(--color-primary)",
      "link": "https://codingpotions.com/archivo"
    },
    "insert": "Artículos"
  },
  {
    "attributes": {
      "list": "bullet"
    },
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "var(--color-primary)",
      "link": "https://codingpotions.com/notas"
    },
    "insert": "Notas"
  },
  {
    "attributes": {
      "list": "bullet"
    },
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "var(--color-primary)",
      "link": "https://codingpotions.com/redes"
    },
    "insert": "Redes sociales"
  },
  {
    "attributes": {
      "list": "bullet"
    },
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "var(--color-primary)",
      "link": "https://codingpotions.com/rss"
    },
    "insert": "RSS"
  },
  {
    "attributes": {
      "list": "bullet"
    },
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "var(--color-primary)",
      "link": "https://codingpotions.com/about"
    },
    "insert": "Sobre mí"
  },
  {
    "attributes": {
      "list": "bullet"
    },
    "insert": "\n"
  },
  {
    "attributes": {
      "background": "#18161b",
      "color": "var(--color-primary)",
      "link": "https://codingpotions.com/acerca-de-este-sitio"
    },
    "insert": "Acerca de este sitio"
  },
  {
    "attributes": {
      "list": "bullet"
    },
    "insert": "\n"
  },
  {
    "insert": "\n"
  }
]', 1, 2, '2024-05-1', null ),
    ('Ordenar un array', 'Crear una función que ordene un array de números de menor a mayor', NULL, 3, 1, '2023-08-01', NULL);

-- Insertar datos en la tabla USER
INSERT INTO `USER` (username, email, password_hashed, created_at, user_type_id, status, bio) VALUES
    ('admin', 'admin@example.com', 'admin123', '2022-01-01', 1, 'active', null),
    ('moderador1', 'moderador1@example.com', 'mod123', '2022-02-15', 2, 'active', null),
    ('moderador2', 'moderador2@example.com', 'mod456', '2022-03-20', 2, 'active', null),
    ('usuario1', 'usuario1@example.com', 'user123', '2022-04-10', 3, 'active', null),
    ('usuario2', 'usuario2@example.com', 'user456', '2022-05-05', 3, 'active', null),
    ('usuario3', 'usuario3@example.com', 'user789', '2022-06-15', 3, 'active', null),
    ('usuario4', 'usuario4@example.com', 'userABC', '2022-07-20', 3, 'active', null),
    ('usuario5', 'usuario5@example.com', 'userDEF', '2022-08-25', 3, 'active', null);

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


INSERT INTO POST_STATE (state_name) VALUES ('Borrador');
INSERT INTO POST_STATE (state_name) VALUES ('Publicado');
INSERT INTO POST_STATE (state_name) VALUES ('Eliminado');

