# ****

# ***mybatis_plus快速上手***

****

## **快速入门**

地址：https://baomidou.com/guide/quick-start.html#%E5%88%9D%E5%A7%8B%E5%8C%96%E5%B7%A5%E7%A8%8B

使用第三方组件：

1.导入对应的依赖

2.研究依赖如何配置

3.代码如何编写

4.提高扩展技术能力！

```github
步骤：
```

```sql
1.创建数据库：
建表语句如下：
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
);
DELETE FROM user;

INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');
```

```yml
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.url=jdbc:mysql://localhost:3306/mybatis_plus?useSSL=false&useUnicode=ture&characterEncoding=utf-8&serverTimezone=GMT
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

说明：这里到最后出现一个bug（因为数据库时区原因所以导致bug），https://blog.csdn.net/wxb141001yxx/article/details/104959538（解决方法参考这个

在url中添加serverTimezone=GMT），具体错误：

报错信息：The server time zone value ‘�й���׼ʱ��’ is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the serverTimezone configuration property) to use a more specifc time zone value if you want to utilize time zone support.（服务器时区值’。©���׼ʱ��’无法识别或代表多个时区。如果要利用时区支持，则必须配置服务器或JDBC驱动程序（通过serverTimezone配置属性）以使用更特定的时区值），根据这个报错信息我们基本上已经知道问题的解决方案是什么了，我们刻意通过serverTimezone配置属性来解决这个问题。					 

```md
创建一个springboot项目（下图为项目工程路径）：
```

![image-20210203200935917](C:\Users\dell\AppData\Roaming\Typora\typora-user-images\image-20210203200935917.png)

```java
1.导入依赖：
     <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.48</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <!--mybatis_plus-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.0.5</version>
        </dependency>
    
2.编写实体类User
package com.sun.mybatis_plus.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String name;
    private Integer age;
    private String email;
}



3.mapper层：
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

Application：
package com.sun.mybatis_plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//扫描mapper文件夹
@MapperScan("com.sun.mybatis_plus.mapper")
@SpringBootApplication
public class MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }

}
```

```java
测试：
	测试文件夹中打开MybatisPlusApplicationTests：
import com.sun.mybatis_plus.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class MybatisPlusApplicationTests {
	//继承了BaseMapper，所有的方法都来自父类
    //我们可以自己编写自己的扩展方法！！！
    @Autowired
    private UserMapper userMapper;


    @Test
    void contextLoads() {
        //参数是一个Wrapper，条件构造器，这里我们先不用所以是null；
        //返回实体类的返回值，即users，快捷键C+A+v
        //查询所有用户
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

}
```

#### 注意：我们需要在主启动类上去扫描我们mapper包下的所有接口@MapperScan("com.sun.mybatis_plus.mapper")

测试类中测试

```md
上传远程仓库（GitHub）

1.首先右键单击项目->>找到git->>add->>commid
2.在GitHub上创建远程库->>获取地址：https://github.com/Goodluck3411/mybatis_plus.git（本项目采用https）。
3.返回idea->>git（上方）->>Git Remotes添加origin
4.之后pull项目即完成
```

```md
运行结果：
```



![image-20210203203129928](C:\Users\dell\AppData\Roaming\Typora\typora-user-images\image-20210203203129928.png)					

​	太困了下班了下一场直播再见。				

​					

​					

​					

​					

​					

​					

​					

​					

​					