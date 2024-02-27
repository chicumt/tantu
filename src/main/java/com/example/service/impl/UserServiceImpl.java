package com.example.service.impl;

import com.example.entity.User;
import com.example.dao.UserDao;
import com.example.service.UserService;
import com.example.utils.BaseContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2024-02-26 12:40:35
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Long id) {
        return this.userDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<User> queryByPage(User user,Integer page, Integer size) {
        long total = this.userDao.count(user);
        Pageable pageable=PageRequest.of(page,size);
        return new PageImpl<>(this.userDao.queryAllByLimit(user, page*size,size), pageable, total);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public Integer insert(User user) {

        return this.userDao.insert(user);
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public Integer update(User user) {

        return this.userDao.update(user);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userDao.deleteById(id) > 0;
    }

    @Override
    public String updatePsw(String oldpsw, String newpsw) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();




        if(bCryptPasswordEncoder.matches(oldpsw,userDao.selectPsw(BaseContext.getCurrentId()))){
            newpsw=bCryptPasswordEncoder.encode(newpsw);
            userDao.updatePsw(newpsw, BaseContext.getCurrentId());
            return "修改成功";
        }

        return "原密码错误";
    }
}
