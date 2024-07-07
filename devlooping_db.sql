USE DEVLOOPING;

DROP VIEW IF EXISTS comment_summary_view;
DROP VIEW IF EXISTS post_summary_view;
DROP VIEW IF EXISTS CHALLENGE_DETAIL_VIEW;

DROP TABLE IF EXISTS USER_POST_LIKES ;
DROP TABLE IF EXISTS USER_POST_SHARES ;
DROP TABLE IF EXISTS COMMENT ;
DROP TABLE IF EXISTS POST ;
DROP TABLE IF EXISTS POST_STATE;
DROP TABLE IF EXISTS CHALLENGE_USER;
DROP TABLE IF EXISTS ACHIEVEMENT_USER;
DROP TABLE IF EXISTS ACHIEVEMENT;
DROP TABLE IF EXISTS PERMISSION_USER;
DROP TABLE IF EXISTS ANSWER_STATUS;
DROP TABLE IF EXISTS PERMISSION;
DROP TABLE IF EXISTS EXPECTED_OUTPUT;
DROP TABLE IF EXISTS TIP;
DROP TABLE IF EXISTS CHALLENGE_TESTS;
DROP TABLE IF EXISTS INPUT;   
DROP TABLE IF EXISTS OUTPUT;
DROP TABLE IF EXISTS TEST;
DROP TABLE IF EXISTS CHALLENGE;
DROP TABLE IF EXISTS CHALLENGE_STATUS;
DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS USER_TYPE;
DROP TABLE IF EXISTS DIFFICULTY;
DROP TABLE IF EXISTS CHALLENGE_CATEGORY;
DROP TABLE IF EXISTS CHALLENGE_TYPE;
DROP TABLE IF EXISTS POST_STATE;
DROP TABLE IF EXISTS CHALLENGE_USER;
DROP TABLE IF EXISTS ARCHIEVEMENT_USER;
DROP TABLE IF EXISTS PERMISSION_USER;
DROP TABLE IF EXISTS ACHIEVEMENT;
DROP TABLE IF EXISTS PERMISSION;
DROP TABLE IF EXISTS CHALLENGE;
DROP TABLE IF EXISTS CHALLENGE_TYPE;
DROP TABLE IF EXISTS DIFFICULTY ;
DROP TABLE IF EXISTS EXPECTED_OUTPUT;

-- Definición de tabla USER_TYPE (tabla independiente)
CREATE TABLE USER_TYPE (
    id_usertype INTEGER PRIMARY KEY AUTO_INCREMENT,
    name_type VARCHAR(255) NOT NULL,
    desc_type VARCHAR(255) NOT NULL
);

-- Definición de tabla PERMISSION (tabla independiente)
CREATE TABLE PERMISSION (
    id_permission INTEGER PRIMARY KEY AUTO_INCREMENT,
    name_permission VARCHAR(255) NOT NULL,
    desc_permission VARCHAR(255) NOT NULL
);

-- Definición de tabla ACHIEVEMENT (tabla independiente)
CREATE TABLE ACHIEVEMENT (
    id_achievement INTEGER PRIMARY KEY AUTO_INCREMENT,
    name_achievement VARCHAR(255) NOT NULL,
    desc_achievement VARCHAR(255) NOT NULL
);

-- Definición de tabla DIFFICULTY (tabla independiente)
CREATE TABLE DIFFICULTY(
	id_difficulty INTEGER PRIMARY KEY AUTO_INCREMENT,
	desc_difficulty VARCHAR(20) UNIQUE not null
);

-- Definición de tabla CHALLENGE_TIP (depende de CHALLENGE)
CREATE TABLE CHALLENGE_TYPE (
    id_type INTEGER PRIMARY KEY AUTO_INCREMENT,
    desc_type VARCHAR(255)
);

-- Definición de tabla CHALLENGE_CATEGORY 
CREATE TABLE CHALLENGE_CATEGORY(
	id_category INTEGER PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(255) NOT NULL,
    category_description VARCHAR(255) NOT NULL
);

-- Definición de tabla USER (depende de USER_TYPE)

CREATE TABLE `USER` (
    id_user INTEGER PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    bio VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hashed VARCHAR(255) NOT NULL,
    created_at DATE NOT NULL,
    verified_at DATE NULL,
    deactivated_at DATE,
    status VARCHAR(255),
    profile_pic_url mediumtext,
    user_type_id INTEGER NOT NULL,
    CONSTRAINT USER_USER_TYPE_FK FOREIGN KEY (user_type_id) REFERENCES USER_TYPE(id_usertype) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Definición de tabla CHALLENGE_STATUS (tabla independiente)
CREATE TABLE CHALLENGE_STATUS (
    id_status INTEGER PRIMARY KEY AUTO_INCREMENT,
    status_name VARCHAR(255) NOT NULL,
    status_desc VARCHAR(255) NOT NULL
);

-- Definición de tabla CHALLENGE 
CREATE TABLE CHALLENGE ( 
    id_challenge INTEGER PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    desc_challenge VARCHAR(255) NOT NULL,
    content mediumtext NULL ,
    default_value VARCHAR(255) NULL,
    CATEGORY_ID INTEGER NOT NULL,
    DIFFICULTY_id_difficulty  INTEGER, 
    CHALLENGE_TYPE_id_type INTEGER ,
    start_at DATE NOT NULL,
    end_at DATE,
    creator_id INTEGER,
    status_id INTEGER NOT NULL,
    CONSTRAINT CHALLENGE_DIFFICULTY_FK FOREIGN KEY (DIFFICULTY_id_difficulty) REFERENCES DIFFICULTY(id_difficulty) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT CHALLENGE_CATEGORY_FK FOREIGN KEY (CATEGORY_ID) REFERENCES CHALLENGE_CATEGORY(id_category) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT CHALLENGE_C_TYPE_FK FOREIGN KEY (CHALLENGE_TYPE_id_type) REFERENCES CHALLENGE_TYPE(id_type) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT CHALLENGE_CREATOR_FK FOREIGN KEY (creator_id) REFERENCES USER(ID_USER) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT CHALLENGE_STATUS_FK FOREIGN KEY (status_id) REFERENCES CHALLENGE_STATUS(id_status) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE TIP (
    ID_TIP INTEGER PRIMARY KEY AUTO_INCREMENT,
    TIP_TITLE VARCHAR(255) NOT NULL,
    TIP_DESC TEXT NOT NULL,
    CHALLENGE_id_challenge INTEGER NOT NULL,
    CONSTRAINT CHALLENGE_ID_FK FOREIGN KEY (CHALLENGE_id_challenge) REFERENCES CHALLENGE(id_challenge) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE TEST(
	ID_TEST INTEGER PRIMARY KEY AUTO_INCREMENT,
    test_description TEXT not null);
 
CREATE TABLE OUTPUT(
	ID_OUTPUT INTEGER PRIMARY KEY AUTO_INCREMENT,
    TEST_ID INTEGER NOT NULL,
    OUTPUT_VALUE VARCHAR(255) NOT NULL,
    CONSTRAINT TEST_OUTPUT_FK FOREIGN KEY (TEST_ID) REFERENCES TEST(ID_TEST) ON DELETE CASCADE ON UPDATE CASCADE);
    
CREATE TABLE INPUT (
	ID_INPUT INTEGER PRIMARY KEY AUTO_INCREMENT,
    TEST_ID INTEGER NOT NULL,
    INPUT_VALUE VARCHAR(255) NULL,
    CONSTRAINT TEST_INPUT_FK FOREIGN KEY (TEST_ID) REFERENCES TEST(ID_TEST) ON DELETE CASCADE ON UPDATE CASCADE);
    

CREATE TABLE CHALLENGE_TESTS(
	CHALLENGE_ID INTEGER NOT NULL,
    TEST_ID INTEGER NOT NULL,
    CONSTRAINT  CT_CHALLENGE_FK FOREIGN KEY (CHALLENGE_ID) REFERENCES CHALLENGE(ID_CHALLENGE) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT  CT_TEST_FK FOREIGN KEY (TEST_ID) REFERENCES TEST(ID_TEST) ON DELETE CASCADE ON UPDATE CASCADE
    );



-- Definición de tabla PERMISSION_USER (depende de PERMISSION y USER)
CREATE TABLE PERMISSION_USER (
    PERMISSION_id_permission INTEGER NOT NULL,
    USER_id_user INTEGER NOT NULL,
    CONSTRAINT PERMISSION_USER_PERMISSION_FK FOREIGN KEY (PERMISSION_id_permission) REFERENCES PERMISSION(id_permission) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT PERMISSION_USER_USER_FK FOREIGN KEY (USER_id_user) REFERENCES `USER`(id_user) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE KEY permission_user_unique (PERMISSION_id_permission, USER_id_user)
);

-- Definición de tabla ACHIEVEMENT_USER (depende de USER y ACHIEVEMENT)
CREATE TABLE ACHIEVEMENT_USER (
    USER_id_user INTEGER NOT NULL,
    ACHIEVEMENT_id_achievement INTEGER NOT NULL,
    created_at DATE NOT NULL,
    CONSTRAINT ARCHIEVEMENT_USER_USER_FK FOREIGN KEY (USER_id_user) REFERENCES `USER`(id_user) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT ARCHIEVEMENT_USER_ACHIEVEMENT_FK FOREIGN KEY (ACHIEVEMENT_id_achievement) REFERENCES ACHIEVEMENT(id_achievement) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE KEY archievement_user_unique (USER_id_user, ACHIEVEMENT_id_achievement)
);

CREATE TABLE ANSWER_STATUS (
	STATUS_ID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY	,
    status_name VARCHAR(20) NOT NULL,
    status_desc varchar(100) not null);

-- Definición de tabla CHALLENGE_USER (depende de USER y CHALLENGE)
CREATE TABLE CHALLENGE_USER (
	id_challenge_user int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    USER_id_user INTEGER NOT NULL,
    CHALLENGE_id_challenge INTEGER NOT NULL,
    answer_code mediumtext,
    ANSWER_STATUS_id int not NULL,
    segundos_dedicados int not null,
    start_date datetime not null,
    end_date datetime null,
    tries int null,    
    CONSTRAINT ANSWER_STATUS_FK FOREIGN KEY (ANSWER_STATUS_id) REFERENCES ANSWER_STATUS(STATUS_ID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT CHALLENGE_USER_USER_FK FOREIGN KEY (USER_id_user) REFERENCES `USER`(id_user) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT CHALLENGE_USER_CHALLENGE_FK FOREIGN KEY (CHALLENGE_id_challenge) REFERENCES CHALLENGE(id_challenge) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE KEY challenge_user_unique (USER_id_user, CHALLENGE_id_challenge)
);

CREATE TABLE POST_STATE(
	id_post_state integer primary key auto_increment,
    state_name varchar(50) not null
);


 -- Definición de tabla POST (depende de USER)
CREATE TABLE POST (
    id_post INTEGER PRIMARY KEY AUTO_INCREMENT,
    post_content longtext not null,
    post_state_id INTEGER NOT NULL,
    created_at datetime  not null,
    updated_at datetime,
    deleted_at  datetime,
    USER_id_user INTEGER NOT NULL,
    etiquetas varchar(500) NOT NULL,
    CONSTRAINT POST_USER_FK FOREIGN KEY (USER_id_user) REFERENCES `USER`(id_user) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT POST_STATE_FK FOREIGN KEY (post_state_id) REFERENCES `POST_STATE`(id_post_state) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Definición de tabla COMMENT (depende de USER y POST)
CREATE TABLE COMMENT (
    id_comment INTEGER PRIMARY KEY AUTO_INCREMENT,
    comment_content longtext not null,
    post_state_id INTEGER NOT NULL,
    created_at datetime  not null,
    deleted_at  datetime,
    USER_id_user INTEGER NOT NULL,
    POST_id_post INTEGER NOT NULL,
    CONSTRAINT COMMENT_USER_FK FOREIGN KEY (USER_id_user) REFERENCES USER(id_user) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT COMMENT_POST_FK FOREIGN KEY (POST_id_post) REFERENCES POST(id_post) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT COMMENT_STATE_FK FOREIGN KEY (post_state_id) REFERENCES POST_STATE(id_post_state) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE USER_POST_LIKES (
	USER_id_user INTEGER,
    POST_id_post INTEGER,
    CONSTRAINT LIKES_USER_FK FOREIGN KEY (USER_id_user) REFERENCES USER(id_user) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT LIKES_POST_FK FOREIGN KEY (POST_id_post) REFERENCES POST(id_post) ON DELETE CASCADE ON UPDATE CASCADE
    );
CREATE TABLE USER_POST_SHARES (
	USER_id_user INTEGER,
    POST_id_post INTEGER,
    CONSTRAINT SHARES_USER_FK FOREIGN KEY (USER_id_user) REFERENCES USER(id_user) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT SHARES_POST_FK FOREIGN KEY (POST_id_post) REFERENCES POST(id_post) ON DELETE CASCADE ON UPDATE CASCADE
    );

CREATE VIEW post_summary_view AS
SELECT p.id_post AS id_post, 
           ps.id_post_state AS id_post_state, 
           u.id_user AS user_id, 
           p.post_content AS post_content, 
           p.created_at AS created_at, 
           p.updated_at AS updated_at,
           p.deleted_at AS deleted_at, 
           p.etiquetas AS etiquetas,
           -- MOSTRAR TODOS LOS IDs de los usuarios que han dado like a la publicación
              (SELECT JSON_ARRAYAGG(l.USER_id_user) FROM user_post_likes l WHERE l.post_id_post = p.id_post) AS likes_users,

           (SELECT COUNT(post_id_post) FROM comment c WHERE c.post_id_post = p.id_post) AS comments_count, 
           (SELECT COUNT(post_id_post) FROM user_post_shares s WHERE s.post_id_post= p.id_post) AS shares_count 
    FROM Post p 
    JOIN post_state ps ON p.post_state_id = ps.id_post_state
    JOIN User u ON p.USER_id_user = u.id_user;

CREATE VIEW comment_summary_view AS
 SELECT 	c.id_comment as id_comment,
		c.comment_content as comment_content,
        c.post_state_id as post_state_id,
        c.created_at as created_at,
        c.deleted_at as deleted_at,
        u.id_user AS user_id,
        p.id_post AS post_id
FROM COMMENT c
JOIN User u ON c.USER_id_user = u.id_user
JOIN post p ON c.POST_id_post = p.id_post;

DROP VIEW IF EXISTS CHALLENGE_DETAIL_VIEW;
CREATE VIEW challenge_detail_view as
SELECT
    c.id_challenge AS challenge_id,
    c.title,
    c.desc_challenge,
    c.content,
    c.default_value,
    c.CATEGORY_ID,
    c.DIFFICULTY_id_difficulty,
    c.CHALLENGE_TYPE_id_type,
    c.creator_id,
    c.status_id,
    c.start_at,
    c.end_at,
    (
        SELECT JSON_ARRAYAGG(
            JSON_OBJECT('tip_title', t.TIP_TITLE, 'tip_desc', t.TIP_DESC)
        )
        FROM TIP t
        WHERE t.CHALLENGE_id_challenge = c.id_challenge
    ) AS tips,
    test_data.test_description,
    (
        SELECT JSON_ARRAYAGG(
            JSON_OBJECT('input_value', i.INPUT_VALUE)
        )
        FROM INPUT i
        WHERE i.TEST_ID = test_data.id_test
    ) AS input_values,
    (
        SELECT JSON_ARRAYAGG(
            JSON_OBJECT('output_value', o.OUTPUT_VALUE)
        )
        FROM OUTPUT o
        WHERE o.TEST_ID = test_data.id_test
    ) AS output_values,
    -- ARREGLO DE LOS IDS DE TODOS LOS USUARIOS QUE INICIARON EL RETO
    (
        SELECT JSON_ARRAYAGG(cu.USER_id_user)
        FROM CHALLENGE_USER cu
        WHERE cu.CHALLENGE_id_challenge = c.id_challenge
    ) AS started_users
FROM
    CHALLENGE AS c
LEFT JOIN
    (
        SELECT
            ct.CHALLENGE_ID,
            t.id_test,
            t.test_description
        FROM
            CHALLENGE_TESTS ct
        JOIN
            TEST t ON ct.TEST_ID = t.id_test
    ) AS test_data ON c.id_challenge = test_data.CHALLENGE_ID

GROUP BY
    c.id_challenge, c.title, c.desc_challenge, c.content, c.CATEGORY_ID, c.DIFFICULTY_id_difficulty, c.CHALLENGE_TYPE_id_type, c.start_at, c.end_at, test_data.test_description, test_data.id_test;
