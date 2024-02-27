package com.example.dao.admin;

import com.example.entity.admin.Eletric;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Eletric)表数据库访问层
 *
 * @author makejava
 * @since 2024-02-27 19:39:42
 */
@Mapper
public interface AdminEletricDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Eletric queryById(Integer id);
    List<Eletric> selectBatch(List<Integer> ids);

    /**
     * 查询指定行数据
     *
     * @param eletric 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Eletric> queryAllByLimit(Eletric eletric, @Param("page")Integer page, @Param("size")Integer size);

    /**
     * 统计总行数
     *
     * @param eletric 查询条件
     * @return 总行数
     */
    long count(Eletric eletric);

    /**
     * 新增数据
     *
     * @param eletric 实例对象
     * @return 影响行数
     */
    int insert(Eletric eletric);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Eletric> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Eletric> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Eletric> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Eletric> entities);

    /**
     * 修改数据
     *
     * @param eletric 实例对象
     * @return 影响行数
     */
    int update(Eletric eletric);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String[] idList);

}

