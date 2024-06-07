DROP PROCEDURE IF EXISTS SP_UpdateChallenge;
DELIMITER //

CREATE PROCEDURE SP_UpdateChallenge(
    IN challengeId INT,
    IN title VARCHAR(255), IN desc_challenge VARCHAR(255), IN content MEDIUMTEXT, IN default_value VARCHAR(255),
    IN category_id INTEGER, IN difficulty_id INTEGER, IN type_id INTEGER, 
    IN start_at DATE, IN end_at DATE,
    IN creator_id INTEGER,
    IN tip_titles TEXT, IN tip_descs TEXT, 
    IN test_description TEXT,
    IN input_values TEXT, IN output_values TEXT 
)
BEGIN
    DECLARE tip_idx INT DEFAULT 0;
    DECLARE input_idx INT DEFAULT 0;
    DECLARE output_idx INT DEFAULT 0;
    DECLARE tip_title VARCHAR(255);
    DECLARE tip_desc TEXT;
    DECLARE input_value VARCHAR(255);
    DECLARE output_value VARCHAR(255);
    DECLARE testId INT;

    -- Update CHALLENGE table
    UPDATE CHALLENGE
    SET title = title, desc_challenge = desc_challenge, content = content, default_value = default_value,
        CATEGORY_ID = category_id, DIFFICULTY_id_difficulty = difficulty_id, CHALLENGE_TYPE_id_type = type_id,
        creator_id = creator_id,
        start_at = start_at, end_at = end_at
    WHERE id_challenge = challengeId;

    DELETE FROM TIP WHERE CHALLENGE_id_challenge = challengeId;

    WHILE tip_idx < JSON_LENGTH(tip_titles) DO
        SET tip_title = JSON_UNQUOTE(JSON_EXTRACT(tip_titles, CONCAT('$[', tip_idx, ']')));
        SET tip_desc = JSON_UNQUOTE(JSON_EXTRACT(tip_descs, CONCAT('$[', tip_idx, ']')));
        INSERT INTO TIP (TIP_TITLE, TIP_DESC, CHALLENGE_id_challenge) VALUES (tip_title, tip_desc, challengeId);
        SET tip_idx = tip_idx + 1;
    END WHILE;
    
    SELECT TEST_ID INTO testId FROM CHALLENGE_TESTS WHERE CHALLENGE_ID = challengeId LIMIT 1;

    IF testId IS NULL THEN
        INSERT INTO TEST (test_description) VALUES (test_description);
        SET testId = LAST_INSERT_ID();
        INSERT INTO CHALLENGE_TESTS (CHALLENGE_ID, TEST_ID) VALUES (challengeId, testId);
    ELSE
        UPDATE TEST
        SET test_description = test_description
        WHERE ID_TEST = testId;
    END IF;


    DELETE FROM INPUT WHERE TEST_ID = testId;

    WHILE input_idx < JSON_LENGTH(input_values) DO
        SET input_value = JSON_UNQUOTE(JSON_EXTRACT(input_values, CONCAT('$[', input_idx, ']')));
        INSERT INTO INPUT (TEST_ID, INPUT_VALUE) VALUES (testId, input_value);
        SET input_idx = input_idx + 1;
    END WHILE;

    DELETE FROM OUTPUT WHERE TEST_ID = testId;

    WHILE output_idx < JSON_LENGTH(output_values) DO
        SET output_value = JSON_UNQUOTE(JSON_EXTRACT(output_values, CONCAT('$[', output_idx, ']')));
        INSERT INTO OUTPUT (TEST_ID, OUTPUT_VALUE) VALUES (testId, output_value);
        SET output_idx = output_idx + 1;
    END WHILE;
END //

DELIMITER ;