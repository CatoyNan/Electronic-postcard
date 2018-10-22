package com.example.demo;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication extends WebMvcConfigurerAdapter{
//xutest
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter=new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig=new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		List<MediaType> mediaTypeList=new ArrayList<MediaType>();
		mediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
		fastJsonHttpMessageConverter.setSupportedMediaTypes(mediaTypeList);
		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
		converters.add(fastJsonHttpMessageConverter);
	}

//	@Override
////	public void addResourceHandlers(ResourceHandlerRegistry registry) {
////		File path = null;
////		try {
////			path = new File(ResourceUtils.getURL("classpath:").getPath());
////		} catch (FileNotFoundException e) {
////			e.printStackTrace();
////		}
////		String gitPath=path.getParentFile().getParentFile().getParent()+File.separator+"logistics"+File.separator+"uploads"+File.separator;
////		registry.addResourceHandler("/uploads/**").addResourceLocations(gitPath);
////		registry.addResourceHandler("/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
////		super.addResourceHandlers(registry);
////	}
//xut4est
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		super.addResourceHandlers(registry);
//		registry.addResourceHandler("/uploadfile").addResourceLocations(
//				"D:\\tts");
//	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
