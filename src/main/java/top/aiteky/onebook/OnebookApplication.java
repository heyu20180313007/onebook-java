package top.aiteky.onebook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("top.aiteky.onebook.mapper")
@EnableAsync
@EnableScheduling
public class OnebookApplication {
	public static void main(String[] args) {
		SpringApplication.run(OnebookApplication.class, args);
	}
}
