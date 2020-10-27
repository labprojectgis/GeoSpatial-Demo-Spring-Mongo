package com.freelance.geospatial.demo.configuration;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.support.DefaultServerCodecConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.slf4j.Logger;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

import javax.annotation.PostConstruct;

@EnableAutoConfiguration
@ComponentScan
@EnableSwagger2
@SpringBootConfiguration
public class GeoConfiguration{
	

	private static final Logger log = LoggerFactory.getLogger(GeoConfiguration.class);
	
	@Value("${spring.data.mongodb.host}")
    private String mongoHost;        	

    @Autowired
    private MongoMappingContext mongoMappingContext;

    @PostConstruct
    public void init() {
    	this.mongoMappingContext.setAutoIndexCreation(true);
    	log.info("Mongo AutoIndexCreation: {}", this.mongoMappingContext.isAutoIndexCreation());
    }
    
    @Bean
    public MongoClient reactiveMongoClient()  {
    	String myHost = System.getenv("MONGODB_HOST");		
		log.info("MONGODB_HOST="+myHost);
        if(myHost==null) {
        	return MongoClients.create();
        }
		return MongoClients.create("mongodb://"+ myHost);
    }	

    @Bean
	public ServerCodecConfigurer serverCodecConfigurer() {
		return new DefaultServerCodecConfigurer();
	}
    

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.freelance.geospatial.demo"))              
          .paths(regex("/geodata.*"))                          
          .build();                                           
    }

}
