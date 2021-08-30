package com.lizy.helper.modules.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lizy.helper.modules.app.entity.Note;
import com.lizy.helper.modules.app.mapper.NoteMapper;
import com.lizy.helper.modules.app.service.INoteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lzy
 * @date 2021/8/28
 */
@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements INoteService {

    @Resource
    private NoteMapper noteMapper;

    @Override
    public Long add(Note note) {
        noteMapper.insert(note);
        return note.getId();
    }
}
