package com.yixin.pdf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ProjectName: yixin_parent
 * @Package: com.yixin.base
 * @ClassName: BaseApplication
 * @Description: java类作用描述
 * @Author: 式神
 * @CreateDate: 2019/3/7 11:07
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/7 11:07
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SpringBootApplication
@EnableSwagger2
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }


}
