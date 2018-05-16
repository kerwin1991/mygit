##Springboot配置Profile
- 多环境配置文件，文件名 application-{profile}.properties/yml，默认使用application.properties
- yml 文档块 ---  ---  ---
- 指定环境启动springboot：
    0. 配置文件指定：spring.profiles.active
    
    1. 命令行参数指定:
        ```
        idea        
            program arguments:--spring.profiles.active=prod
        package打包，启动jar包：
            java -jar spring-boot-sgg-one-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
        ```
    2. 虚拟机携带参数：
        ```
        idea
            VM options: -Dspring.profiles.active=prod
        ```

- install：打包到本地仓库；deploy：打包到私服；package:打包到项目target目录下。

##Springboot 日志


0. 框架，记录系统的运行时信息：日志框架。

##Springboot Web开发

- 创建项目，选中需要的模块；springboot已经配置好了，在配置文件中少量配置就可以运行；编写业务代码

0. springboot对静态资源的映射规则

    webjars，以jar包方式引入静态资源。
    
    ```java    
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!this.resourceProperties.isAddMappings()) {
            logger.debug("Default resource handling disabled");
            return;
        }
        Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
        CacheControl cacheControl = this.resourceProperties.getCache()
                .getCachecontrol().toHttpCacheControl();
        if (!registry.hasMappingForPattern("/webjars/**")) {
            customizeResourceHandlerRegistration(registry
                    .addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/")
                    .setCachePeriod(getSeconds(cachePeriod))
                    .setCacheControl(cacheControl));
        }
        String staticPathPattern = this.mvcProperties.getStaticPathPattern();
        if (!registry.hasMappingForPattern(staticPathPattern)) {
            customizeResourceHandlerRegistration(
                    registry.addResourceHandler(staticPathPattern)
                            .addResourceLocations(getResourceLocations(
                                    this.resourceProperties.getStaticLocations()))
                            .setCachePeriod(getSeconds(cachePeriod))
                            .setCacheControl(cacheControl));
        }
    }
    ```

##模板引擎##

- JSP、Velocity、Freemarker、Thymeleaf

    写一个模板+数据 --> TemplateEngine --> 输出html

0. thymeleaf

    引入spring-boot-starter-thymeleaf





[1.](#1)

[1.1](#1.1)

[baidu](www.baidu.com)