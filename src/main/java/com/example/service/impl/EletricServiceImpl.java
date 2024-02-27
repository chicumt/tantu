package com.example.service.impl;

import com.example.dao.PriceDao;
import com.example.dao.StationDao;
import com.example.entity.Eletric;
import com.example.dao.EletricDao;
import com.example.entity.Price;
import com.example.entity.VO.DataChart;
import com.example.entity.VO.EletricVO;
import com.example.service.EletricService;
import com.example.utils.BaseContext;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * (Eletric)表服务实现类
 *
 * @author makejava
 * @since 2024-02-26 13:18:54
 */
@Service("eletricService")
public class EletricServiceImpl implements EletricService {
    @Autowired
    private EletricDao eletricDao;
    @Autowired
    private StationDao stationDao;
    @Autowired
    private PriceDao priceDao;

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
    public boolean deleteById(Integer id) {
        return this.eletricDao.deleteById(id) > 0;
    }



    @Override
    public EletricVO getToday() {
        String id= BaseContext.getCurrentId();
        List<Long> ids=stationDao.selectStationIdbyId(id);

        EletricVO eletricVO= eletricDao.getToday(ids);
        if(eletricVO==null){
            EletricVO eletricVO1=new EletricVO();
            eletricVO1.setProfit(0.0);
            eletricVO1.setTotal(0.0);
            List<DataChart> dataCharts=new ArrayList<>();
            eletricVO1.setDataCharts(dataCharts);
            return eletricVO1;
        }else{
            eletricVO.setDataCharts(eletricDao.getTodayChart());
        }


        return eletricVO;
    }

    @Override
    public EletricVO getTotal() {
        String id= BaseContext.getCurrentId();
        List<Long> ids=stationDao.selectStationIdbyId(id);
        EletricVO eletricVO=eletricDao.getTotal(ids);

        if(eletricVO==null){
            EletricVO eletricVO1=new EletricVO();
            eletricVO1.setProfit(0.0);
            eletricVO1.setTotal(0.0);
            List<DataChart> dataCharts=new ArrayList<>();
            eletricVO1.setDataCharts(dataCharts);
            return eletricVO1;
        }else{
            eletricVO.setDataCharts(eletricDao.getRecentChart());
        }


        return eletricVO;
    }
}
