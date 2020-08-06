package com.jt.sso.mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.jt.common.mapper.SysMapper;
import com.jt.sso.pojo.User;
import com.sun.org.apache.xpath.internal.operations.And;
@Repository
public interface UserMapper extends SysMapper<User> {

	@Select("Select * from tb_user where username=#{username} and password=#{password}")
	User findUserByUP(User user);

	
}
