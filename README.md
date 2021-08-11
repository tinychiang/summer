# summer

## 1. 介绍

Java Mesh Service - 技术与业务充分解耦网格服务架构，并尽最大可能集成所有微服务技术栈。

## 2. 技术栈

`Spring-Cloud-Netflix-Eureka` `Spring-Cloud-Netflix-Feign` `Spring-Cloud-Netflix-Gateway` `Spring-Cloud-Netflix-Zuul` `Consul` `Apollo` `Springboot` `Elasticsearch` `Redis` `MongoDB` `Zookeeper` `RocketMQ` `Kafka` `RabitMQ` `MySQL` `Durid` `Mybatis-plus` `Xxl-job` `Spring-Cloud-alibaba-Nacos` `Dubbo` `Spring-Cloud-alibaba-Sentinel` `Go-fastdfs` `Swagger` `Arthas` `Nginx` `Docker`

## 3. 项目结构

```shell
.
├── summer-cloud    # Cloud 服务依赖
│   ├── summer-eureka   # Eureka Server 注册中心
│   └── summer-gateway  # Spring Cloud Gateway 网关
└── summer-context  # 服务主体
    ├── summer-api      # RPC API 接口
    ├── summer-biz      # 业务逻辑
    ├── summer-db       # 数据处理, 关系型&&非关系型
    ├── summer-frame    # 架构封装
    │   ├── summer-frame-commons        # 共通
    │   ├── summer-frame-elasticsearch  # elasticsearch 配置与封装
    │   ├── summer-frame-mybatis-plus   # mybatis plus 配置与封装
    │   ├── summer-frame-redis          # redis 配置与封装
    │   └── summer-frame-web            # spring boot web 配置与封装
    ├── summer-queue    # 消息队列配置与封装
    │   ├── summer-queue-kafka  # kafka 配置与封装
    │   ├── summer-queue-rabbit # rabbit 配置与封装
    │   └── summer-queue-rocket # rocket 配置与封装
    └── summer-starter  # 启动入口
        ├── summer-alibaba  # Spring Cloud Alibaba
        └── summer-netflix  # Spring Cloud Netflix
```