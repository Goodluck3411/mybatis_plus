package com.sun.mybatis_plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.mybatis_plus.pojo.User;
import org.springframework.stereotype.Repository;
//在对应的mapper上面继承基本的类Basemapper
@Repository//代表持久层
public interface UserMapper extends BaseMapper <User>{
    //所有的CRUD已经完成了
    //这里我们不需要像以前一样配置xml等等等等。

}
