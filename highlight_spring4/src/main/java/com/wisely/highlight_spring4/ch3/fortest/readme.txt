测试

Spring 通过 SpringTestContexFramework 对集成测试提供顶级支持。它不依赖于特定的测试框架，既可使用Junit，也可使用TestNG。

基于 Maven 构建的项目结构默认有关于测试的目录：src/test/java(测试代码）、src/test/resources(测拭资源），
区别于src/main/java(项目源码）、src/main/resources(项目资源)。

Spring 提供了一个 SpringJUnit4ClassRunner 类，它提供了 SpringTestContextFramework 的功能。
通过 @ContextConfiguration 来配置 ApplicationContext，通过 @ActiveProfiles 确定活动的profile。

需要增加 Spring 测试的依赖包到 Maven：
<!-- Spring test 支持 -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>${spring-framework.version}</version>
</dependency>
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.11</version>
</dependency>