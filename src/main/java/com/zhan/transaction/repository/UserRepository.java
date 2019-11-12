package com.zhan.transaction.repository;

import com.zhan.transaction.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


/**
 * @author zhan
 * @since 2019-11-08 11:38
 */
public interface UserRepository extends JpaRepository<User, Long> {

//    @Lock(LockModeType.PESSIMISTIC_READ)
    public User findById(long id);


    @Modifying
    @Query("update User set age = ?1 where id = 3")
    int updateAge(int age);

    void deleteById(Long id);
}
