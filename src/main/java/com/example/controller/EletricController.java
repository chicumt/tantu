package com.example.controller;

import com.example.entity.Eletric;
import com.example.service.EletricService;
import org.springframework.data.domain.Page;
import com.example.utils.ResponseResult;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * (Eletric)表控制层
 *
 * @author makejava
 * @since 2024-02-26 13:18:54
 */
@RestController
@RequestMapping("eletric")
public class EletricController {
    /**
     * 服务对象
     */
    @Autowired
    private EletricService eletricService;

    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @PostMapping("/page")
    public ResponseResult<Page<Eletric>> queryByPage(@RequestBody Eletric eletric, @RequestParam(defaultValue = "0") Integer page,
                                                         @RequestParam(defaultValue = "10") Integer size) {
        return new ResponseResult(this.eletricService.queryByPage(eletric,page, size));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResponseResult<Eletric> queryById(@PathVariable Integer id) {
        return new ResponseResult(this.eletricService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param eletric 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseResult add(@RequestBody Eletric eletric) {


        Integer t=this.eletricService.insert(eletric);
        if(t>=1)
        return new ResponseResult("新增成功");
        else return new ResponseResult("插入失败");
    }

    /**
     * 编辑数据
     *
     * @param eletric 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseResult edit(@RequestBody Eletric eletric) {
        Integer t=this.eletricService.update(eletric);
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
    public ResponseResult deleteById(@PathVariable Integer id) {

        if(this.eletricService.deleteById(id))
        return new ResponseResult("删除成功");
        else return new ResponseResult("id不存在");
    }
    @GetMapping("/today")
    public ResponseResult getToday(){
        return new ResponseResult<>(eletricService.getToday());
    }


    @GetMapping("/total")
    public ResponseResult getTotal(){
        return new ResponseResult<>(eletricService.getTotal());
    }

}

