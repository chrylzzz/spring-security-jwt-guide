package github.javaguide.springsecurityjwtguide.system.vo;

import lombok.Data;

/**
 * 测试注册接收Json
 * Created by Chr.yl on 2019/5/10.
 *
 * @author Chr.yl
 */
@Data
public class UserVo {
    private String username;
    private String password;
    private String rememberMe;

}
