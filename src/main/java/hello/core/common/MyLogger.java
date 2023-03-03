package hello.core.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void log(String msg) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + msg);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + "request scope bean create: " + this);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + "request scope bean destroy: " + this);
    }
}
