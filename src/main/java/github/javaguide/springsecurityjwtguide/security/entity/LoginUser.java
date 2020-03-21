package github.javaguide.springsecurityjwtguide.security.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * @author shuang.kou
 */
@Data
public class LoginUser implements Serializable{

    private static final long serialVersionUID = 7722556388190642404L;
    private String username;
    private String password;
    private Boolean rememberMe;

}
