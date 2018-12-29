package essw.com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("essw.com.scheduler.mapper")
public class LogcollectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogcollectApplication.class, args);
    }
}

