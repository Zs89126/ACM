package com.management.service.Impl;

import com.management.common.ResultEntity;
import com.management.entity.SchoolHeadRegister;
import com.management.enums.ResponseCodeEnums;
import com.management.mapper.SchoolHeadRegisterMapper;
import com.management.service.SchoolHeadRegisterService;
import com.management.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SchoolHeadRegisterServiceImpl implements SchoolHeadRegisterService {

    @Autowired
    SchoolHeadRegisterMapper schoolHeadRegisterMapper;

    @Override
    public ResultEntity addSchoolHeadRegister(MultipartFile file, String[] params) {
        ResultEntity resultEntity = new ResultEntity(200, "success", null);
        SchoolHeadRegister schoolHeadRegister = new SchoolHeadRegister();
        schoolHeadRegister.setUserId(params[0]);
        schoolHeadRegister.setUserName(params[1]);
        schoolHeadRegister.setGender(params[2]);
        schoolHeadRegister.setPhoneNumber(params[3]);
        schoolHeadRegister.setEmail(params[4]);
        schoolHeadRegister.setPassWord(params[5]);
        schoolHeadRegister.setRecommend(params[6]);
        schoolHeadRegister.setScFullCname(params[7]);
        schoolHeadRegister.setScFullEname(params[8]);
        schoolHeadRegister.setScAbbreCname(params[9]);
        schoolHeadRegister.setScAbbreEname(params[10]);
        schoolHeadRegister.setAddress(params[11]);
        schoolHeadRegister.setPostCode(params[12]);
        //文件上传逻辑实现
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        if(!"jpg".equals(suffix) && !"png".equals(suffix) && !"pdf".equals(suffix)){
            resultEntity.setMessage("WrongFileFormat");
            return resultEntity;
        }
        String materials = FileUtils.uploadImg(file, "materials");
        schoolHeadRegister.setMaterials(materials);
        schoolHeadRegister.setStatus("0");

        //逻辑删除和时间创建
        schoolHeadRegister.setIsDeleted("0");
        Date date = new Date();
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = Timestamp.valueOf(df1.format(date));
        schoolHeadRegister.setGmtCreate(timestamp);
        schoolHeadRegister.setGmtModified(timestamp);

        //判断用户是否已经存在
        SchoolHeadRegister scHeadReg = schoolHeadRegisterMapper.selectScHeadRegByScname(schoolHeadRegister.getScFullCname());
        if(scHeadReg != null){
            if(scHeadReg.getStatus().equals('0')){
                resultEntity.setCode(ResponseCodeEnums.RECORD_EXIST.getCode());
                resultEntity.setMessage(ResponseCodeEnums.RECORD_EXIST.getMsg()+",等待审核");
            }else if(scHeadReg.getStatus().equals('1')){
                resultEntity.setCode(ResponseCodeEnums.RECORD_EXIST.getCode());
                resultEntity.setMessage(ResponseCodeEnums.RECORD_EXIST.getMsg()+",不能重复登录");
            }
        }else{
            resultEntity.setData(schoolHeadRegisterMapper.insertSchoolHeadRegister(schoolHeadRegister));
        }
        return resultEntity;
    }
}
