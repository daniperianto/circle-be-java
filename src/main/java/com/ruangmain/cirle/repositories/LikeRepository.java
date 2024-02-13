package com.ruangmain.cirle.repositories;

import com.ruangmain.cirle.models.Like;
import com.ruangmain.cirle.models.LikePK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends CrudRepository<Like, LikePK> {

    @Query("select count(l) from Like l where l.thread.id = ?1")
    Integer getThreadLikes(Long id);
}
