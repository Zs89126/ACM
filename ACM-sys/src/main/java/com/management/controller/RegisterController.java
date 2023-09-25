package com.management.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.management.common.ResultEntity;
import com.management.service.SchoolHeadRegisterService;
import com.management.utils.BasicResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.ParseException;

@Api(tags = "1.注册相关")
@ApiSupport(order = 1)
@RestController
@RequestMapping("register")
public class RegisterController {

    @Resource
    SchoolHeadRegisterService schoolHeadRegisterService;

    @ApiOperation(value = "新增学校联系人注册表信息", notes = "所需参数[userid,username,gender,phone_number,email,password,materials,recommend,status,sc_full_cname,sc_full_ename,sc_abbreviation_cname,address,sc_abbreviation_ename,post_code]")
    @ApiOperationSupport(order = 1,author = "zs")
    @PostMapping("addSchoolHeadRegister")
    public ResultEntity addSchoolHeadRegister(@RequestParam("file") MultipartFile material,
                                              @ApiParam(name = "userid", value = "身份证号") @RequestParam("userid") String userId,
                                              @ApiParam(name = "username", value = "名字") @RequestParam("username") String userName,
                                              @ApiParam(name = "gender", value = "性别") @RequestParam("gender") String gender,
                                              @ApiParam(name = "phone-number", value = "手机号") @RequestParam("phone-number") String phoneNumber,
                                              @ApiParam(name = "email", value = "邮箱") @RequestParam("email") String email,
                                              @ApiParam(name = "password", value = "密码") @RequestParam("password") String passWord,
                                              @ApiParam(name = "recommend", value = "审核建议") @RequestParam("recommend") String recommend,
                                              @ApiParam(name = "sc-full-cname", value = "学校中文全称") @RequestParam("sc-full-cname") String ScFullCname,
                                              @ApiParam(name = "sc-full-ename", value = "学校英文全称") @RequestParam("sc-full-ename") String ScFullEname,
                                              @ApiParam(name = "sc-abbreviation-cname", value = "学校中文缩写") @RequestParam("sc-abbreviation-cname") String ScAbbreCname,
                                              @ApiParam(name = "sc-abbreviation-ename", value = "学校英文缩写") @RequestParam("sc-abbreviation-ename") String ScAbbreEname,
                                              @ApiParam(name = "address", value = "学校地址") @RequestParam("address") String address,
                                              @ApiParam(name = "post-code", value = "邮政编码") @RequestParam("post-code") String postCode
                                              ){
        String[] params = new String[]{userId, userName, gender, phoneNumber, email,passWord,recommend,ScFullCname,ScFullEname,ScAbbreCname,ScAbbreEname,address,postCode};
        return BasicResponseUtils.success(schoolHeadRegisterService.addSchoolHeadRegister(material, params));
    }

    @ApiOperation(value = "新增教练注册表信息", notes = "所需参数[userid,username,gender,phone_number,email,password,materials,recommend,status,sc_full_cname,sc_full_ename,sc_abbreviation_cname,address,sc_abbreviation_ename,post_code]")
    @ApiOperationSupport(order = 2,author = "zs")
    @PostMapping("addCoachRegister")
    public ResultEntity addCoachRegister(@ApiParam(name = "userid", value = "身份证号") @RequestParam("userid") String userId,
                                         @ApiParam(name = "username", value = "名字") @RequestParam("username") String userName,
                                         @ApiParam(name = "gender", value = "性别") @RequestParam("gender") String gender,
                                         @ApiParam(name = "phone-number", value = "手机号") @RequestParam("phone-number") String phoneNumber,
                                         @ApiParam(name = "email", value = "邮箱") @RequestParam("email") String email,
                                         @ApiParam(name = "password", value = "密码") @RequestParam("password") String passWord,
                                         @ApiParam(name = "recommend", value = "审核建议") @RequestParam("recommend") String recommend,
                                         @ApiParam(name = "sc-full-cname", value = "学校中文全称") @RequestParam("sc-full-cname") String ScFullCname
                                         ){
        String[] params = new String[]{userId, userName, gender, phoneNumber, email,passWord,recommend,ScFullCname};
        return BasicResponseUtils.success();
    }



}
