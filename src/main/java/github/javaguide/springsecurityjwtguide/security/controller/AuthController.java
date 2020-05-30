package github.javaguide.springsecurityjwtguide.security.controller;

import com.alibaba.fastjson.JSON;
import github.javaguide.springsecurityjwtguide.system.service.UserService;
import github.javaguide.springsecurityjwtguide.system.vo.UserVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * 注册,在db生成User和用户
 *
 * @author Chr.yl
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    /**
     * {
     * "username": "123456",
     * "password": "123456",
     * "rememberMe": true
     * }
     */
    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody UserVo userVo) {
//    public ResponseEntity registerUser(@RequestBody Map<String, String> registerUser) {
//        测试 json
        //json.toJson属性有null时,会解析错误,注意这里
        Map<String, Object> registerUserMap = JSON.parseObject(JSON.toJSONString(userVo), Map.class);
        Set<Map.Entry<String, Object>> entrySet = registerUserMap.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
        Map<String, String> registerUser = null;
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            registerUser = new HashMap<>();

            registerUser.put(next.getKey(), next.getValue().toString());
        }

        userService.saveUser(registerUser);
        return ResponseEntity.ok().build();
    }

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
}
