package com.lin.DAO;

import com.lin.POJO.Activity;
import com.lin.POJO.briefInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityMapper {
    @Insert("insert into tb_activity(a_name, a_icon, a_date, a_lore, a_party, a_phone, a_describe, a_image) values(#{a_name}, #{a_icon}, #{a_date}, #{a_lore}, #{a_party}, #{a_phone}, #{a_describe}, #{a_image})")
    int insertActivity(Activity activity);
    @Select("select * from tb_activity")
    Activity[] selectAllActivity();
    @Select("SELECT COUNT(a_id) FROM tb_activity")
    int getActivityCount();
    @Delete("delete from tb_activity where a_id = #{a_id}")
    int deleteActivityByA_id(int a_id);
    @Update("update tb_activity set a_name = #{a_name}, a_icon = #{a_icon}, a_date = #{a_date}, a_lore = #{a_lore}, a_party = #{a_party}, a_phone = #{a_name}, a_phone = #{a_name}, a_describe = #{a_describe}, a_image = #{a_image} where a_id = #{a_id}")
    int updateActivityByActivity(Activity activity);
    @Select("select * from tb_activity where a_id = #{a_id}")
    Activity selectActivityByA_id(int a_id);
}

