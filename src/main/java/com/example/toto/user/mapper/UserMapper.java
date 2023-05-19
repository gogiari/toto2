package com.example.toto.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.toto.user.vo.UserVo;

@Repository
@Mapper
public interface UserMapper {

    List<UserVo> getUserList();

	void insertUser(UserVo userVo);

    UserVo getUser(String userid);

    void deleteUser(String userid);

    UserVo getUserLog(Map<String, Object> map);

}
