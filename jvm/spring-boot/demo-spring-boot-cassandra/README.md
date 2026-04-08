# Demo Spring Boot 04 - Cassandra 数据库操作示例（简化版）

这是一个简单的 Spring Boot + Cassandra 集成示例。

## 项目结构

```
demo-spring-boot-cassandra/
├── src/main/java/com/demo/jvm/spring/boot/boot04/
│   ├── entity/
│   │   └── User.java              # 用户实体类
│   ├── repository/
│   │   └── UserRepository.java    # 用户数据访问层
│   ├── service/
│   │   └── UserService.java       # 用户业务逻辑层
│   ├── controller/
│   │   └── UserController.java    # 用户API控制器
│   ├── config/
│   │   └── CassandraConfig.java   # Cassandra配置
│   └── Boot04Main.java            # 主启动类
├── src/main/resources/
│   └── application.yml            # 应用配置
└── README.md
```

## 快速开始

### 1. 启动 Cassandra

使用 Docker 快速启动：

```bash
docker run --name cassandra-demo -p 9042:9042 -d cassandra:3.11
```

等待 30-60 秒让 Cassandra 完全启动。

### 2. 运行应用

```bash
cd demo-spring-boot-cassandra
mvn spring-boot:run
```

应用将在 `http://localhost:8080` 启动。

## 实体类

**User 实体**：
- id: UUID (主键)
- username: String (用户名)
- email: String (邮箱)
- age: Integer (年龄)

## API 接口

### 创建用户
```bash
POST http://localhost:8080/api/users
Content-Type: application/json

{
  "username": "张三",
  "email": "zhangsan@example.com",
  "age": 25
}
```

### 获取所有用户
```bash
GET http://localhost:8080/api/users
```

### 根据ID获取用户
```bash
GET http://localhost:8080/api/users/{userId}
```

### 根据用户名获取用户
```bash
GET http://localhost:8080/api/users/username/{username}
```

### 根据年龄查找用户
```bash
GET http://localhost:8080/api/users/age/{age}
```

### 删除用户
```bash
DELETE http://localhost:8080/api/users/{userId}
```

## 配置文件

`application.yml`:

```yaml
spring:
  application:
    name: demo-spring-boot-cassandra
  data:
    cassandra:
      keyspace-name: spring_cassandra
      contact-points: 127.0.0.1
      port: 9042
      local-datacenter: datacenter1
      schema-action: CREATE_IF_NOT_EXISTS

server:
  port: 8080
```

## 测试示例

1. **创建用户**：
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"username":"测试用户","email":"test@example.com","age":30}'
```

2. **查看所有用户**：
```bash
curl http://localhost:8080/api/users
```

3. **根据年龄查找**：
```bash
curl http://localhost:8080/api/users/age/30
```

## 注意事项

- 确保 Cassandra 在端口 9042 上运行
- 应用会自动创建 keyspace 和表
- 使用了 `ALLOW FILTERING` 进行查询（生产环境建议优化）

## 常见问题

### 1. Local Datacenter 错误
如果遇到 `local DC must be explicitly set` 错误：

1. 确认 Cassandra 数据中心名称：
```bash
docker exec -it cassandra-demo cqlsh -e "SELECT data_center FROM system.local;"
```

2. 如果数据中心名称不是 `datacenter1`，请更新 `application.yml` 中的配置：
```yaml
spring:
  data:
    cassandra:
      local-datacenter: 你的数据中心名称
```

### 2. 表不存在错误
如果遇到 `table users does not exist` 错误：

**方法一：重启应用**
- 停止应用
- 重新启动，应用会自动创建表和测试数据

**方法二：手动创建表**
```bash
# 进入 Cassandra 容器
docker exec -it cassandra-demo cqlsh

# 执行以下命令
CREATE KEYSPACE IF NOT EXISTS spring_cassandra 
WITH REPLICATION = {
  'class': 'SimpleStrategy',
  'replication_factor': 1
};

USE spring_cassandra;

CREATE TABLE IF NOT EXISTS users (
  id UUID PRIMARY KEY,
  username TEXT,
  email TEXT,
  age INT
);
```

**方法三：使用提供的脚本**
```bash
docker exec -i cassandra-demo cqlsh < create_table.cql
```

### 3. 连接失败
- 等待 Cassandra 完全启动（通常需要 30-60 秒）
- 检查端口 9042 是否被占用
- 确认 Docker 容器正在运行

这是一个最基础的 Cassandra 集成示例，展示了基本的 CRUD 操作。