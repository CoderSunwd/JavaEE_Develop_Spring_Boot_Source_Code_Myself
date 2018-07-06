Profile

Profile 为在不同环境下使用不同的配置提供了支持（开发环境下的配置和生产环境下的配置肯定是不同的，例如，数据库的配置）。

（1）通过设定 Environment 的 ActiveProfiles 来设定当前 context 需要使用的配置环境。在开发中使用 @Profile 注解类或者方法，达到不同情况下选择实例化不同的 Bean。
（2）通过设定 jvm 的 spring.profiles.active 参数来设置配置环境。
（3）Web 项目设置在 Servlet 的 context parameter 中。

涉及注解：@Configuration、@Bean、@Profile
