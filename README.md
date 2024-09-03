# Etiqa_Insurance_springboot

Basic Spring boot application created.

Below tools & libraries used 

Spring Tool Suite 4 
JAVA 17 
Spring boot 3.3.3
Mysql

Spring MVC Framework used 
Mockito for code coverage 

Logback for application logs and error logs
Custom Exception created 

Enable Second leave Cache - redis 
Swagger for API documentation - openapi 2.0.6
Kafka  -> basic classes wrote but not used. 


Below annotation used in application.

@SpringBootApplication
 --  This annotation is used to mark the main class of a Spring Boot application. It encapsulates @SpringBootConfiguration , @EnableAutoConfiguration , and @ComponentScan annotations with their default attributes
 
@EnableCaching
--- This annotation is used to enable caching in a Spring Boot application

@Configuration
--- This annotation indicating that an object is a source of bean definitions, it declares beans through @bean methods.

@PropertySource
--- This annotation is used to provide properties file to Spring Environment.

@EnableJpaRepositories
--- This annotation used to enable JPA repositories by using basePackages.

 @Autowired
-- This annotation used for automatic dependency injection

@Priamry
 --- This annotation used for to avoid diamond situation, to give higher preference to a bean when there are multiple beans of the same type like multiple databases.

 @RestController
 -- is class level annotation , used to create web services that return JSON or XML data.

 @RequestMapping
 -- can use in class or method level and map requests to controllers methods.

 @CacheConfig(cacheNames = "customer")
 -- It is used to share common properties such as cache name, cache manager to all methods annotated with cache annotations.

 @PostMapping
 -- It is a shortcut for @RequestMapping annotation with method = RequestMethod.POST attribute

 @GetMapping
 -- It is a shortcut for @RequestMapping annotation with method = RequestMethod.GET attribute

 @PutMapping
-- It is a shortcut for @RequestMapping annotation with method = RequestMethod.PUT attribute

 @DeleteMapping
-- It is a shortcut for @RequestMapping annotation with method = RequestMethod.DELETE attribute

@Service
-- It is used to indicate that an annotated class is a “Service” which implements business logics relate to a particular domain of the application.

@Repository
--- It is used to indicate that a class is a repository, which is a component responsible for accessing and manipulating data from a database.

@SpringBootTest
-- The annotation works by creating the ApplicationContext used in your tests through SpringApplication 

@Moockbean
-- this annotation used for bean which we want for mock the data.



