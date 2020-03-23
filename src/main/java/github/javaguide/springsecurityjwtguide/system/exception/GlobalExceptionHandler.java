package github.javaguide.springsecurityjwtguide.system.exception;

import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Chr.yl
 */
@ControllerAdvice//全局异常处理
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserNameAlreadyExistException.class)//定义捕获范围
    @ResponseStatus(HttpStatus.BAD_REQUEST)//作用就是改变服务器响应的状态码 ,比如一个本是200的请求可以通过@ResponseStatus 改成404/500等等.
    public ErrorMessage handleUserNameAlreadyExistException(UserNameAlreadyExistException e) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = SignatureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleSignatureException(SignatureException e) {
        return new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }
}
