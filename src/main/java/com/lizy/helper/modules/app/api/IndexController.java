package com.lizy.helper.modules.app.api;

import com.lizy.helper.modules.app.service.IIndexService;
import com.lizy.helper.modules.common.dto.output.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lizy
 * @date 2021/8/30 14:13
 */
@RequestMapping("/app/index")
@RestController
public class IndexController {

    @Autowired
    private IIndexService indexService;

    /**
     * 网易云热评接口
     *
     * @return
     */
    @GetMapping("/comment")
    public Object listComment(){
        return ApiResult.ok("ok", indexService.listNetEaseComment());
    }

}