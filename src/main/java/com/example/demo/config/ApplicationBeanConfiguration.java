package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.util.FileUtil;
import com.example.demo.util.FileUtilImpl;
import com.example.demo.util.ValidationUtil;
import com.example.demo.util.ValidationUtilImpl;
import com.example.demo.util.XmlParser;
import com.example.demo.util.XmlParserImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
public class ApplicationBeanConfiguration {

	@Bean
	public FileUtil fileUtil() {
		return new FileUtilImpl();
	}

	@Bean
	public Gson gson() {
		return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
	}

	@Bean
	public XmlParser xmlParser() {
		return new XmlParserImpl();
	}

	@Bean
	public ValidationUtil validationUtil() {
		return new ValidationUtilImpl();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
