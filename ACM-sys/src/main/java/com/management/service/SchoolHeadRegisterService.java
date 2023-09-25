package com.management.service;

import com.management.common.ResultEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface SchoolHeadRegisterService {

    /**
     * 新增联系人用户，需审核
     * @author zs
     * @param params
     * @return int
     */
    ResultEntity addSchoolHeadRegister(MultipartFile file, String[] params);

}
