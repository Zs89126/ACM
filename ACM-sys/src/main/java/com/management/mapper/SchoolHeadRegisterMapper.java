package com.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.management.entity.SchoolHeadRegister;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Service;


@Mapper
public interface SchoolHeadRegisterMapper extends BaseMapper<SchoolHeadRegister> {

    /**
     * 学校联系人注册，向表中插入数据
     * @author zs
     * @param schoolHeadRegister
     * @return
     */
    @Insert({
            "insert into school_head_register",
            "userid,username,gender,phone_number,email,password,materials,recommend,status,sc_full_cname,sc_full_ename,sc_abbreviation_cname,address," ,
            "sc_abbreviation_ename,post_code,is_deleted,gmt_create,gmt_modified",
            "values (#{userId,jdbcType=VARCHAR},#{userName,jdbcType=VARCHAR},#{gender,jdbcType=VARCHAR},#{phoneNumber,jdbcType=VARCHAR},#{email,jdbcType=VARCHAR}",
            "#{passWord,jdbcType=VARCHAR},#{materils,jdbcType=VARCHAR},#{recommend,jdbcType=VARCHAR},#{status,jdbcType=VARCHAR},#{ScFullCname,jdbcType=VARCHAR}",
            "#{ScFullEname,jdbcType=VARCHAR},#{ScAbbreCname,jdbcType=VARCHAR},#{address,jdbcType=VARCHAR},#{ScAbbreEname,jdbcType=VARCHAR},#{postCode,jdbcType=VARCHAR}",
            "#{isDeleted,jdbcType=VARCHAR},, #{gmtCreate,jdbcType=TIMESTAMP}, #{gmtModified,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement = "SELECT SCOPE_IDENTITY();", keyProperty = "id", before = false, resultType = Long.class)
    int insertSchoolHeadRegister(SchoolHeadRegister schoolHeadRegister);

    /**
     * @author zs
     * @date 2023/9/25
     * @param ScFullCname
     * @return
     */
    @Select({
            "select *" +
                    "from school_head_register" +
                    "where is_deleted = '0' and sc_full_cname = #{ScFullCname, jdbcType=VARCHAR}"
    })
    SchoolHeadRegister selectScHeadRegByScname(String ScFullCname);

}
