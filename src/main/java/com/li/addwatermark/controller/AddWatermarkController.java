package com.li.addwatermark.controller;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.Map;

/**
 * @author likexue
 * @create 2019/11/2314:39
 **/
@RestController
@Slf4j
public class AddWatermarkController extends BaseController {
    @Value("${file.upload}")
    private String uploadFilePath;
    String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        log.info("MultipartFile:{}", file);
        if (file.isEmpty()) {
            return error("上传文件为空");
        }
        InputStream is = null;
        try {
            is = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String savePath = null;
        String relativePath = null;
        String suffix = "jpg";
        String originFileName = file.getOriginalFilename();
        if (originFileName != null && !originFileName.equals("")) {
            //问题2
            String[] originFileNames = originFileName.split("\\.");
            log.info("originFileNamees:{}", originFileNames.toString());
            suffix = originFileNames[originFileNames.length - 1];
        }
        try {
            long time = System.currentTimeMillis();
            String fileName = time + "." + suffix;
            savePath = uploadFilePath + "/" + fileName;
            relativePath = "/images/" + fileName;
            Thumbnails.of(is)
                    .size(1000, 1200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(path + "/static/images/celitea.jpg")), 1f)
                    .toFile(new File(savePath));
        } catch (IOException e) {
            e.printStackTrace();
            return error("添加水印失败");
        }
        return success("成功添加水印", relativePath);
    }

    @GetMapping(value = "/")
    public String demo() {
        return "hello world!";
    }
}
