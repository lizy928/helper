package com.lizy.helper.task;

import com.lizy.helper.modules.app.service.IIndexService;
import com.lizy.helper.modules.common.cache.IndexCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * 数据缓存定时任务
 *
 * @author lizy
 * @date 2021/8/30 14:17
 */
@Component
public class DataCacheTask {

    @Autowired
    private IIndexService indexService;

    /**
     * 刷新缓存网易云热评
     * 每晚十二点 更新
     * 0 0 0 * * ?
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void cacheNetEaseComment(){
        IndexCache.clearCache();
        indexService.addCommentCache();
    }

}
