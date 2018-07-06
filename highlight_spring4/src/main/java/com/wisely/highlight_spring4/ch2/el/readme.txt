Spring EL 和资源调用

涉及注解：@Service、@Value、@Configuration、@ComponentScan、@PropertySource

 Scope 描述的是 Spring 容器如何新建 Bean 的实例的。Spring 的 Scope 有以下几种，通过 @Scope 注解来实现。
 1. Singleton：一个 Spring 容器中只有一个 Bean 的实例，此为 Spring 的默认配置，全容器共享一个实例。
 2. Prototype：每次调用新建一个 Bean 的实例。
 3. Request： Web 项目中，给每一个 http request 新建一个 Bean 实例。
 4. Session： Web 项目中，给每一个 http session 新建一个 Bean 实例。

增加 commons-io 可简化文件相关操作，本例中使用 commons-io 将 file 转换成字符串。
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.3</version>
</dependency>