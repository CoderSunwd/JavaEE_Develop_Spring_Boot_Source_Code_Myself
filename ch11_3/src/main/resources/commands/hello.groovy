package commands
import org.crsh.cli.Command
import org.crsh.cli.Usage
import org.crsh.command.InvocationContext
class hello {
    @Usage("Say Hello") // 使用 @Usage 注解解释该命令的用途
    @Command // 使用 @Command 注解当前是一个 CRaSH 命令。
    def main(InvocationContext context) {
        
        def bootVersion = context.attributes['spring.boot.version']; // 获得 Spring Boot 的版本，注意 Groovy 的方法和变量声明关键字为 def。
        def springVersion = context.attributes['spring.version'] //获得 Spring 框架的版本。
        
        // 返回命令执行结果。
        return "Hello, your Spring Boot version is " + bootVersion + ", your Spring Framework version is " + springVersion
    }
}