package com.example.service.impl;

import com.example.entity.Station;
import com.example.dao.StationDao;
import com.example.service.StationService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Station)表服务实现类
 *
 * @author makejava
 * @since 2024-02-26 13:09:06
 */
@Service("stationService")
public class StationServiceImpl implements StationService {
    @Autowired
    private StationDao stationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Station queryById(Long id) {
        return this.stationDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param station 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Station> queryByPage(Station station,Integer page, Integer size) {
        long total = this.stationDao.count(station);
        Pageable pageable=PageRequest.of(page,size);
        if(station.getApply()!=null){
            return new PageImpl<>(this.stationDao.queryAllByLimitJoin(station, page*size,size), pageable, total);
        }
        return new PageImpl<>(this.stationDao.queryAllByLimit(station, page*size,size), pageable, total);
    }

    /**
     * 新增数据
     *
     * @param station 实例对象
     * @return 实例对象
     */
    @Override
    public Integer insert(Station station) {

        return this.stationDao.insert(station);
    }

    /**
     * 修改数据
     *
     * @param station 实例对象
     * @return 实例对象
     */
    @Override
    public Integer update(Station station) {

        return this.stationDao.update(station);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.stationDao.deleteById(id) > 0;
    }
}
