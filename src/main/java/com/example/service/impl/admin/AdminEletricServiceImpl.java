package com.example.service.impl.admin;

import com.example.entity.admin.Eletric;
import com.example.dao.admin.AdminEletricDao;
import com.example.service.admin.AdminEletricService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Eletric)表服务实现类
 *
 * @author makejava
 * @since 2024-02-27 19:39:44
 */
@Service("AdmineletricService")
public class AdminEletricServiceImpl implements AdminEletricService {
    @Autowired
    private AdminEletricDao eletricDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Eletric queryById(Integer id) {
        return this.eletricDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param eletric 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Eletric> queryByPage(Eletric eletric,Integer page, Integer size) {
        long total = this.eletricDao.count(eletric);
        Pageable pageable=PageRequest.of(page,size);
        return new PageImpl<>(this.eletricDao.queryAllByLimit(eletric, page*size,size), pageable, total);
    }

    /**
     * 新增数据
     *
     * @param eletric 实例对象
     * @return 实例对象
     */
    @Override
    public Integer insert(Eletric eletric) {

        return this.eletricDao.insert(eletric);
    }

    /**
     * 修改数据
     *
     * @param eletric 实例对象
     * @return 实例对象
     */
    @Override
    public Integer update(Eletric eletric) {

        return this.eletricDao.update(eletric);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String ids) {
        String[] idList=ids.split(",");
        return this.eletricDao.deleteById(idList) > 0;
    }
}
