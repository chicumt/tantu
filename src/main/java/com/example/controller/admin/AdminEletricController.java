package com.example.controller.admin;

import com.example.entity.admin.Eletric;
import com.example.service.admin.AdminEletricService;
import com.example.utils.ResponseResult;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * (Eletric)表控制层
 *
 * @author makejava
 * @since 2024-02-27 19:39:40
 */
@RestController
@RequestMapping("/admin/eletric")
public class AdminEletricController {
    /**
     * 服务对象
     */
    @Autowired
    private AdminEletricService adminEletricService;

    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @PostMapping("/page")
    public ResponseResult<Page<Eletric>> queryByPage(@RequestBody Eletric eletric, @RequestParam(defaultValue = "0") Integer page,
                                                         @RequestParam(defaultValue = "10") Integer size) {
        return new ResponseResult(this.adminEletricService.queryByPage(eletric,page, size));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResponseResult<Eletric> queryById(@PathVariable Integer id) {
        return new ResponseResult(this.adminEletricService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param eletric 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseResult add(@RequestBody Eletric eletric) {


        Integer t=this.adminEletricService.insert(eletric);
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
        Integer t=this.adminEletricService.update(eletric);
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
    @DeleteMapping("/{ids}")
    public ResponseResult deleteById(@PathVariable String ids) {

        if(this.adminEletricService.deleteById(ids))
        return new ResponseResult("删除成功");
        else return new ResponseResult("id不存在");
    }

}

