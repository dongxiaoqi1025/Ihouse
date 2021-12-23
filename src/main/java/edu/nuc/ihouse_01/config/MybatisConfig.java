package edu.nuc.ihouse_01.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {
    //在开发环境中 使用的 可以检测SQL执行效率的插件（生产环境可关闭）
    @Bean
    public PerformanceMonitorInterceptor performanceMonitorInterceptor(){
        return  new PerformanceMonitorInterceptor();
    }
    //配置分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return  new PaginationInterceptor();
    }

}
