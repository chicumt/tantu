package com.example.dao;

import com.example.entity.Price;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Price)表数据库访问层
 *
 * @author makejava
 * @since 2024-02-26 14:00:25
 */
@Mapper
public interface PriceDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Price queryById(Integer id);
    List<Price> selectBatch(List<Integer> ids);

    /**
     * 查询指定行数据
     *
     * @param price 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Price> queryAllByLimit(Price price, @Param("page")Integer page, @Param("size")Integer size);

    /**
     * 统计总行数
     *
     * @param price 查询条件
     * @return 总行数
     */
    long count(Price price);

    /**
     * 新增数据
     *
     * @param price 实例对象
     * @return 影响行数
     */
    int insert(Price price);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Price> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Price> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Price> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Price> entities);

    /**
     * 修改数据
     *
     * @param price 实例对象
     * @return 影响行数
     */
    int update(Price price);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<Price> selectAll();

}

