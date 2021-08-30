package com.lizy.helper.modules.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.lizy.helper.modules.app.entity.Note;

/**
 * @author dliony
 */
public interface INoteService extends IService<Note> {

    /**
     * 新增
     *
     * @param note 笔记
     * @return
     */
    Long add(Note note);
}
