### Docker 部署 springboot 项目

1. 打包 springboot 项目。

   ```shell
   $ mvn clean package -Dmaven.test.skip=true
   ```

2. 创建 Dockerfile 文件

   ```dockerfile
   # 基础镜像
   FROM jdk:8
   
   # 作者信息
   MAINTAINER "sixkery"
   
   # 添加一个存储空间
   VOLUME /tmp
   
   # 暴露端口
   EXPOSE 8080
   
   # 添加变量，如果使用 dockerfile-maven-plugin, 则会自动替换这里的变量内容
   ARG JAR_FILE=target/code-docker.jar
   
   # 往容器中添加 jar 包
   ADD ${JAR_FILE} app.jar
   
   # 启动镜像自动运行程序
   
   ENTRYPOINT ["java","-Djava.security.egd=file:/dev/urandom","-jar","/app.jar"]
   
   ```

   

3. 把 springboot 打的包和 Dockerfile 放在一个文件夹 code-docker下。

4. 进入文件夹 code-docker下构建镜像

   ```shell
   $ docker build -t code-docker .
   
   # 查看镜像
   $ docker images
   
   ```

5. 运行镜像

   ```shell
   $ docker run -p 8080:8080 -d code-docker --name code-docker
   
      -p: 主机端口:容器端口
      -d: 后台运行，并返回容器 ID
      --name="vueblog": 为容器指定一个名称 
   ```

   



