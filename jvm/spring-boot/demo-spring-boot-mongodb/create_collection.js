// MongoDB 集合创建和初始化脚本
// 如果自动创建集合失败，可以手动执行这些脚本

// 切换到 spring_mongodb 数据库
use spring_mongodb;

// 创建 customers 集合（如果不存在）
db.createCollection("customers");

// 创建索引以提高查询性能
db.customers.createIndex({ "firstName": 1 });
db.customers.createIndex({ "lastName": 1 });
db.customers.createIndex({ "email": 1 }, { unique: true });
db.customers.createIndex({ "age": 1 });
db.customers.createIndex({ "firstName": 1, "lastName": 1 });

// 插入测试数据
db.customers.insertMany([
  {
    firstName: "张三",
    lastName: "王",
    email: "zhangsan@example.com",
    age: 25
  },
  {
    firstName: "李四",
    lastName: "李",
    email: "lisi@example.com",
    age: 30
  },
  {
    firstName: "王五",
    lastName: "王",
    email: "wangwu@example.com",
    age: 28
  },
  {
    firstName: "赵六",
    lastName: "赵",
    email: "zhaoliu@example.com",
    age: 35
  },
  {
    firstName: "钱七",
    lastName: "钱",
    email: "qianqi@example.com",
    age: 22
  }
]);

// 验证数据插入
print("=== 插入的客户数据 ===");
db.customers.find().pretty();

// 显示集合统计信息
print("=== 集合统计信息 ===");
db.customers.stats();

// 显示索引信息
print("=== 索引信息 ===");
db.customers.getIndexes();

print("MongoDB 集合和数据初始化完成！");

