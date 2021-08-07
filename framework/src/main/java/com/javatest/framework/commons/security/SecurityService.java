package com.javatest.framework.commons.security;

import com.javatest.framework.commons.domain.security.JWTUser;
import com.javatest.framework.commons.domain.security.JWTUserAuthentication;
import com.javatest.framework.commons.utils.ExceptionUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


public class SecurityService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TOPO_USER = "topo-service";

    private String secret;

    private String serviceName;

    private static final String AUTH_HEADER = "authorization";

    public SecurityService(@NonNull final String secret, @NonNull final String serviceName) {
        this.secret = secret;
        this.serviceName = serviceName;
    }

    /**
     * 将Jwt Token 转换成 JWTUser
     * */
    public JWTUser parse(String token) {
        try {
            logger.debug("secret: {}, service name: {}", secret, serviceName);
            Claims claims = Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            JWTUser jwtUser = new JWTUser();
            jwtUser.setMethod(claims.get("method", List.class));
            jwtUser.setPrefix(claims.get("prefix", List.class));
            jwtUser.setRoles(claims.get("roles", List.class));
            jwtUser.setService(claims.get("service", String.class));
            jwtUser.setUser(claims.get("user", String.class));
            return jwtUser;
        } catch (Exception e ) {
            logger.debug(ExceptionUtil.strExceptionStack(e));
            logger.error("Token : " + token + " is not valid");
        }
        return null;


    }

    /**
     * 过滤器校验
     * */
    public Optional<Authentication> verifyToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(AUTH_HEADER);
        if (StringUtils.isNotBlank(token)) {
            if (token.startsWith("Bearer ")) {
                token = token.substring("Bearer ".length());
            }
            JWTUser jwtUser = parse(token.trim());
            if (isFromSdn(jwtUser) || checkService(jwtUser)) {
                return Optional.of(new JWTUserAuthentication(jwtUser, true));
            }
        }

        return Optional.empty();
    }

    /**
     * 检查service
     * */
    private boolean checkService(JWTUser jwtUser) {
        if (jwtUser != null) {
            String tokenService = jwtUser.getService();
            if (tokenService != null
                    && (tokenService.toLowerCase().equals(serviceName.toLowerCase())
                        || tokenService.toLowerCase().equals(TOPO_USER))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断请求是否来自内部
     * */
    private boolean isFromSdn(JWTUser jwtUser) {
        if (jwtUser != null) {
            String user = jwtUser.getUser();
            if (StringUtils.isNotBlank(user) && user.toLowerCase().equals("sdn")) {
                return true;
            }
        }
        return false;
    }


}

