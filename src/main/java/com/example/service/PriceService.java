package com.example.service;

import com.example.entity.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Price)表服务接口
 *
 * @author makejava
 * @since 2024-02-26 14:00:25
 */
public interface PriceService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Price queryById(Integer id);

    /**
     * 分页查询
     *
     * @param price 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Price> queryByPage(Price price,Integer page, Integer size);

    /**
     * 新增数据
     *
     * @param price 实例对象
     * @return 实例对象
     */
    Integer insert(Price price);

    /**
     * 修改数据
     *
     * @param price 实例对象
     * @return 实例对象
     */
    Integer update(Price price);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
