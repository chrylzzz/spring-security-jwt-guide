package github.javaguide.springsecurityjwtguide;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * Created by Chr.yl on 2020/3/20.
 */
public class BCryptPasswordEncoderTest {
    public static void main(String[] args) {
        String pass = "admin";
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashPass = bcryptPasswordEncoder.encode(pass);
        System.out.println(hashPass);

        boolean f = bcryptPasswordEncoder.matches("admin", hashPass);
        System.out.println(f);


        Date date = new Date();
        boolean before = date.before(new Date());
        System.out.println(before);

    }
}
