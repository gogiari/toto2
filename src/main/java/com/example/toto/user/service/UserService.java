package com.example.toto.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.toto.user.mapper.UserMapper;
import com.example.toto.user.vo.UserVo;

@Service
public class UserService {

    @Autowired
    public UserMapper userMapper;

    public List<UserVo> getUserList() {
        return userMapper.getUserList();
    }

    public void insertUser(UserVo userVo) {
        userMapper.insertUser(userVo);
    }

    public UserVo getUser(String userid) {
        return userMapper.getUser(userid);
    }

    public void deleteUser(String userid) {
        userMapper.deleteUser(userid);
    }

    public UserVo getUserLog(Map<String, Object> map) {
        UserVo userVo = userMapper.getUserLog(map);
        return userVo;
    }


}
