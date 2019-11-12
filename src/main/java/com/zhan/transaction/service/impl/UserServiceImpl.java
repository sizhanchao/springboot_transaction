package com.zhan.transaction.service.impl;

import com.zhan.transaction.model.User;
import com.zhan.transaction.repository.UserRepository;
import com.zhan.transaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhan
 * @since 2019-11-08 11:42
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public List<User> getUserList() {
        /*User byId = userRepository.findById(0L);
        byId.setPassword("11111");
        userRepository.save(byId);*/
        return userRepository.findAll();
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public User findUserById(long id) {
        User byId = userRepository.findById(id);
        byId.setPassword("aaaaaaa");
        userRepository.save(byId);
//        userRepository.saveAndFlush(byId);
        userRepository.flush();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        throw new Exception("aaaaa");
//        int y=0;
//        int x=10/y;
        return byId;
    }

    @Override
//    @Transactional(rollbackFor = Exception.class,isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public void save(User user) {
        try {
            userRepository.save(user);
            int count = 10/0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(User user) {
        userRepository.save(user);
    }

    @Override
    public User updateAge(String age) {
        return null;
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
