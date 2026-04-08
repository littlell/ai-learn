-- Cassandra 表创建脚本
-- 如果自动创建表失败，可以手动执行这些脚本

-- 创建 keyspace（如果不存在）
CREATE KEYSPACE IF NOT EXISTS spring_cassandra 
WITH REPLICATION = {
  'class': 'SimpleStrategy',
  'replication_factor': 1
};

-- 使用 keyspace
USE spring_cassandra;

-- 创建 users 表
CREATE TABLE IF NOT EXISTS users (
  id UUID PRIMARY KEY,
  username TEXT,
  email TEXT,
  age INT
);

-- 插入测试数据
INSERT INTO users (id, username, email, age) VALUES (uuid(), '张三', 'zhangsan@example.com', 25);
INSERT INTO users (id, username, email, age) VALUES (uuid(), '李四', 'lisi@example.com', 30);
INSERT INTO users (id, username, email, age) VALUES (uuid(), '王五', 'wangwu@example.com', 28);

-- 查看数据
SELECT * FROM users;
