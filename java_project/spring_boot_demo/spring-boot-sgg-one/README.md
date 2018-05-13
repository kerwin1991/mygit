##Profile
1、多环境配置文件，文件名 application-{profile}.properties/yml，默认使用application.properties
2、yml 文档块 ---  ---  ---

3、指定环境启动springboot：
配置文件指定：spring.profiles.active
命令行参数指定:
    idea 
        program arguments:--spring.profiles.active=prod
    package打包，启动jar包：
        java -jar spring-boot-sgg-one-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
虚拟机携带参数：
    idea
        VM options: -Dspring.profiles.active=prod


install：打包到本地仓库；deploy：打包到私服；package:打包到项目target目录下。