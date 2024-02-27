package com.example.service.impl;

import com.example.entity.Price;
import com.example.dao.PriceDao;
import com.example.service.PriceService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Price)表服务实现类
 *
 * @author makejava
 * @since 2024-02-26 14:00:25
 */
@Service("priceService")
public class PriceServiceImpl implements PriceService {
    @Autowired
    private PriceDao priceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Price queryById(Integer id) {
        return this.priceDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param price 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Price> queryByPage(Price price,Integer page, Integer size) {
        long total = this.priceDao.count(price);
        Pageable pageable=PageRequest.of(page,size);
        return new PageImpl<>(this.priceDao.queryAllByLimit(price, page*size,size), pageable, total);
    }

    /**
     * 新增数据
     *
     * @param price 实例对象
     * @return 实例对象
     */
    @Override
    public Integer insert(Price price) {

        return this.priceDao.insert(price);
    }

    /**
     * 修改数据
     *
     * @param price 实例对象
     * @return 实例对象
     */
    @Override
    public Integer update(Price price) {

        return this.priceDao.update(price);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.priceDao.deleteById(id) > 0;
    }
}
