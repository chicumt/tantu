package com.example.service.admin;

import com.example.entity.admin.Eletric;
import org.springframework.data.domain.Page;

/**
 * (Eletric)表服务接口
 *
 * @author makejava
 * @since 2024-02-27 19:39:44
 */
public interface AdminEletricService {

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
    boolean deleteById(String ids);

}
