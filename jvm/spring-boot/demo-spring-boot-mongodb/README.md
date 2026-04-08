# Demo Spring Boot 05 - MongoDB 数据库操作示例

这是一个完整的 Spring Boot + MongoDB 集成示例，展示了客户管理系统的基本功能。

## 项目结构

```
demo-spring-boot-mongodb/
├── src/main/java/com/demo/jvm/spring/boot/boot05/
│   ├── entity/
│   │   └── Customer.java           # 客户实体类
│   ├── repository/
│   │   └── CustomerRepository.java # 客户数据访问层
│   ├── service/
│   │   └── CustomerService.java    # 客户业务逻辑层
│   ├── controller/
│   │   └── CustomerController.java # 客户API控制器
│   ├── config/
│   │   └── MongoConfig.java        # MongoDB配置
│   └── App.java                    # 主启动类
├── src/main/resources/
│   └── application.yml             # 应用配置
├── check_mongodb.md                # MongoDB检查文档
├── create_collection.js            # 集合创建脚本
└── README.md
```

## 快速开始

### 1. 启动 MongoDB

使用 Docker 快速启动：

```bash
docker run --name mongodb-demo -p 27017:27017 -d mongo:5.0
```

等待 10-20 秒让 MongoDB 完全启动。

### 2. 运行应用

```bash
cd demo-spring-boot-mongodb
mvn spring-boot:run
```

应用将在 `http://localhost:8080` 启动。

## 实体类

**Customer 实体**：
- id: String (主键，MongoDB ObjectId)
- firstName: String (名字)
- lastName: String (姓氏)
- email: String (邮箱)
- age: Integer (年龄)

## API 接口

### 创建客户
```bash
POST http://localhost:8080/api/customers
Content-Type: application/json

{
  "firstName": "张三",
  "lastName": "王",
  "email": "zhangsan@example.com",
  "age": 25
}
```

### 获取所有客户
```bash
GET http://localhost:8080/api/customers
```

### 根据ID获取客户
```bash
GET http://localhost:8080/api/customers/{customerId}
```

### 根据名字获取客户
```bash
GET http://localhost:8080/api/customers/firstName/{firstName}
```

### 根据姓氏获取客户
```bash
GET http://localhost:8080/api/customers/lastName/{lastName}
```

### 根据邮箱获取客户
```bash
GET http://localhost:8080/api/customers/email/{email}
```

### 根据年龄查找客户
```bash
GET http://localhost:8080/api/customers/age/{age}
```

### 根据年龄范围查找客户
```bash
GET http://localhost:8080/api/customers/age/between?minAge=25&maxAge=35
```

### 根据名字模糊搜索客户
```bash
GET http://localhost:8080/api/customers/search/firstName/{firstName}
```

### 根据姓名查找客户
```bash
GET http://localhost:8080/api/customers/fullName?firstName=张三&lastName=王
```

### 更新客户信息
```bash
PUT http://localhost:8080/api/customers/{customerId}
Content-Type: application/json

{
  "firstName": "张三",
  "lastName": "王",
  "email": "zhangsan_new@example.com",
  "age": 26
}
```

### 删除客户
```bash
DELETE http://localhost:8080/api/customers/{customerId}
```

### 删除所有客户
```bash
DELETE http://localhost:8080/api/customers
```

### 获取客户总数
```bash
GET http://localhost:8080/api/customers/count
```

## 配置文件

`application.yml`:

```yaml
spring:
  application:
    name: demo-spring-boot-mongodb
  data:
    mongodb:
      host: localhost
      port: 27017
      database: spring_mongodb
      authentication-database: admin

server:
  port: 8080
```

## 测试示例

1. **创建客户**：
```bash
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{"firstName":"测试用户","lastName":"李","email":"test@example.com","age":30}'
```

2. **查看所有客户**：
```bash
curl http://localhost:8080/api/customers
```

3. **根据年龄查找**：
```bash
curl http://localhost:8080/api/customers/age/30
```

4. **根据年龄范围查找**：
```bash
curl "http://localhost:8080/api/customers/age/between?minAge=25&maxAge=35"
```

5. **模糊搜索名字**：
```bash
curl http://localhost:8080/api/customers/search/firstName/张
```

## 特性说明

### 1. 完整的分层架构
- **Entity Layer**: 数据实体定义
- **Repository Layer**: 数据访问层，包含自定义查询方法
- **Service Layer**: 业务逻辑层，封装业务操作
- **Controller Layer**: REST API 控制器

### 2. 丰富的查询功能
- 基本 CRUD 操作
- 自定义查询方法
- 范围查询
- 模糊查询
- 组合条件查询

### 3. MongoDB 特性
- 使用 `@Document` 注解指定集合名称
- 支持复杂查询条件
- 使用 `@Query` 注解自定义查询

## 注意事项

- 确保 MongoDB 在端口 27017 上运行
- 应用会自动创建数据库和集合
- 使用了 MongoDB 的文档数据库特性
- 支持中文数据存储和查询

## 常见问题

### 1. 连接失败
- 等待 MongoDB 完全启动（通常需要 10-20 秒）
- 检查端口 27017 是否被占用
- 确认 Docker 容器正在运行

### 2. 数据库不存在
MongoDB 会自动创建数据库和集合，无需手动创建。

### 3. 中文显示问题
确保客户端支持 UTF-8 编码。

### 4. 查询性能
对于生产环境，建议：
- 在经常查询的字段上创建索引
- 使用投影查询减少数据传输
- 实现分页查询

## 扩展功能

可以考虑添加的功能：
- 分页查询支持
- 数据验证
- 审计日志
- 缓存支持
- 搜索索引

这是一个功能完整的 MongoDB 集成示例，展示了现代 Spring Boot 应用的最佳实践。

