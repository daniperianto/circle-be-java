package com.ruangmain.cirle.repositories;

import com.ruangmain.cirle.models.Follow;
import com.ruangmain.cirle.models.FollowPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FollowRepository extends CrudRepository<Follow, FollowPK> {

    @Query("select count(f) from Follow f where f.followers.id = :id")
    Integer countByFollowing(Long id);

    @Query("select count(f) from Follow f where f.following.id = :id")
    Integer countByFollowers(Long id);

    @Query(value = "select u.id from users u left join ( select * from follows where followers_id = ?1 ) as f on u.id = f.following_id where f.followers_id is null order by random() limit 5", nativeQuery = true)
    List<Long> findSuggestedAccountsId(Long id);


    @Query(value = "select u.id from users u inner join follows f on u.id = f.following_id where f.followers_id = ?1", nativeQuery = true)
    Iterable<Long> findFollowing(Long id);

    @Query(value = "select u.id from users u inner join follows f on u.id = f.followers_id where f.following_id = ?1", nativeQuery = true)
    Iterable<Long> findFollowers(Long id);

    @Query(value = "select count(f) from follows f where f.followers_id = ?1 and f.following_id = ?2", nativeQuery = true)
    Integer isFollowing(Long userId, Long id);
}
