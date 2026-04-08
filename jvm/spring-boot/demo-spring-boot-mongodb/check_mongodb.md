# 查看 MongoDB 连接和数据库信息

## 方法一：使用 MongoDB Shell 连接
```bash
docker exec -it mongodb-demo mongosh
```

## 方法二：查看数据库列表
```bash
docker exec -it mongodb-demo mongosh --eval "show dbs"
```

## 方法三：查看集合信息
```bash
docker exec -it mongodb-demo mongosh spring_mongodb --eval "show collections"
```

## 方法四：查看客户数据
```bash
docker exec -it mongodb-demo mongosh spring_mongodb --eval "db.customers.find().pretty()"
```

## 方法五：查看集合统计信息
```bash
docker exec -it mongodb-demo mongosh spring_mongodb --eval "db.customers.stats()"
```

## 方法六：查看索引信息
```bash
docker exec -it mongodb-demo mongosh spring_mongodb --eval "db.customers.getIndexes()"
```

## 方法七：查看 MongoDB 服务状态
```bash
docker exec -it mongodb-demo mongosh admin --eval "db.runCommand('serverStatus')"
```

## 常用查询示例

### 按名字查询
```bash
docker exec -it mongodb-demo mongosh spring_mongodb --eval "db.customers.find({firstName: '张三'})"
```

### 按年龄范围查询
```bash
docker exec -it mongodb-demo mongosh spring_mongodb --eval "db.customers.find({age: {\$gte: 25, \$lte: 35}})"
```

### 统计客户数量
```bash
docker exec -it mongodb-demo mongosh spring_mongodb --eval "db.customers.countDocuments()"
```

### 按姓氏分组统计
```bash
docker exec -it mongodb-demo mongosh spring_mongodb --eval "db.customers.aggregate([{\$group: {_id: '\$lastName', count: {\$sum: 1}}}])"
```

## 常见的连接问题排查

### 1. 检查容器状态
```bash
docker ps | grep mongodb-demo
```

### 2. 查看容器日志
```bash
docker logs mongodb-demo
```

### 3. 检查端口映射
```bash
docker port mongodb-demo
```

### 4. 测试网络连接
```bash
telnet localhost 27017
```

## MongoDB 版本信息
```bash
docker exec -it mongodb-demo mongosh --eval "db.version()"
```

