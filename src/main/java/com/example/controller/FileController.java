package com.example.controller;

import com.example.entity.File;
import com.example.service.FileService;
import com.example.utils.ResponseResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * (File)表控制层
 *
 * @author makejava
 * @since 2024-01-21 17:58:29
 */
@RestController
@RequestMapping("file")
public class FileController {
    /**
     * 服务对象
     */
    @Autowired
    private FileService fileService;

    /**
     * 分页查询
     *
     * @param file 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping("/page")
    public ResponseEntity<Page<File>> queryByPage(File file, PageRequest pageRequest) {
        return ResponseEntity.ok(this.fileService.queryByPage(file, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping(value = "/{fileId}")
    public ResponseEntity<byte[]> queryById(@PathVariable("fileId") Long id, HttpServletRequest request) {

        File myFileObject = fileService.queryById(id);
        byte[] file = myFileObject.getFile();
        String fileType = myFileObject.getType();

        // 设置Content-Type
        MediaType mediaType = MediaType.parseMediaType(fileType);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setContentDisposition(ContentDisposition.builder("inline").filename(myFileObject.getFileId().toString()).build());

        String rangeHeader = request.getHeader("Range");
        if (rangeHeader != null) {
            // 解析范围请求
            Range range = parseRange(rangeHeader, file.length);

            // 设置Content-Range
            String contentRange = "bytes " + range.getStart() + "-" + range.getEnd() + "/" + file.length;
            headers.add("Content-Range", contentRange);
            headers.setContentLength(range.getLength());

            // 读取并返回文件的请求范围部分
            byte[] rangeBytes = Arrays.copyOfRange(file, (int) range.getStart(), (int) range.getEnd() + 1);
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                    .headers(headers)
                    .body(rangeBytes);
        } else {
            headers.setContentLength(file.length);
            return ResponseEntity.ok().headers(headers).body(file);
        }
    }
    private Range parseRange(String rangeHeader, long fileSize) {
        Pattern pattern = Pattern.compile("bytes=(\\d*)-(\\d*)");
        Matcher matcher = pattern.matcher(rangeHeader);
        if (matcher.matches()) {
            long start = matcher.group(1).isEmpty() ? 0 : Long.parseLong(matcher.group(1));
            long end = matcher.group(2).isEmpty() ? fileSize - 1 : Long.parseLong(matcher.group(2));
            return new Range(start, end);
        } else {
            throw new IllegalArgumentException("Range Header format is not valid.");
        }
    }

    private static class Range {
        private final long start;
        private final long end;

        public Range(long start, long end) {
            this.start = start;
            this.end = end;
        }

        public long getStart() {
            return start;
        }

        public long getEnd() {
            return end;
        }

        public long getLength() {
            return end - start + 1;
        }
    }
    /**
     * 新增数据
     *
     * @param file 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseResult<String> add(@RequestParam("file") MultipartFile multipartFile) throws IOException {


        Long t= fileService.insert(multipartFile);
        System.out.println(t);
        if(t>=1)
            return new ResponseResult(t);
        else return new ResponseResult(1,"插入失败");
    }

    /**
     * 编辑数据
     *
     * @param file 实体
     * @return 编辑结果
     */
    @PutMapping(value = "/{fileId}")
    public ResponseResult<String> edit(@PathVariable("fileId") Long id, @RequestParam("file") MultipartFile multipartFile) throws IOException {

        Integer t= fileService.update(id,multipartFile);
        if(t>=1)
        return new ResponseResult("修改成功");
        else return new ResponseResult(1,"修改失败");
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping(value = "{fileId}")
    public ResponseResult<String> deleteById(@PathVariable("fileId") Long id) {
        if(this.fileService.deleteById(id))
            return new ResponseResult("删除成功");
        else return new ResponseResult(1,"删除失败");

    }

}

