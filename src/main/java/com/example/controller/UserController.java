package com.example.controller;

import com.example.entity.Dto.PasswordDTO;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.utils.BaseContext;
import org.springframework.data.domain.Page;
import com.example.utils.ResponseResult;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2024-02-26 12:40:35
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;

    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @PostMapping("/page")
    public ResponseResult<Page<User>> queryByPage(@RequestBody User user, @RequestParam(defaultValue = "0") Integer page,
                                                         @RequestParam(defaultValue = "10") Integer size) {
        return new ResponseResult(this.userService.queryByPage(user,page, size));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping()
    public ResponseResult<User> queryById() {
        Long id= Long.parseLong(BaseContext.getCurrentId());
        return new ResponseResult(this.userService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseResult add(@RequestBody User user) {


        Integer t=this.userService.insert(user);
        if(t>=1)
        return new ResponseResult("新增成功");
        else return new ResponseResult("插入失败");
    }

    /**
     * 编辑数据
     *
     * @param user 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseResult edit(@RequestBody User user) {
        user.setId(Long.parseLong(BaseContext.getCurrentId()));
        Integer t=this.userService.update(user);
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

        if(this.userService.deleteById(id))
        return new ResponseResult("删除成功");
        else return new ResponseResult("id不存在");
    }
    @PostMapping ("/password/update")
    public ResponseResult<String> updatePsw(@RequestBody PasswordDTO passwordDTO){
        return new ResponseResult<>(userService.updatePsw(passwordDTO.getOldpsw(), passwordDTO.getNewpsw()));
    }
}

