package com.example.controller;

import com.example.entity.Station;
import com.example.service.StationService;
import com.example.utils.BaseContext;
import org.springframework.data.domain.Page;
import com.example.utils.ResponseResult;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * (Station)表控制层
 *
 * @author makejava
 * @since 2024-02-26 13:09:06
 */
@RestController
@RequestMapping("station")
public class StationController {
    /**
     * 服务对象
     */
    @Autowired
    private StationService stationService;

    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @PostMapping("/page")
    public ResponseResult<Page<Station>> queryByPage(@RequestParam(defaultValue = "0") Integer page,
                                                         @RequestParam(defaultValue = "20") Integer size) {
        Station station=new Station();
        station.setUserId(Long.parseLong(BaseContext.getCurrentId()));
        return new ResponseResult(this.stationService.queryByPage(station,page, size));
    }

    @PostMapping("/normal")
    public ResponseResult<Page<Station>> queryNormal(@RequestParam(defaultValue = "0") Integer page,
                                                     @RequestParam(defaultValue = "20") Integer size) {
        Station station=new Station();
        station.setUserId(Long.parseLong(BaseContext.getCurrentId()));
        station.setStatus(1);
        station.setApply(1);
        return new ResponseResult(this.stationService.queryByPage(station,page, size));
    }
    @PostMapping("/error")
    public ResponseResult<Page<Station>> queryError(@RequestParam(defaultValue = "0") Integer page,
                                                     @RequestParam(defaultValue = "20") Integer size) {
        Station station=new Station();
        station.setUserId(Long.parseLong(BaseContext.getCurrentId()));
        station.setStatus(0);
        station.setApply(1);
        return new ResponseResult(this.stationService.queryByPage(station,page, size));
    }
    @PostMapping("/all")
    public ResponseResult<Page<Station>> queryAll(@RequestParam(defaultValue = "0") Integer page,
                                                     @RequestParam(defaultValue = "40") Integer size) {
        Station station=new Station();
        station.setUserId(Long.parseLong(BaseContext.getCurrentId()));

        return new ResponseResult(this.stationService.queryByPage(station,page, size));
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResponseResult<Station> queryById(@PathVariable Long id) {
        return new ResponseResult(this.stationService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param station 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseResult add(@RequestBody Station station) {

        station.setUserId(Long.parseLong(BaseContext.getCurrentId()));
        Integer t=this.stationService.insert(station);
        if(t>=1)
        return new ResponseResult("新增成功");
        else return new ResponseResult("插入失败");
    }

    /**
     * 编辑数据
     *
     * @param station 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseResult edit(@RequestBody Station station) {
        Integer t=this.stationService.update(station);
        if(t>=1)
        return new ResponseResult("修改成功");
        else return new ResponseResult("修改失败");
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    public ResponseResult deleteById(@PathVariable Long id) {

        if(this.stationService.deleteById(id))
        return new ResponseResult("删除成功");
        else return new ResponseResult("id不存在");
    }

}

