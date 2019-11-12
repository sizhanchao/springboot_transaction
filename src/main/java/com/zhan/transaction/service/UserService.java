package com.zhan.transaction.service;

import com.zhan.transaction.model.User;

import java.util.List;

/**
 * @author zhan
 * @since 2019-11-08 11:41
 */
public interface UserService {
    List<User> getUserList();

    User findUserById(long id);
    void save(User user);
    void edit(User user);
    User updateAge(String age);
    void delete(long id);
}
