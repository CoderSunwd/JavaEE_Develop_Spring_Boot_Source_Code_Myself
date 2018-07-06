AOP

涉及注解：@Target、@Retention、@Documented、@Service、@Aspect、@Component、@Pointcut、@After、@Before、
       @Configuration、@ComponentScan、@EnableAspectJAutoProxy

需要添加 spring aop 支持及 AspectJ 依赖。
<!-- spring aop 支持 -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aop</artifactId>
    <version>4.1.6.RELEASE</version>
</dependency>
<!-- aspectj 支持 -->
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjrt</artifactId>
    <version>1.8.5</version>
</dependency>
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjtools</artifactId>
    <version>1.8.5</version>
</dependency>

书中所写的aspectjweaver没有调通