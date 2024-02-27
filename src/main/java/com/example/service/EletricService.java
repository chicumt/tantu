package com.example.service;

import com.example.entity.Eletric;
import com.example.entity.VO.EletricVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Eletric)表服务接口
 *
 * @author makejava
 * @since 2024-02-26 13:18:54
 */
public interface EletricService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Eletric queryById(Integer id);

    /**
     * 分页查询
     *
     * @param eletric 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Eletric> queryByPage(Eletric eletric,Integer page, Integer size);

    /**
     * 新增数据
     *
     * @param eletric 实例对象
     * @return 实例对象
     */
    Integer insert(Eletric eletric);

    /**
     * 修改数据
     *
     * @param eletric 实例对象
     * @return 实例对象
     */
    Integer update(Eletric eletric);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);



    EletricVO getToday();

    EletricVO getTotal();
}
