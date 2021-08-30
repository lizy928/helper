package com.lizy.helper.modules.app.service;

import com.lizy.helper.modules.app.model.NetEaseCommentModel;

import java.util.List;

/**
 * @author lizy
 * @date 2021/8/30 13:47
 */
public interface IIndexService {

    /**
     * 添加网易云热评缓存
     *
     * @return
     */
    void addCommentCache();

    /**
     * 获取网易云热评接口
     *
     * @return
     */
    List<NetEaseCommentModel> listNetEaseComment();

}