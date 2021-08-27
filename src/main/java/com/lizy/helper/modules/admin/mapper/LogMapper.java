package com.lizy.helper.modules.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.lizy.helper.modules.admin.dto.input.LogQueryPara;
import com.lizy.helper.modules.admin.entity.SysLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lizy
 * @date 2021/8/27 10:44
 */
public interface LogMapper extends BaseMapper<SysLog> {

    /**
     * 列表分页
     *
     * @param page
     * @param filter
     * @return
     */
    List<SysLog> selectLogs(Pagination page, @Param("filter") LogQueryPara filter);

    /**
     * 列表
     *
     * @param filter
     * @return
     */
    List<SysLog> selectLogs(@Param("filter") LogQueryPara filter);

}
