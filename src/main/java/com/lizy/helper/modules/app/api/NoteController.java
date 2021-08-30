package com.lizy.helper.modules.app.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lizy.helper.modules.admin.entity.User;
import com.lizy.helper.modules.app.entity.Note;
import com.lizy.helper.modules.app.service.INoteService;
import com.lizy.helper.modules.common.annotation.LoginUser;
import com.lizy.helper.modules.common.dto.output.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzy
 * @date 2021/8/28
 */
@RequestMapping("/app/note")
@RestController
public class NoteController {

    @Resource
    private INoteService noteService;

    /**
     * 新增笔记
     *
     * @param user 当前登录对象
     * @param note 笔记
     * @return
     */
    @PostMapping
    public Object add(@LoginUser User user, @RequestBody Note note) {
        note.setUserId(Long.valueOf(user.getId()));
        final Long id = noteService.add(note);
        final Note noteNew = noteService.selectById(id);
        return ApiResult.ok("ok", noteNew);
    }

    /**
     * 列表
     *
     * @param user 当前登录对象
     * @return
     */
    @GetMapping
    public Object list(@LoginUser User user) {
        final List<Note> noteList = noteService.selectList(new EntityWrapper<Note>()
                .eq("user_id", user.getId())
                .orderBy("update_time", false)
                .orderBy("id", false)
        );
        return ApiResult.ok("ok", noteList);
    }

    /**
     * 更新
     *
     * @param user 当前登录对象
     * @return
     */
    @PutMapping("/{id}")
    public Object update(@LoginUser User user, @PathVariable Long id, @RequestBody Note note) {
        note.setId(id);
        noteService.updateById(note);
        final Note noteNew = noteService.selectById(id);
        return ApiResult.ok("ok", noteNew);
    }

    /**
     * 删除
     *
     * @param user 当前登录对象
     * @return
     */
    @DeleteMapping("/{id}")
    public Object delete(@LoginUser User user, @PathVariable Long id) {
        noteService.deleteById(id);
        return ApiResult.ok("ok");
    }
}
