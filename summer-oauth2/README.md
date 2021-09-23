# 客户端使用

**`依赖`**

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-oauth2</artifactId>
</dependency>
```

**`配置文件`**

```properties
server.port=8080
security.oauth2.client.client-id=webapp
security.oauth2.client.client-secret=secret
security.oauth2.client.access-token-uri=http://localhost:8110/oauth/token
security.oauth2.client.user-authorization-uri=http://localhost:8110/oauth/authorize
security.oauth2.resource.user-info-uri=http://localhost:8110/oauth/user
```

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class Oauth2WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**").authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .anyRequest().authenticated();
    }
}
```

**`鉴权示例`**

```java

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Oauth2ClientController {

    @GetMapping("/api/user")
    @PreAuthorize("hasAuthority('USER')")
    public Map<String, Object> apiUser() {
        return null;
    }
}
```

> `参考` https://kefeng.wang/2018/04/06/oauth2-sso/