package github.javaguide.springsecurityjwtguide.security.utils;

import github.javaguide.springsecurityjwtguide.security.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chr.yl
 */
public class JwtTokenUtils {


    /**
     * 生成足够的安全随机密钥，以适合符合规范的签名
     */
    private static byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SecurityConstants.JWT_SECRET_KEY);
    private static SecretKey secretKey = Keys.hmacShaKeyFor(apiKeySecretBytes);//该秘钥为随机秘钥

    public static String createToken(String username, List<String> roles, boolean isRememberMe) {
        //记住为7天,否则为1小时
        long expiration = isRememberMe ? SecurityConstants.EXPIRATION_REMEMBER : SecurityConstants.EXPIRATION;

        String tokenPrefix = Jwts.builder()
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .signWith(secretKey, SignatureAlgorithm.HS256)//秘钥,编码方式
                .claim(SecurityConstants.ROLE_CLAIMS, String.join(",", roles))
                .setIssuer("SnailClimb")
                .setIssuedAt(new Date())
                .setSubject(username)//subject存储username
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
        return SecurityConstants.TOKEN_PREFIX + tokenPrefix;
    }

    /**
     * token是否过期
     *
     * @param token
     * @return
     */
    private static boolean isTokenExpired(String token) {
        Date expiredDate = getTokenBody(token).getExpiration();//过期时间
        return expiredDate.before(new Date());//过期时间在现在之前吗,true则已经过期
    }

    /**
     * 根据jwtToken得到subject
     *
     * @param token
     * @return
     */
    public static String getUsernameByToken(String token) {
        //add by Chr.yl
        boolean isExp = isTokenExpired(token);
        if (isExp) {
            return null;
        }
        //add end
        return getTokenBody(token).getSubject();
    }

    /**
     * 获取用户所有角色
     */
    public static List<SimpleGrantedAuthority> getUserRolesByToken(String token) {
        String role = (String) getTokenBody(token)
                .get(SecurityConstants.ROLE_CLAIMS);
        return Arrays.stream(role.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    //解密,根据票据解密
    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)//秘钥
                .parseClaimsJws(token)//解析
                .getBody();
    }
}
