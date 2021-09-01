package com.lizy.helper.modules.app.api;

import com.lizy.helper.modules.common.dto.output.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
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
        String filePathString = filePath + File.separator + fileName;
        File file = new File(filePathString);
        //String fileName = new String(fileNameString.getBytes("ISO8859-1"), "UTF-8");
        response.setContentType("application/octet-stream");
        // URLEncoder.encode(fileNameString, "UTF-8") 下载文件名为中文的，文件名需要经过url编码
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
        FileInputStream fileIn = null;
        ServletOutputStream out = null;
        try {
            fileIn = new FileInputStream(file);
            out = response.getOutputStream();
            byte[] outputByte = new byte[1024];
            int readTmp = 0;
            while ((readTmp = fileIn.read(outputByte)) != -1) {
                //并不是每次都能读到1024个字节，所有用readTmp作为每次读取数据的长度，否则会出现文件损坏的错误
                out.write(outputByte, 0, readTmp);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            assert fileIn != null;
            fileIn.close();
            assert out != null;
            out.flush();
            out.close();
        }
    }

}
