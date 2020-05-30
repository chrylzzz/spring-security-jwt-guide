package github.javaguide.springsecurityjwtguide.system.vo;

import lombok.Data;

/**
 * 测试注册接收Json
 * Created by Chr.yl on 2020/5/30.
 *
 * @author Chr.yl
 */
@Data
public class UserVo {
    private String username;
    private String password;
    private String rememberMe;

}
