package com.lizy.helper.modules.common.api;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lizy
 * @date 2021/8/27 10:35
 */
@RestController
@Slf4j
public class BaseController {

    public Logger LOG = LoggerFactory.getLogger( getClass() );

}
