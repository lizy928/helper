package com.lizy.helper.modules.app.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lizy.helper.modules.app.entity.SignInRecord;
import com.lizy.helper.modules.app.mapper.SignInRecordMapper;
import com.lizy.helper.modules.app.model.SignInRecordModel;
import com.lizy.helper.modules.app.service.ISignInRecordService;
import com.lizy.helper.utils.DateTimeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author lzy
 * @date 2021/8/28
 */
@Service
public class SignInRecordServiceImpl extends ServiceImpl<SignInRecordMapper, SignInRecord> implements ISignInRecordService {

    @Resource
    private SignInRecordMapper signInRecordMapper;

    @Override
    public void signIn(SignInRecordModel signInRecordModel) {
        SignInRecord signInRecord = new SignInRecord();
        signInRecord.setSignInId(signInRecordModel.getSignInId());
        signInRecord.setRemark(signInRecordModel.getRemark());
        if (DateTimeUtils.isToday(signInRecordModel.getSignInTime())) {
            signInRecord.setType(1);
            signInRecord.setSignInTime(new Date());
        } else {
            //补签
            signInRecord.setType(0);
            signInRecord.setSignInTime(signInRecordModel.getSignInTime());
        }
        signInRecordMapper.insert(signInRecord);
    }

    @Override
    public List<SignInRecord> listByDate(Long signInId, Date startTime, Date endTime) {
        final List<SignInRecord> recordList = signInRecordMapper.selectList(new EntityWrapper<SignInRecord>()
                .eq("sign_in_id", signInId)
                .between("sign_in_time", startTime, endTime));
        return  recordList;
    }
}
