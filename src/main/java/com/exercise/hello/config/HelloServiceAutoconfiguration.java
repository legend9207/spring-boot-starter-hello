package com.exercise.hello.config;

import com.exercise.hello.HelloService;
import com.exercise.hello.properties.HelloServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置类，给bean注入参数
 *
 * @author
 *
 */
//标记当前类是配置类
@Configuration
/**
 * 使用java类作为配置文件。会把java配置类注册到spring容器中。
 * @see org.springframework.boot.context.properties.EnableConfigurationPropertiesImportSelector.ConfigurationPropertiesBeanRegistrar
 */
@EnableConfigurationProperties(HelloServiceProperties.class)
@ConditionalOnClass(HelloService.class) //需要被配置的类
@ConditionalOnProperty(prefix = "hello", value = "enable", matchIfMissing = true)
//扫描需要注册的Bean。
@ComponentScan(basePackages = {
        "com.exercise.hello"
})
public class HelloServiceAutoconfiguration {

    //自动注入配置类
    @Autowired
    private HelloServiceProperties helloServiceProperties;

    /**
     * 给bean注入参数，同时返回一个bean实例
     * 同时注解表名，返回是一个bean实例
     * 当容器中没有这个bean实例的时候，就返回一个自动注入好参数的bean实例回去
     * @return HelloService
     */
    @Bean
    @ConditionalOnMissingBean(HelloService.class)
    public HelloService helloService() {
        HelloService helloService = new HelloService();
        helloService.setMsg(helloServiceProperties.getMsg());
        return helloService;
    }

}
