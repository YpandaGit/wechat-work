package indi.wechat.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients({ "indi.wechat.work.api" })
public class WechatWorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(WechatWorkApplication.class, args);
	}

}
