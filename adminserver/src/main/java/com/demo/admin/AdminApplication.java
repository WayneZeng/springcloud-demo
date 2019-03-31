package com.demo.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class AdminApplication {

  public static void main(String[] args) {
    SpringApplication.run(AdminApplication.class, args);
  }

  @Bean({"threadPoolTaskExecutor", "webMvcAsyncTaskExecutor"})
  public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
    return new ThreadPoolTaskExecutor();
  }
}
