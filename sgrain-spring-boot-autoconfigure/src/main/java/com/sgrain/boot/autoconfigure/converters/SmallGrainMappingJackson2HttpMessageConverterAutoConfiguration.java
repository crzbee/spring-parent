package com.sgrain.boot.autoconfigure.converters;

import com.sgrain.boot.common.utils.LoggerUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * @program: spring-parent
 * @description: springboot框架将字典数据类型转换为json，Content-Type默认由 content-type: application/json 更改为：content-type: application/json;charset=UTF-8
 * @create: 2020/10/28
 */
@Configuration
@AutoConfigureAfter(HttpMessageConvertersAutoConfiguration.class)
@EnableConfigurationProperties(Jackson2MessagesProperties.class)
@ConditionalOnProperty(prefix = "spring.sgrain.jackson2.converter", name = "enable", havingValue = "true", matchIfMissing = true)
public class SmallGrainMappingJackson2HttpMessageConverterAutoConfiguration implements CommandLineRunner {
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    public SmallGrainMappingJackson2HttpMessageConverterAutoConfiguration(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {
        this.mappingJackson2HttpMessageConverter = mappingJackson2HttpMessageConverter;
    }

    @PostConstruct
    public void SmallGrainMappingJackson2HttpMessageConverterAutoConfiguration() {
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
    }

    @Override
    public void run(String... args) throws Exception {
        LoggerUtils.info(SmallGrainMappingJackson2HttpMessageConverterAutoConfiguration.class, "【自动化配置】----响应报文Content-Type编码组件初始化完成...");
    }
}
