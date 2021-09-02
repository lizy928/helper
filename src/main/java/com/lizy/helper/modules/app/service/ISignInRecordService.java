package com.lizy.helper.modules.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.lizy.helper.modules.app.entity.SignInRecord;
import com.lizy.helper.modules.app.model.SignInDetailModel;
import com.lizy.helper.modules.app.model.SignInRecordModel;

import java.util.Date;
import java.util.List;

/**
 * @author dliony
 */
public interface ISignInRecordService extends IService<SignInRecord> {

    /**
     * 签到
     *
     * @param signInRecordModel 签到对象
     */
    void signIn(SignInRecordModel signInRecordModel);

    /**
     * 查询时间范围内的签到
     *
     * @param startTime
     * @param endTime
     * @return
     */
    List<SignInRecord> listByDate(Long signInId, Date startTime, Date endTime);

    /**
     * 查询打卡记录详情
     *
     * @param signId 项目id
     * @param split 时间
     * @return
     */
    SignInDetailModel listDetail(Long signId, String[] split);
}
