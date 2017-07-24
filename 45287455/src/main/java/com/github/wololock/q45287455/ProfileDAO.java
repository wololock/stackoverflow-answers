package com.github.wololock.q45287455;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(Profile.Mapper.class)
public interface ProfileDAO {
    
    @SqlQuery("select * from profile where profile_id >= :from and profile_id < :to")
    List<Profile> findAllInRange(@Bind("from") int from, @Bind("to") int to);

    void close();
}
