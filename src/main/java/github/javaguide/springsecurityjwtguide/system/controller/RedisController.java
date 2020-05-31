package github.javaguide.springsecurityjwtguide.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import github.javaguide.springsecurityjwtguide.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Chr.yl on 2018/3/31.
 *
 * @author Chr.yl
 */
@RestController
@RequestMapping("/redis")
public class RedisController {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @PostMapping("/set")
    public Object show(@RequestBody User user) {
        /**
         * redisTemplate
         */
        String redisKey = "123";
        String redisKey2 = "1233";
//        redisTemplate.opsForValue().set(redisKey, user, 600, TimeUnit.SECONDS);
//        User redisUser = (User) redisTemplate.opsForValue().get(redisKey);


        /**
         * stringRedisTemplate:直接使用json配合stringRedisTemplate
         */
        //存单个对象
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user);
        userList.add(user);
        userList.add(user);
        stringRedisTemplate.opsForValue().set(redisKey2, JSON.toJSONString(user), 600, TimeUnit.SECONDS);
        String userStr = stringRedisTemplate.opsForValue().get(redisKey2);
        User parse = JSONObject.parseObject(userStr, User.class);
        User parse1 = JSON.parseObject(userStr, User.class);
        System.out.println(parse);
        System.out.println(parse1);
        //存 list
//        stringRedisTemplate.opsForValue().set(redisKey2, JSON.toJSONString(userList), 600, TimeUnit.SECONDS);
//        String userStr = stringRedisTemplate.opsForValue().get(redisKey2);
//        List<User> parseArray = JSONArray.parseArray(userStr, User.class);
//        System.out.println(parseArray);

        return user;
    }

}
