# How to creat redis cluster in local environment
## Steps
1. `./create-cluster.sh start`: run the redis instances 
2. `./create-cluster.sh create`: actual redis cluster will be created 
3. `./create-cluster.sh stop`: stop all the instances
4. `./create-cluster.sh clean`: remove all the log AOF / log files  

- You can add redis nodes to your redis cluster by using `./create-cluster.sh add-node <port>` command. Be sure to stop newly added nodes by using `./create-cluster.sh stop-node <port>` command.   
## Configuration 
In order to change the configuration of redis cluster, modify below configuration in create-cluster.sh script.  
```text
# Settings
CLUSTER_HOST=127.0.0.1
PORT=7000
TIMEOUT=2000
NODES=6
REPLICAS=1
PROTECTED_MODE=yes
ADDITIONAL_OPTIONS=""
```

# Interacting with the cluster 
```shell
$ redis-cli -c -p 7001
127.0.0.1:7001> set foo bar
-> Redirected to slot [12182] located at 127.0.0.1:7003
OK

127.0.0.1:7001> get foo 
-> Redirected to slot [12182] located at 127.0.0.1:7003
"bar"

127.0.0.1:7001> cluster nodes 
81b7a2268506ff2fad49de5e6b62f57c888d0165 127.0.0.1:7005@17005 slave b0d884d8c648f5e90492dd40e94456bc5968f687 0 1687695039183 2 connected
de2116279f94669396e9450817b827d747b70a11 127.0.0.1:7006@17006 slave ed8902da4e9ee0ef1e13399e9838bf7ac8350c0f 0 1687695039387 3 connected
b0d884d8c648f5e90492dd40e94456bc5968f687 127.0.0.1:7002@17002 master - 0 1687695039000 2 connected 5461-10922
f6fffb66979114ea8da97146ac334b013aa31455 127.0.0.1:7004@17004 slave 49f97dda545e07e29ab3f56dabd75220b7721219 0 1687695039695 1 connected
ed8902da4e9ee0ef1e13399e9838bf7ac8350c0f 127.0.0.1:7003@17003 master - 0 1687695039081 3 connected 10923-16383
49f97dda545e07e29ab3f56dabd75220b7721219 127.0.0.1:7001@17001 myself,master - 0 1687695039000 1 connected 0-5460
```

# TODO 
- Re-sharding 

# Reference 
- https://redis.io/docs/management/scaling/#create-a-redis-cluster
- https://github.com/redis/redis/tree/unstable/utils/create-cluster
