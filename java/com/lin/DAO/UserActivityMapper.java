package com.lin.DAO;

import com.lin.POJO.UserActivity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivityMapper {
    @Select("select * from tb_user_activity where openid = CAST(#{openid} AS binary(28)) and a_id = #{a_id}")
    UserActivity selectByOpenidAndA_id(@Param("openid")String openid, @Param("a_id")int a_id);

    @Insert("insert into tb_user_activity(openid, a_id) values(#{openid}, #{a_id})")
    int insertinto(@Param("openid")String openid, @Param("a_id")int a_id);

    @Select("select a_id from tb_user_activity where openid = CAST(#{openid} AS binary(28))")
    int[] selectAllByOpenid(@Param("openid")String openid);

    @Delete("delete from tb_user_activity where openid = CAST(#{openid} AS binary(28)) and a_id = #{a_id}")
    int delete(@Param("openid")String openid, @Param("a_id")int a_id);

    @Select("select openid from tb_user_activity where a_id = #{a_id}")
    String[] selectAllOpenByA_id(@Param("a_id") int a_id);
}
