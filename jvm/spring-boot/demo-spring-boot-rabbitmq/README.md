# Demo Spring Boot 03 - RabbitMQ交换机示例

这个项目演示了如何在Spring Boot中使用RabbitMQ的不同类型交换机：
- **直连交换机（Direct Exchange）**：根据路由键精确匹配
- **扇出交换机（Fanout Exchange）**：广播到所有绑定队列
- **主题交换机（Topic Exchange）**：支持通配符路由，灵活匹配

## 项目结构

```
src/main/java/com/demo/jvm/spring/boot/boot03/
├── RabbitMain.java                      # 启动类
├── config/
│   ├── DirectRabbitConfig.java         # 直连交换机配置类
│   ├── FanoutRabbitConfig.java         # 扇出交换机配置类
│   └── TopicRabbitConfig.java          # 主题交换机配置类
├── producer/
│   ├── DirectMessageProducer.java      # 直连交换机消息生产者
│   ├── FanoutMessageProducer.java      # 扇出交换机消息生产者
│   └── TopicMessageProducer.java       # 主题交换机消息生产者
├── consumer/
│   ├── DirectMessageConsumer.java      # 直连交换机消息消费者
│   ├── FanoutMessageConsumer.java      # 扇出交换机消息消费者
│   └── TopicMessageConsumer.java       # 主题交换机消息消费者
└── controller/
    ├── IndexController.java            # 主页控制器
    ├── DirectMessageController.java    # 直连交换机测试控制器
    ├── FanoutMessageController.java    # 扇出交换机测试控制器
    └── TopicMessageController.java     # 主题交换机测试控制器
```

## 交换机工作原理

### 直连交换机（Direct Exchange）
- 根据路由键（routing key）精确匹配
- 消息只会发送到绑定键与路由键完全匹配的队列

**配置**：
- **交换机**: `direct.exchange`
- **队列**: 
  - `direct.queue.info` (绑定键: `info`)
  - `direct.queue.warning` (绑定键: `warning`) 
  - `direct.queue.error` (绑定键: `error`)

### 扇出交换机（Fanout Exchange）
- 忽略路由键，将消息广播到所有绑定的队列
- 适用于需要同时通知多个服务的场景

**配置**：
- **交换机**: `fanout.exchange`
- **队列**: 
  - `fanout.queue.email` (邮件服务)
  - `fanout.queue.sms` (短信服务)
  - `fanout.queue.push` (推送服务)

### 主题交换机（Topic Exchange）
- 支持通配符路由，比直连交换机更灵活
- `*` 匹配一个单词，`#` 匹配零个或多个单词
- 适用于复杂的路由规则和分类订阅

**配置**：
- **交换机**: `topic.exchange`
- **队列**: 
  - `topic.queue.order` (绑定: `order.*`)
  - `topic.queue.user` (绑定: `user.*`)
  - `topic.queue.all` (绑定: `#`)

## 配置说明

在 `application.yml` 中配置了：

```yaml
mq:
  config:
    # 直连交换机配置
    direct:
      exchange: direct.exchange
      queue:
        info: direct.queue.info
        warning: direct.queue.warning
        error: direct.queue.error
      routing-key:
        info: info
        warning: warning
        error: error
    # 扇出交换机配置
    fanout:
      exchange: fanout.exchange
      queue:
        email: fanout.queue.email
        sms: fanout.queue.sms
        push: fanout.queue.push
    # 主题交换机配置
    topic:
      exchange: topic.exchange
      queue:
        order: topic.queue.order
        user: topic.queue.user
        all: topic.queue.all
      routing-key:
        order-create: order.create
        order-cancel: order.cancel
        user-login: user.login
        user-logout: user.logout
```

## 如何运行

1. 启动RabbitMQ服务器
2. 修改 `application.yml` 中的RabbitMQ连接配置
3. 运行 `RabbitMain.java`
4. 应用启动后访问: http://localhost:9000

## API测试

### 浏览器测试（推荐）

访问测试首页：**http://localhost:9000/**

#### 主要测试页面：
- **主页**: http://localhost:9000/ (包含所有测试链接)
- **直连交换机测试页**: http://localhost:9000/direct/
- **扇出交换机测试页**: http://localhost:9000/fanout/
- **主题交换机测试页**: http://localhost:9000/topic/

#### 直连交换机测试：
- **发送INFO消息**: http://localhost:9000/direct/info?message=消息内容
- **发送WARNING消息**: http://localhost:9000/direct/warning?message=消息内容
- **发送ERROR消息**: http://localhost:9000/direct/error?message=消息内容
- **批量测试**: http://localhost:9000/direct/test

#### 扇出交换机测试：
- **发送广播消息**: http://localhost:9000/fanout/broadcast?message=消息内容
- **批量测试**: http://localhost:9000/fanout/test

#### 主题交换机测试：
- **发送订单创建消息**: http://localhost:9000/topic/order/create?orderId=订单ID
- **发送订单取消消息**: http://localhost:9000/topic/order/cancel?orderId=订单ID
- **发送用户登录消息**: http://localhost:9000/topic/user/login?username=用户名
- **发送用户登出消息**: http://localhost:9000/topic/user/logout?username=用户名
- **批量测试**: http://localhost:9000/topic/test

### 命令行测试

```bash
# 直连交换机测试
curl "http://localhost:9000/direct/info?message=用户登录成功"
curl "http://localhost:9000/direct/warning?message=系统负载较高"
curl "http://localhost:9000/direct/error?message=数据库连接失败"

# 扇出交换机测试
curl "http://localhost:9000/fanout/broadcast?message=系统维护通知"
curl "http://localhost:9000/fanout/test"

# 主题交换机测试
curl "http://localhost:9000/topic/order/create?orderId=ORDER123"
curl "http://localhost:9000/topic/user/login?username=张三"
curl "http://localhost:9000/topic/test"
```

## 消息处理

### 直连交换机消息处理
消费者会根据不同的消息级别进行相应的处理：
- **INFO**: 记录信息，进行常规业务处理
- **WARNING**: 记录警告，可能发送告警通知
- **ERROR**: 记录错误，立即处理并发送紧急通知

### 扇出交换机消息处理
扇出交换机将消息同时发送到所有绑定的队列：
- **邮件服务**: 处理邮件发送逻辑
- **短信服务**: 处理短信发送逻辑
- **推送服务**: 处理推送通知逻辑

### 主题交换机消息处理
主题交换机根据路由键模式匹配消息：
- **订单队列** (order.*): 处理所有订单相关消息
- **用户队列** (user.*): 处理所有用户相关消息
- **全部队列** (#): 记录所有消息到日志系统

## 观察结果

运行测试后，你可以在控制台看到：

### 直连交换机测试结果：
1. 生产者发送消息到指定路由键
2. 只有匹配的队列消费者接收到消息
3. 不同级别消息的处理逻辑展示

### 扇出交换机测试结果：
1. 生产者发送一条广播消息
2. 所有绑定的队列（邮件、短信、推送）同时接收到消息
3. 三个服务同时处理同一条消息

### 主题交换机测试结果：
1. 生产者发送带有特定路由键的消息
2. 消息被路由到匹配的队列（order.* 或 user.*）
3. 全部队列（#）总是能接收到所有消息
4. 体现了灵活的路由匹配规则

## 应用场景

### 直连交换机适用场景：
- 日志分级处理
- 不同优先级的任务处理
- 根据消息类型路由到不同处理器

### 扇出交换机适用场景：
- 多渠道通知（邮件、短信、推送同时发送）
- 系统广播消息
- 数据同步到多个系统

### 主题交换机适用场景：
- 复杂的路由规则（如按业务模块路由）
- 分类订阅（不同服务订阅不同类型消息）
- 日志系统（按级别和模块分类）
- 微服务间的事件通知
