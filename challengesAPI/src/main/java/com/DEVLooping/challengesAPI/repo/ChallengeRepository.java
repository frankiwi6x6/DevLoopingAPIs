package com.DEVLooping.challengesAPI.repo;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.DEVLooping.challengesAPI.entity.ChallengeRequest;

@Repository
public interface ChallengeRepository extends JpaRepository<ChallengeRequest, Integer> {

    @Procedure(name = "SP_saveChallenge")
    void SP_saveChallenge(
            @Param("title") String title,
            @Param("desc_challenge") String desc_challenge,
            @Param("content") String content,
            @Param("defaultValue") String default_value,
            @Param("category_id") Integer category_id,
            @Param("difficulty_id") Integer difficulty_id,
            @Param("type_id") Integer type_id,
            @Param("start_at") Date start_at,
            @Param("end_at") Date end_at,
            @Param("tip_titles") String tip_titles,
            @Param("tip_descs") String tip_descs,
            @Param("test_description") String test_description,
            @Param("input_values") String input_values,
            @Param("output_values") String output_values
    );
}
