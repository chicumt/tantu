package com.example.service;

import com.example.entity.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * (File)表服务接口
 *
 * @author makejava
 * @since 2024-01-21 17:58:29
 */
public interface FileService {

    /**
     * 通过ID查询单条数据
     *
     * @param fileId 主键
     * @return 实例对象
     */
    File queryById(Long fileId);

    /**
     * 分页查询
     *
     * @param file 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<File> queryByPage(File file, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param file 实例对象
     * @return 实例对象
     */
    Long insert(MultipartFile file) throws IOException;

    /**
     * 修改数据
     *
     * @param file 实例对象
     * @return 实例对象
     */
    Integer update(Long id,MultipartFile file) throws IOException;

    /**
     * 通过主键删除数据
     *
     * @param fileId 主键
     * @return 是否成功
     */
    boolean deleteById(Long fileId);

}
