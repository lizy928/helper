package com.lizy.helper.modules.app.service.impl;

import com.lizy.helper.modules.app.model.NetEaseCommentModel;
import com.lizy.helper.modules.app.service.IIndexService;
import com.lizy.helper.modules.common.cache.IndexCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author lizy
 * @date 2021/8/30 13:48
 */
@Service
public class IndexServiceImpl implements IIndexService {

    @Value("${netease.commentUrl}")
    private String commentUrl;
    @Value("${netease.num}")
    private Integer num;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addCommentCache() {
        for (int i = 0; i < num; i++) {
            final NetEaseCommentModel netEaseCommentModel = restTemplate.getForObject(commentUrl, NetEaseCommentModel.class);
            IndexCache.addCommentCache(netEaseCommentModel);
        }
    }

    @Override
    public List<NetEaseCommentModel> listNetEaseComment() {
        return IndexCache.getCommentCache();
    }
}
