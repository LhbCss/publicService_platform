package com.lin.DAO;

import com.lin.POJO.User;
import com.lin.POJO.User_Info;
import com.lin.POJO.admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
@Select("select * from tb_user where openid = CAST(#{openid} AS binary(28))")
User SelectByOpenid(String openid);

@Insert("insert into tb_user(openid) values(#{openid})")
int InsertByOpenid(String openid);
@Select("select * from tb_user_info where openid = CAST(#{openid} AS binary(28))")
User_Info SelectUserInfoByOpenid(String openid);
@Update("update tb_user_info set name = #{name}, age = #{age}, phone = #{phone}, address = #{address} where openid = #{openid}")
int updateUserInfo(User_Info userInfo);
@Insert("insert into tb_user_info(openid, name, age, phone, address) values(#{openid}, #{name}, #{age}, #{phone}, #{address})")
int InsertUserInfo(User_Info userInfo);
@Select("select * from tb_admin where name = CAST(#{name} AS BINARY(16)) and password = CAST(#{password} AS BINARY(24))")
admin selectAdmin(admin a);
}

