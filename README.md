# summer

`Java` `Mesh Service`

## 1. 介绍

技术与业务充分解耦的网格服务架构，并尽最大可能集成所有微服务技术栈。

## 2. 技术栈

- 核心<br/>
  `Spring Cloud Hoxton.SR8` `Spring Cloud Alibaba 2.X` `Spring Boot 2.X`
- 网关&代理<br/>
  `Gateway` `Zuul` `Nginx`
- 配置中心<br/>
  `Apollo`
- 注册中心<br/>
  `Eureka` `Nacos` `Consul` `Zookeeper`
- RPC<br/>
  `Feign` `Dubbo`
- 熔断器<br/>
  `Sentinel` `Hystrix`
- 搜索引擎<br/>
  `Elasticsearch 7.X` `Solr`
- 存储<br/>
  `MySQL 8` `Postgre`
- 缓存<br/>
  `Redis 6.X` `Memcached`
- 消息中间件<br/>
  `RocketMQ` `Kafka` `RabitMQ`
- 文件存储<br/>
  `MongoDB` `Go-fastdfs` `CEPH`
- DB连接池&SQL处理<br/>
  `Durid` `Spring Data JDBC` `Mybatis-Plus` `Spring Data JPA`
- 定时任务<br/>
  `xxl-job` `Spring Batch`
- RestAPI<br/>
  `Swagger 2.X`
- 部署&运维<br/>
  `Docker` `Jenkins` `Arthas` `Prometheus`
- ui<br/>
  `Vue 3.X` `Element-Plus`

## 3. 项目结构

```shell
.
├── summer-cloud    # Cloud 服务依赖
│   ├── summer-eureka   # Eureka Server 注册中心
│   └── summer-gateway  # Spring Cloud Gateway 网关
├── summer-context  # 服务主体
│   ├── summer-api      # RPC API 接口
│   ├── summer-biz      # 业务逻辑
│   ├── summer-db       # 数据处理, 关系型&&非关系型
│   ├── summer-frame    # 架构封装
│   │   ├── summer-frame-commons        # 共通
│   │   ├── summer-frame-elasticsearch  # elasticsearch 配置与封装
│   │   ├── summer-frame-mybatis-plus   # mybatis plus 配置与封装
│   │   ├── summer-frame-redis          # redis 配置与封装
│   │   ├── summer-frame-web            # spring boot web 配置与封装
│   ├── summer-queue    # 消息队列配置与封装
│   │   ├── summer-queue-kafka  # kafka 配置与封装
│   │   ├── summer-queue-rabbit # rabbit 配置与封装
│   │   ├── summer-queue-rocket # rocket 配置与封装
│   └── summer-starter  # 启动入口
│       ├── summer-alibaba  # Spring Cloud Alibaba
│       ├── summer-netflix  # Spring Cloud Netflix
└── summer-ui       # 前端
```

## 4. summer-frame

### 4.1. summer-frame-commons

### 4.2. summer-frame-web

### 4.3. summer-frame-elasticsearch

### 4.4. summer-frame-mybatis-plus

### 4.5. summer-frame-redis

## 5. summer-starter

### 5.1. summer-alibaba

### 5.2. summer-netflix