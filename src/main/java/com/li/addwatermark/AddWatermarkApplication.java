package com.li.addwatermark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * @author likexue
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class AddWatermarkApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddWatermarkApplication.class, args);
		try {
			String path = ResourceUtils.getURL("classpath:").getPath();
			System.out.println(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
