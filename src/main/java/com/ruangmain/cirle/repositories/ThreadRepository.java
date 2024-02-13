package com.ruangmain.cirle.repositories;

import com.ruangmain.cirle.models.Thread;
import com.ruangmain.cirle.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepository extends CrudRepository<Thread, Long> {

    @Query("select t from Thread t where t.user = ?1")
    Iterable<Thread> findByUser(User user);

    @Query("select t from Thread t inner join User u on t.user.id = u.id where u.id in (select f.following.id from Follow f where f.followers = ?1) order by t.created_at desc ")
    Iterable<Thread> findByFollowing(User user);

}
