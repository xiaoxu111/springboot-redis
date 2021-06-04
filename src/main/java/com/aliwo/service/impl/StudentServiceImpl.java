package com.aliwo.service.impl;

import com.aliwo.dao.StudentDao;
import com.aliwo.entity.Student;
import com.aliwo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xuyayuan
 * @data 2021年06月04日
 * Redis在高并发请款下可能会存在那些问题？
 * 一：缓存穿透，当从数据库中查询结果为null时有可能会引发缓存穿透的问题
 * 解决方案是为这些为null的结果赋予一个默认值
 * 二：缓存雪崩，当缓存中的某些缓存在同一很短的时段内几乎同时过期，此时就会引发缓存雪崩
 * 解决方案是提前规划好系统中所有缓存的到期时间
 * 三：热点缓存(缓存击穿)，当一个缓存的有效期过期时可能引发热点缓存问题
 * 解决方案是双重检测锁机制
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * @param student
     * @return java.lang.Integer
     * @author xuyy
     * @date 2021/6/4 22:37
     * @CacheEvict(value = "realTimeCache", allEntries = true) 解释
     * @CacheEvict是用来标注在需要清除缓存元素的方法或类上的。当标记在一个类上时表示其中所有的方法的执行都会触发缓存的清除操作。
     * @CacheEvict可以指定的属性有value、key、condition、allEntries和beforeInvocation。其中value、key和condition的语义 与@Cacheable
     * 对应的属性类似。即value表示清除操作是发生在哪些Cache上的（对应Cache的名称）；
     * key表示需要清除的是哪个key，如未指定则会使用默认策略生成的key；condition表示清除操作发生的条件
     * allEntries是boolean类型，表示是否需要清除缓存中的所有元素。默认为false，表示不需要。
     * 当指定了allEntries为true时，Spring Cache将忽略指定的key。有的时候我们需要Cache一下清除所有的元素
     */
    @CacheEvict(value = "realTimeCache", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insertStudent(Student student) {
        return studentDao.insertStudent(student);
    }

    /**
     * @param age
     * @return java.util.List<com.aliwo.entity.Student>
     * @author xuyy
     * @date 2021/6/4 22:15
     * @Cacheable(value = "realTimeCache", key = "'student_'+ #age") 解释
     * @Cacheable 注解在方法上，表示该方法的返回结果是可以缓存的。也就是说，该方法的返回结果会放在缓存中，
     * 以便于以后使用相同的参数调用该方法时，会返回缓存中的值，而不会实际执行该方法
     * findStudentsBelowAge 方法与一个名为 realTimeCache 的缓存关联起来了。调用该方法时，会检查 realTimeCache 缓存，如果缓存中有结果，就不会去执行方法了
     * 一个缓存名对应一个被注解的方法，但是一个方法可能传入不同的参数，那么结果也就会不同，这应该如何区分呢？这就需要用到 key 。
     * 在 spring 中，key 的生成有两种方式：显式指定和使用 keyGenerator 自动生成
     */
    @Transactional
    @Cacheable(value = "realTimeCache", key = "'student_'+ #age")
    @Override
    public List<Student> findStudentsBelowAge(Integer age) {
        return studentDao.findStudentsBelowAge(age);
    }

    /**
     * @return java.lang.Integer
     * @author xuyy
     * @date 2021/6/4 20:43
     * 这里要使用双重检测锁机制解决当前代码中可能引发热点缓存问题(设置了过期时间可能引发热点缓存的问题)
     */
    @Transactional
    @Override
    public Integer countStudent() {
        // 获取redis操作对象
        BoundValueOperations<Object, Object> ops = redisTemplate.boundValueOps("count");
        // 从redis中获取指定key的value
        Object count = ops.get();
        // 双重检测
        if (null == count) {
            synchronized (this) {
                count = ops.get();
                if (null == count) {
                    // 从数据库中查询
                    count = studentDao.countStudent();
                    // 将查询结果存放到redis中，并指定过期时间
                    ops.set(count, 10, TimeUnit.SECONDS);
                }
            }
        }
        return (Integer) count;
    }
}
