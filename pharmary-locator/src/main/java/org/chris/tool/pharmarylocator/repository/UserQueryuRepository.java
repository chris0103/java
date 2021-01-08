package org.chris.tool.pharmarylocator.repository;

import java.util.List;

import org.chris.tool.pharmarylocator.entity.UserQueries;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserQueryuRepository extends CrudRepository<UserQueries, Long> {

    List<UserQueries> findByUserAndPoiId(String user, String poiId);
    
    @Query("select a from UserQueries a where queryCount = (select max(queryCount) from UserQueries where user = a.user) order by a.user")
    List<UserQueries> showStat();
}
