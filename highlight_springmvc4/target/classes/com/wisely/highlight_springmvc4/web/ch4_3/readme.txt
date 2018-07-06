Spring MVC 的常用注解

(1)@Controller

@Controller 注解在类上，表明这个类是 SpringMVC 里的 ConlroHer ,将其声明为 Spring 的一个 Bean ，
DispatcherServlet 会自动扫描注解了此注解的类（这里和我们在1.3.3节淡示用注解作为拦截方式的例子原理类似),
并将Web请求映射到注解了 @RequestMapping 的方法上。这里特别指出，在声明普通Bean的时候，使用 @Component、
@Service、@Repository和@Controller 是等同的，因为 @Service、@Repository、@Controller
都组合了 @Component 元注解；但在 SpringMVC 声明控制器 Bean 的时候，只能使用 @Controller

(2)@RequestMapping

@RequestMapping 注解是用来映射 Web 请求（访问路径和参数）、处理类和方法的。@RequeslMapping 可注解在类或方法上。
注解在方法上的 @RequestMapping 路径会继承注解在类上的路径，@RequestMapping 支持 Servlet 的request和response作为参数，
也支持对request和response的媒体类型进行配置。

(3)@ResponseBody

@ResponseBody 支持将返回值放在response体内，而不是返回一个页面。我们在很多基于Ajax的程序的时候，
可以以此注解返回数据而不是页面：此注解可放置在返回值前或者方法上。

(4)@RequestBody

@RequestBody 允许request的参数在request体中，而不是在直接链接在地址后面。此注解放置在参数前。

(5)@PathVariable

@PathVariable 用来接收路径参数，如/news/001,可接收00丨作为参数，此注解放置在参数前。


添加 jackson 及相关依赖，获得对象和 json 之间的转换。
<!-- 对json格式的支持 -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.5.3</version>
</dependency>

