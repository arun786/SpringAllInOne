# Swagger Documentation


Dependencies which we need to add is 

	<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.7.0</version>
	</dependency>
	
	<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.7.0</version>
	</dependency>
	
	
	
Steps to be followed are 

### Configuration required

#### Annotation

    @Configuration :- This denotes its a Configuration class, which is java based Configuration.
    @EnableSwagger2 :- In order to generate Swagger documentation, we use this annotation.

Steps

1. Create a class and annotate it with 
	a. @Configuration
	b. @EnableSwagger2
	

2. Create a bean in the class and annotate it with @Bean. 
	@Bean is used to declare a bean.
	
3. We create a new instance of Docket bean. Docket takes in Documentation type as a parameter. Docket bean is used for creating swagger docs.

4. we can use various annotation in controller such as 

 - > @API - it means the swagger-core will include only those classes which are annotated with @API
 - > @ApiOperation - 
 - > @ApiResponses 
 - > @ApiResponse
	