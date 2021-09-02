package com.lizy.helper.modules.app.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lizy.helper.modules.app.entity.SignInRecord;
import com.lizy.helper.modules.app.mapper.SignInRecordMapper;
import com.lizy.helper.modules.app.model.SignInDetailModel;
import com.lizy.helper.modules.app.model.SignInRecordModel;
import com.lizy.helper.modules.app.service.ISignInRecordService;
import com.lizy.helper.modules.common.dto.DateDto;
import com.lizy.helper.utils.DateTimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public SignInDetailModel listDetail(Long signId, String[] split) {
        final DateDto startAndEndTime = DateTimeUtils.getMonthStartAndEndTime(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
        List<SignInRecord> recordList = listByDate(signId, startAndEndTime.getStartTime(), startAndEndTime.getEndTime());
        List<SignInRecordModel> modelList = recordList.stream().map(signInRecord -> {
            SignInRecordModel signInRecordModel = new SignInRecordModel();
            BeanUtils.copyProperties(signInRecord, signInRecordModel);
            return signInRecordModel;
        }).collect(Collectors.toList());
        SignInDetailModel detailModel = new SignInDetailModel();
        detailModel.setId(signId);
        detailModel.setRecordList(modelList);
        detailModel.setMonthCount(modelList.size());

        int totalCount = selectCount(new EntityWrapper<SignInRecord>().eq("sign_in_id", signId));
        detailModel.setTotalCount(totalCount);

        boolean todaySignIn = selectCount(new EntityWrapper<SignInRecord>().eq("sign_in_id", signId)
                .eq("sign_in_time", DateTimeUtils.dateFormat(new Date(), DateTimeUtils.DATE_PATTERN))
        ) > 0;
        detailModel.setTodaySignIn(todaySignIn);
        return detailModel;
    }
}
