package com.lizy.helper.modules.common.cache;

import com.lizy.helper.modules.app.model.NetEaseCommentModel;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * 首页数据缓存
 *
 * @author lizy
 * @date 2021/8/30 14:01
 */
@Component
public class IndexCache {

    public static LinkedList<NetEaseCommentModel> commentCache = new LinkedList<>();

    public static List<NetEaseCommentModel> getCommentCache(){
        return commentCache;
    }

    public static void addCommentCache(NetEaseCommentModel comment) {
        commentCache.add(comment);
    }

    public static void clearCache(){
        commentCache.clear();
    }
}