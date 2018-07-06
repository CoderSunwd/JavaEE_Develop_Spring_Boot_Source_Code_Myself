Spring Aware

使用 Spring 提供的 Aware 接口，才能调用 Spring 所提供的资源。

Spring Aware 接口包括：
BeanNameAware                       获得到容器中Bean的名称
BeanFactoryAware                    获得当前bean Factory,从而调用容器的服务
ApplicationContextAware             当前的application context从而调用容器的服务
MessageSourceAware                  得到message source从而得到文本信息
ApplicationEventPublisherAware      应用时间发布器,用于发布事件
ResourceLoaderAware                 获取资源加载器,可以获得外部资源文件