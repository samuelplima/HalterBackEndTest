package com.herd.test.repository;

import com.herd.test.model.entities.Herd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HerdRepository extends JpaRepository<Herd, Integer> {

    @Query(value = "select id, collar_id, cow_number, collar_status from tb_herd where cow_number =:cowNumber"
    , nativeQuery=true)
    Herd findCowByNumber(final int cowNumber);

    @Query(value = "select id, collar_id, cow_number, collar_status from tb_herd where collar_id =:collarId"
            , nativeQuery=true)
    Herd findCollarIdByNumber(final int collarId);

}
