package competition_2020;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Nick
 * @Date 2020/1/13--19:24
 * @Description
 */

@SpringBootApplication
@MapperScan( value = {"competition_2020.mapper"} )
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
