package com.management.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("coach_register")
public class CoachRegister {

    // 主键id 自增
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    // 当前登录用户的身份证号
    @TableField(value = "userid")
    private String userId;

    // 当前登录用户的姓名
    @TableField(value = "username")
    private String userName;

    // 当前用户的性别
    @TableField(value = "gender")
    private String gender;

    // 学校的中文全称名字
    @TableField(value = "sc_full_cname")
    private String ScFullCname;

    // 登录密码
    @TableField(value = "password")
    private String passWord;

    // 用户手机号
    @TableField(value = "phone_number")
    private String phoneNumber;

    // 登录邮箱
    @TableField(value = "email")
    private String email;

    // 审核意见
    @TableField(value = "recommend")
    private String recommend;

    // 审核状态
    @TableField(value = "status")
    private String status;

    // 逻辑删除
    @TableField(value = "is_deleted")
    @TableLogic
    private String isDeleted;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @TableField(value = "gmt_create")
    private Date gmtCreate;

    // 修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @TableField(value = "gmt_modified")
    private Date gmtModified;
}
