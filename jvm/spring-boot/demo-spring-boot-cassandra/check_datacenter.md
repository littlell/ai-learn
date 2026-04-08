# 查看 Cassandra Datacenter 信息

## 方法一：查看当前数据中心名称
```bash
docker exec -it cassandra-demo cqlsh -e "SELECT data_center FROM system.local;"
```

## 方法二：查看所有节点信息
```bash
docker exec -it cassandra-demo cqlsh -e "SELECT * FROM system.peers;"
```

## 方法三：查看集群状态
```bash
docker exec -it cassandra-demo nodetool status
```

## 方法四：详细集群信息
```bash
docker exec -it cassandra-demo cqlsh -e "
SELECT peer, data_center, rack, release_version 
FROM system.peers;
"
```

## 常见的 Datacenter 名称
- `datacenter1` - 默认单节点/开发环境
- `DC1`, `DC2` - 简单命名
- `us-east-1`, `us-west-2` - AWS 区域命名
- `beijing`, `shanghai` - 地理位置命名
