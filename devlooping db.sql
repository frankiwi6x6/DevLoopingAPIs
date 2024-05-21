USE DEVLOOPING;
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

-- Definición de tabla CHALLENGE (tabla dependiente de DIFFICULTY y CHALLENGE_TYPE)
CREATE TABLE CHALLENGE ( 
    id_challenge INTEGER PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    desc_challenge VARCHAR(255) NOT NULL,
    content mediumtext NULL ,
    DIFFICULTY_id_difficulty  INTEGER, 
    CHALLENGE_TYPE_id_type INTEGER ,
    start_at DATE NOT NULL,
    end_at DATE,
    CONSTRAINT CHALLENGE_DIFFICULTY_FK FOREIGN KEY (DIFFICULTY_id_difficulty) REFERENCES DIFFICULTY(id_difficulty) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT CHALLENGE_C_TYPE_FK FOREIGN KEY (CHALLENGE_TYPE_id_type) REFERENCES CHALLENGE_TYPE(id_type) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE EXPECTED_OUTPUT(
	id_expected_output INTEGER PRIMARY KEY AUTO_INCREMENT,
    CHALLENGE_id INTEGER,
    input1 text,
    input2 text,
    input3 text,
    input4 text,
    output text not null,
    CONSTRAINT EO_CHALLENGE_FK FOREIGN KEY (CHALLENGE_id) REFERENCES CHALLENGE(id_challenge) ON DELETE CASCADE ON UPDATE CASCADE
    
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

-- Definición de tabla PERMISSION_USER (depende de PERMISSION y USER)
CREATE TABLE PERMISSION_USER (
    PERMISSION_id_permission INTEGER NOT NULL,
    USER_id_user INTEGER NOT NULL,
    CONSTRAINT PERMISSION_USER_PERMISSION_FK FOREIGN KEY (PERMISSION_id_permission) REFERENCES PERMISSION(id_permission) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT PERMISSION_USER_USER_FK FOREIGN KEY (USER_id_user) REFERENCES `USER`(id_user) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE KEY permission_user_unique (PERMISSION_id_permission, USER_id_user)
);

-- Definición de tabla ARCHIEVEMENT_USER (depende de USER y ACHIEVEMENT)
CREATE TABLE ARCHIEVEMENT_USER (
    USER_id_user INTEGER NOT NULL,
    ACHIEVEMENT_id_achievement INTEGER NOT NULL,
    created_at DATE NOT NULL,
    CONSTRAINT ARCHIEVEMENT_USER_USER_FK FOREIGN KEY (USER_id_user) REFERENCES `USER`(id_user) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT ARCHIEVEMENT_USER_ACHIEVEMENT_FK FOREIGN KEY (ACHIEVEMENT_id_achievement) REFERENCES ACHIEVEMENT(id_achievement) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE KEY archievement_user_unique (USER_id_user, ACHIEVEMENT_id_achievement)
);

CREATE TABLE ANSWER_STATUS (
	STATUS_ID     INTEGER NOT NULL PRIMARY KEY	,
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
    CONSTRAINT CHALLENGE_STATUS_FK FOREIGN KEY (ANSWER_STATUS_id) REFERENCES ANSWER_STATUS(STATUS_ID) ON DELETE CASCADE ON UPDATE CASCADE,
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
DROP VIEW post_summary_view;
CREATE VIEW post_summary_view AS
SELECT p.id_post AS id_post, 
           ps.id_post_state AS id_post_state, 
           u.id_user AS user_id, 
           p.post_content AS post_content, 
           p.created_at AS created_at, 
           p.updated_at AS updated_at,
           p.deleted_at AS deleted_at, 
           p.etiquetas AS etiquetas,
           (SELECT COUNT(post_id_post) FROM user_post_likes l WHERE l.post_id_post = p.id_post) AS likes_count, 
           (SELECT COUNT(post_id_post) FROM comment c WHERE c.post_id_post = p.id_post) AS comments_count, 
           (SELECT COUNT(post_id_post) FROM user_post_shares s WHERE s.post_id_post= p.id_post) AS shares_count 
    FROM Post p 
    JOIN post_state ps ON p.post_state_id = ps.id_post_state
    JOIN User u ON p.USER_id_user = u.id_user;
        

