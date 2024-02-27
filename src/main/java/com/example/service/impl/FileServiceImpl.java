package com.example.service.impl;

import com.example.entity.File;
import com.example.dao.FileDao;
import com.example.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * (File)表服务实现类
 *
 * @author makejava
 * @since 2024-01-21 17:58:29
 */
@Service("fileService")
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDao fileDao;

    /**
     * 通过ID查询单条数据
     *
     * @param fileId 主键
     * @return 实例对象
     */
    @Override
    public File queryById(Long fileId) {
        return this.fileDao.queryById(fileId);
    }

    /**
     * 分页查询
     *
     * @param file 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<File> queryByPage(File file, PageRequest pageRequest) {
        long total = this.fileDao.count(file);
        return new PageImpl<>(this.fileDao.queryAllByLimit(file, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param file 实例对象
     * @return 实例对象
     */
    @Override
    public Long insert(MultipartFile multipartFile) throws IOException {
//        this.fileDao.insert(file);
        String type=multipartFile.getContentType();
        File file=new File();
        file.setFile(multipartFile.getBytes());
        file.setType(type);
        file.setName(multipartFile.getOriginalFilename());
        this.fileDao.insert(file);
        return file.getFileId();
    }

    /**
     * 修改数据
     *
     * @param file 实例对象
     * @return 实例对象
     */
    @Override
    public Integer update(Long id,MultipartFile multipartFile) throws IOException {
        String type=multipartFile.getContentType();
        File file=new File();
        file.setFile(multipartFile.getBytes());
        file.setType(type);
        file.setName(multipartFile.getOriginalFilename());
        file.setFileId(id);
        return this.fileDao.update(file);
    }

    /**
     * 通过主键删除数据
     *
     * @param fileId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long fileId) {
        return this.fileDao.deleteById(fileId) > 0;
    }
}
