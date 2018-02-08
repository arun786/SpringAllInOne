# Reading a Property file

if we have properties files other than application.properties file, we need to follow the following.

1. In the main application, where
 	@SpringBootApplication is defined, we need to add
 	@PropertySource("classpath:<name of the Property file>") or
	@PropertySource({"classpath:database.properties","classpath:security.properties"})

2. where we are referring the file, we will be using @Value, but here the values will be automatically assigned.

Example as below

1. we are adding the below property file

Database.properties
	
	spring.datasource.url=jdbc:mysql://localhost/school
	spring.datasource.username=root
	spring.datasource.password=root
	spring.jpa.hibernate.ddl-auto=none

2. Changes to the main application.

	@SpringBootApplication
	//@PropertySource("classpath:database.properties")
	@PropertySource({"classpath:database.properties","classpath:security.properties"})
	public class SpringInerviewApplication {
	
		public static void main(String[] args) {
			SpringApplication.run(SpringInerviewApplication.class, args);
		}
	}