package com.lizy.helper.modules.app.api;

import com.lizy.helper.modules.common.dto.output.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author lzy
 * @date 2021/8/31
 */
@RequestMapping("/app")
@RestController
@Slf4j
public class FileControler {

    @Value("${config.filePath}")
    private String filePath;

    /**
     * 文件上传
     *
     * @param file 文件
     * @return
     */
    @PostMapping("/file/upload")
    public Object fileUpLoad(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        String trueFileName = UUID.randomUUID().toString();
        String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length())
                : null;
        // 设置存放图片文件的路径
        String path = null == type ? filePath + File.separator + trueFileName
                : filePath + File.separator + trueFileName + "." + type;
        File file2 = new File(filePath);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        Map<String, Object> resultMap = new HashMap<>();
        // 转存文件到指定的路径
        try {
            file.transferTo(new File(path));
            resultMap.put("fileName", null == type ? trueFileName : trueFileName + "." + type);
            System.out.println(resultMap);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return ApiResult.ok("ok", resultMap);
    }

    /**
     * 文件下载
     *
     * @param fileName 文件名
     * @return
     * @throws IOException
     */
    @RequestMapping("/file/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        String path = filePath + File.separator + fileName;
        File newFile = new File(path);
        String name = newFile.getName();
        if (!newFile.exists()) {
            throw new IOException(name + "文件不存在");
        }
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + name);
        InputStream myStream = new FileInputStream(path);
        IOUtils.copy(myStream, response.getOutputStream());
        response.flushBuffer();
    }

}
