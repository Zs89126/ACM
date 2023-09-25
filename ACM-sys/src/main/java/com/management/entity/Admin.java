package com.management.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("admin_info")
public class Admin {

    // 主键id 自增
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    // 管理员身份证号
    @TableField(value = "userid")
    private String userId;

    // 管理员名字
    @TableField(value = "username")
    private String userName;

    // 登录密码
    @TableField(value = "password")
    private String passWord;

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
