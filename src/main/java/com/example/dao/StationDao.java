package com.example.dao;

import com.example.entity.Station;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Station)表数据库访问层
 *
 * @author makejava
 * @since 2024-02-26 13:09:06
 */
@Mapper
public interface StationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Station queryById(Long id);
    List<Station> selectBatch(List<Long> ids);

    /**
     * 查询指定行数据
     *
     * @param station 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Station> queryAllByLimit(Station station, @Param("page")Integer page, @Param("size")Integer size);

    /**
     * 统计总行数
     *
     * @param station 查询条件
     * @return 总行数
     */
    long count(Station station);

    /**
     * 新增数据
     *
     * @param station 实例对象
     * @return 影响行数
     */
    int insert(Station station);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Station> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Station> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Station> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Station> entities);

    /**
     * 修改数据
     *
     * @param station 实例对象
     * @return 影响行数
     */
    int update(Station station);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<Station> selectAllId();
    List<Long> selectStationIdbyId(String id);

    List<Station> queryAllByLimitJoin(Station station, int i, Integer size);
}

