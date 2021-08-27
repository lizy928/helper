package com.lizy.helper.modules.admin.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lizy.helper.modules.admin.dto.input.LogQueryPara;
import com.lizy.helper.modules.admin.entity.SysLog;
import com.lizy.helper.modules.admin.mapper.LogMapper;
import com.lizy.helper.modules.admin.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lizy
 * @date 2021/8/27 10:44
 */
@Service
@Transactional
public class LogServiceImpl extends ServiceImpl<LogMapper, SysLog> implements ILogService {

    @Autowired
    LogMapper logMapper;

    @Override
    public void listPage(Page<SysLog> page, LogQueryPara para) {
        List<SysLog> result = logMapper.selectLogs(page, para);
        result.forEach( e->{
            if (e.getUserId()==0){
                e.setUsername("非法人员");
            }
        });
        page.setRecords(result);
    }

    @Override
    public List<SysLog> list(LogQueryPara para) {
        return logMapper.selectLogs(para);
    }

    @Override
    public Integer save(SysLog para) {
        if (para.getId()!=null) {
            logMapper.updateById(para);
        } else {
            logMapper.insert(para);
        }
        return para.getId();
    }

}
