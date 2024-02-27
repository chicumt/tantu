package com.example.service;

import com.example.entity.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Station)表服务接口
 *
 * @author makejava
 * @since 2024-02-26 13:09:06
 */
public interface StationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Station queryById(Long id);

    /**
     * 分页查询
     *
     * @param station 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Station> queryByPage(Station station,Integer page, Integer size);

    /**
     * 新增数据
     *
     * @param station 实例对象
     * @return 实例对象
     */
    Integer insert(Station station);

    /**
     * 修改数据
     *
     * @param station 实例对象
     * @return 实例对象
     */
    Integer update(Station station);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
