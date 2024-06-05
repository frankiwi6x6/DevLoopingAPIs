DELIMITER //

CREATE PROCEDURE SP_SaveChallenge(
    IN title VARCHAR(255), IN desc_challenge VARCHAR(255), IN content MEDIUMTEXT, 
    IN category_id INTEGER, IN difficulty_id INTEGER, IN type_id INTEGER, 
    IN start_at DATE, IN end_at DATE,
    IN tip_titles TEXT, IN tip_descs TEXT,  -- Arrays de títulos y descripciones de TIP
    IN test_description TEXT,
    IN input_values TEXT, IN output_values TEXT -- Arrays de INPUT y OUTPUT
)
BEGIN
    DECLARE challenge_id INT;
    DECLARE test_id INT;
    DECLARE tip_idx INT DEFAULT 1;
    DECLARE input_idx INT DEFAULT 1;
    DECLARE output_idx INT DEFAULT 1;
    DECLARE tip_title VARCHAR(255);
    DECLARE tip_desc TEXT;
    DECLARE input_value VARCHAR(255);
    DECLARE output_value VARCHAR(255);
    
    -- Insertar en CHALLENGE
    INSERT INTO CHALLENGE (title, desc_challenge, content, CATEGORY_ID, DIFFICULTY_id_difficulty, CHALLENGE_TYPE_id_type, start_at, end_at)
    VALUES (title, desc_challenge, content, category_id, difficulty_id, type_id, start_at, end_at);
    SET challenge_id = LAST_INSERT_ID();

    -- Manejar múltiples TIPs
    WHILE tip_idx <= JSON_LENGTH(tip_titles) DO
        SET tip_title = JSON_UNQUOTE(JSON_EXTRACT(tip_titles, CONCAT('$[', tip_idx - 1, ']')));
        SET tip_desc = JSON_UNQUOTE(JSON_EXTRACT(tip_descs, CONCAT('$[', tip_idx - 1, ']')));
        INSERT INTO TIP (TIP_TITLE, TIP_DESC, CHALLENGE_id_challenge) VALUES (tip_title, tip_desc, challenge_id);
        SET tip_idx = tip_idx + 1;
    END WHILE;
    
    -- Insertar en TEST
    INSERT INTO TEST (test_description) VALUES (test_description);
    SET test_id = LAST_INSERT_ID();
    
    -- Manejar múltiples INPUTs
    WHILE input_idx <= JSON_LENGTH(input_values) DO
        SET input_value = JSON_UNQUOTE(JSON_EXTRACT(input_values, CONCAT('$[', input_idx - 1, ']')));
        INSERT INTO INPUT (TEST_ID, INPUT_VALUE) VALUES (test_id, input_value);
        SET input_idx = input_idx + 1;
    END WHILE;

    -- Manejar múltiples OUTPUTs
    WHILE output_idx <= JSON_LENGTH(output_values) DO
        SET output_value = JSON_UNQUOTE(JSON_EXTRACT(output_values, CONCAT('$[', output_idx - 1, ']')));
        INSERT INTO OUTPUT (TEST_ID, OUTPUT_VALUE) VALUES (test_id, output_value);
        SET output_idx = output_idx + 1;
    END WHILE;

    -- Insertar en CHALLENGE_TESTS
    INSERT INTO CHALLENGE_TESTS (CHALLENGE_ID, TEST_ID) VALUES (challenge_id, test_id);
END //

DELIMITER ;