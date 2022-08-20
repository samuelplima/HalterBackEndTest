package com.herd.test.repository;

import com.herd.test.model.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Integer> {

    @Query(value = "select * from tb_farm where farm_name =:farmName"
            , nativeQuery=true)
    Farm findFarmByName(final String farmName);

    List<Farm> findByfarmNameLike(final String farmName);

    Farm findFarmByFarmNameAndId(final String farmName, int id);

}
