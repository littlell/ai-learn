#### Spring Core 示例（单根包 `core`，按功能分子包）

所有示例在 **com.demo.jvm.spring.core** 下，按功能聚合为子包，便于按主题浏览和运行。

| 子包 | 内容 |
|------|------|
| **ioc.hello** | IoC HelloWorld |
| **ioc.aliasing** | Aliasing a bean outside the bean definition |
| **ioc.staticfactory** | Instantiation with a static factory method |
| **ioc.instancefactory** | Instantiation using an instance factory method |
| **di.constructor** | Constructor-based dependency injection |
| **di.setter** | Setter-based dependency injection |
| **di.collections** | Collections injection |
| **di.nullempty** | Null and empty string values |
| **di.pnamespace** | XML shortcut with the p-namespace and c-namespace |
| **lifecycle.dependson** | Using depends-on |
| **lifecycle.lazy** | Lazy-initialized beans |
| **lifecycle.scope** | Bean scopes |
| **bean.aware** | Customizing the nature of a bean and bean aware |
| **annotation.config** | Annotation-based container configuration |
| **annotation.scanning** | Classpath scanning and managed components |
| **javaconfig.pure** | Java-based container configuration |
| **javaconfig.importxml** | Java-based configuration import xml configuration |
| **javaconfig.importjava** | Xml configuration import Java-based container configuration |
| **spel** | Spring Expression Language (SpEL) |
| **aop.schema** | Schema-based AOP |
| **aop.aspectj** | @AspectJ-based AOP |
| **tx.propagation** | Spring tx propagation |
| **tx.isolation** | Spring tx isolation |

**运行方式**：在 IDE 中运行对应子包下的 `Main` 类。配置文件与包路径对应，例如：

- `src/main/resources/ioc/hello/applicationContext.xml`
- `src/main/resources/tx/propagation/applicationContext.xml`（另有 `db.properties`）
- `javaconfig/importxml` 下有 `default.properties`

**编译**：在本目录执行 `mvn compile`。
