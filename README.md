# DataMiner部署手册
=================

## 部署环境

###精简配置

| 组件      | 节点1            | 节点2       | 节点3       |
|-----------|------------------|-------------|-------------|
| OS        | Centos7          | Centos7     | Centos7     |
| 主机名    | bda01            | bda02       | bda03       |
| Zookeeper | ●                |             |             |
| Mesos     | Mesos-master     | Mesos-slave | Mesos-slave |
| Spark     | Spark dispatcher |             |             |
| Kafka     | ●                |             |             |
| Mysql     | ●                |             |             |
| Sparta    | ●                |             |             |
| bda-web   | ●                |             |             |
| Hdfs\*    | Namenode         | Datanode    | Datanode    |

\*为可选安装

### 标准配置

| 组件      | 节点1            | 节点2                 | 节点3    | 节点4    | 节点5    |
|-----------|------------------|-----------------------|----------|----------|----------|
| OS        | Centos7          | Centos7               | Centos7  | Centos7  | Centos7  |
| 主机名    | bda01            | bda02                 | bda03    | bda04    | bda05    |
| Zookeeper | ●                | ●                     | ●        |          |          |
| Mesos     | master           | slave                 | slave    | slave    | slave    |
| Spark     | Spark dispatcher |                       |          |          |          |
| Kafka     | ●                | ●                     | ●        |          |          |
| Mysql     | ●                |                       |          |          |          |
| Sparta    | ●                |                       |          |          |          |
| bda-web   | ●                |                       |          |          |          |
| Hdfs\*    | Namenode         | 2rd Namenode Datanode | Datanode | Datanode | Datanode |

\*为可选安装

### 高可用配置

| 组件      | 节点1            | 节点2                | 节点3        | 节点4    | 节点5    | 节点6    | 节点7    |
|-----------|------------------|----------------------|--------------|----------|----------|----------|----------|
| OS        | Centos7          | Centos7              | Centos7      | Centos7  | Centos7  | Centos7  | Centos7  |
| 主机名    | bda01            | bda02                | bda03        | bda04    | bda05    | bda05    | bda05    |
| Zookeeper | ●                | ●                    | ●            |          |          |          |          |
| Mesos     | master           | master slave         | master slave | slave    | slave    | slave    | slave    |
| Spark     | Spark dispatcher |                      |              |          |          |          |          |
| Kafka     | ●                | ●                    | ●            |          |          |          |          |
| Mysql     | ●                | ●                    |              |          |          |          |          |
| Sparta    | ●                | ●                    |              |          |          |          |          |
| bda-web   | ●                | ●                    |              |          |          |          |          |
| Hdfs      | Namenode         | 2rdNamenode Datanode | Datanode     | Datanode | Datanode | Datanode | Datanode |

## 部署步骤

### 操作系统的配置

#### 主机名设置

为各个节点设置主机名

>   hostnamectl set-hostname bda0x

#### /etc/hosts设置

将所有主机名添加到/etc/hosts文件中

#### 安装JAVA环境

>   yum install –y java

设置JAVA_HOME：编辑/etc/profile，在文件最后加入

>   export JAVA_HOME=
>   /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.131-2.b11.el7_3.x86_64/jre
>   \#具体目录根据实际JAVA路径为准

#### 安装目录设置

在所有主机上创建安装目录/root/apps

#### 设置SSH免密码登录

>   设置主节点到其他节点的免密码登陆。首先登录到bda01节点，执行以下命令：

cd \~/.ssh \#若没有该目录，请先执行一次ssh localhost

ssh-keygen -t rsa \#连续三次回车,即生成了公钥和私钥

cat id_rsa.pub >> authorized_keys \# 加入授权

chmod 600 ./authorized_keys \# 修改文件权限

scp ./authorized_keys bda0x:\~/.ssh/ \# 将keys分发到各台主机上

>   测试各个节点，确保bda01与各节点免密登录。

### Zookeeper安装与配置

登录bda01节点，进入安装目录，下载并安装zookeeper，默认使用3.4.6版本的zookeeper。

```
cd /root/apps

# 下载zookeeper 安装包
wget https://mirrors.tuna.tsinghua.edu.cn/apache/zookeeper/zookeeper-3.4.6/zookeeper-3.4.6.tar.gz

tar zxvf zookeeper-3.4.6.tar.gz

ln –s zookeeper-3.4.6 zookeeper
```

#### 配置zookeeper

>cp zookeeper/conf/ zoo_sample.cfg zookeeper/conf/zoo.cfg

#### 启动zookeeper

>zookeeper/bin/zkServer.sh start

### HDFS安装与配置

HDFS的安装是为了在各个组件中共享数据，比如spark的安装包和checkpoint点。默认安装hadoop
2.6.5。

#### 安装HDFS

##### 创建hadoop用户

>useradd -m hadoop -s /bin/bash
>passwd hadoop

##### 添加sudo权限

为hadoop用户添加权限，编辑/etc/sudoers，添加hadoop ALL=(ALL) ALL

![为hadoop增加sudo权限](media/7706f4e1a7fa218c4fdd24f1ac0c4208.png)

##### hadoop用户SSH免密码登录

配置方法为切换到hadoop用户，然后参照设置SSH免密码登录步骤进行配置。

##### 安装hadoop软件包

将hadoop安装到/usr/local目录，
```
cd /root/apps
wget
https://mirrors.tuna.tsinghua.edu.cn/apache/hadoop/common/hadoop-2.6.5/hadoop-2.6.5.tar.gz
tar –zxvf hadoop-2.6.5.tar.gz -C /usr/local \# 解压到/usr/local中
cd /usr/local/
ln -s /usr/local/hadoop-2.6.5 /usr/local/hadoop \# 将文件夹名改为hadoop
chown -R hadoop:hadoop /usr/local/hadoop \# 修改文件权限
```
为Hadoop设置环境变量

在所有的节点中，编辑/etc/profile，追加一下环境变量
```
export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.131-2.b11.el7_3.x86_64/jre
export HADOOP_HOME=/usr/local/hadoop
export HADOOP_INSTALL=\$HADOOP_HOME
export HADOOP_MAPRED_HOME=\$HADOOP_HOME
export HADOOP_COMMON_HOME=\$HADOOP_HOME
export HADOOP_HDFS_HOME=\$HADOOP_HOME
export YARN_HOME=\$HADOOP_HOME
export HADOOP_COMMON_LIB_NATIVE_DIR=\$HADOOP_HOME/lib/native
export PATH=\$PATH:\$HADOOP_HOME/sbin:\$HADOOP_HOME/bin
```
#### 配置HDFS

Hadoop
的配置文件位于 /usr/local/hadoop/etc/hadoop/ 中，分布式需要修改3个配置文件slaves, **core-site.xml** 和 **hdfs-site.xml** 。Hadoop的配置文件是
xml 格式，每个配置以声明 property 的 name 和 value 的方式来实现。

**slaves** 在此文件中添加slaves节点的主机名
```
bda02
bda03
……
```

**core-site.xml**
```xml
<configuration>
<property>
<name>hadoop.tmp.dir</name>
<value>file:///usr/local/hadoop-2.6.5/tmp</value>
<description>Abase for other temporary directories.</description>
</property>
<property>
<name>fs.defaultFS</name>
<value>hdfs://bda9:9000</value>
</property>
</configuration>
```
**hdfs-site.xml**
```xml
<configuration>
<property>
<name>dfs.http.address</name>
<value>bda01:50070</value>
</property>
<property>
<name>dfs.namenode.secondary.http-address</name>
<value>bda01:50090</value>
</property>
<property>
<name>dfs.replication</name>
<value>1</value>
</property>
<property>
<name>dfs.namenode.edits.dir</name>
<value>file:///usr/local/hadoop/data/hdfs/namenode</value>
</property>
<property>
<name>dfs.datanode.data.dir</name>
<value>file:///usr/local/hadoop/data/hdfs/datanode</value>
</property>
</configuration>
```
#### 分发HDFS

将/usr/local/hadoop文件拷贝到其他节点的相同目录中。

#### 启动hdfs

NameNode 的格式化 bin/hdfs namenode -format，成功的话，会看到 “successfully
formatted” 和 “Exitting with status 0” 的提示，若为 “Exitting with status 1”
则是出错。

接着开启 NaneNode 和 DataNode 守护进程：sbin/start-dfs.sh，启动完成后，可以通过命令 jps 来判断是否成功启动，若成功启动则会列出如下进程:
“NameNode”、”DataNode”和SecondaryNameNode（如果 SecondaryNameNode
没有启动，请运行 sbin/stop-dfs.sh 关闭进程，然后再次尝试启动尝试）。

#### 验证HDFS

成功启动后，可以访问 Web
界面 [http://localhost:50070](http://localhost:50070/) 查看 NameNode 和 Datanode
信息，还可以在线查看 HDFS 中的文件。也可以通过命令查询hdfs目录hadoop fs -ls /

### Mesos安装与配置

#### 安装Mesos

每个节点上执行安装命令
```
rpm -Uvh http://repos.mesosphere.io/el/7/noarch/RPMS/mesosphere-el-repo-7-1.noarch.rpm
yum -y install mesos
```
#### 配置Mesos-Master
```
echo zk://bda01:2181/mesos > /etc/mesos/zk
echo 'bigdata-analysis' > /etc/mesos/cluster
echo 1 /etc/mesos-master/quorum #在里面添加一个数字，数字大小不小于master的数量除以2，这里精简配置里只有一个master，填1就可以
echo ` hostname -i` > /etc/mesos-master/ip        #添加master的ip，只做显示用
echo `hostname` > /etc/mesos-master/hostname      #添加master的主机名
echo `/root/apps/spark` > /etc/mesos-master/spark_home
# mesos-master节点需要停止slave服务并关闭开机启动
systemctl stop mesos-slave.service
systemctl disable mesos-slave.service
systemctl restart mesos-master.service
```
#### 配置Mesos-Slave
```
echo 'bigdata-analysis' > /etc/mesos/cluster
echo zk://bda01:2181/mesos > /etc/mesos/zk
echo 'mesos' > /etc/mesos-slave/containerizers  
echo '5mins' > /etc/mesos-slave/executor_registration_timeout
echo '/var/lib/mesos' > /etc/mesos-slave/work_dir
echo `hostname` /etc/mesos-slave/hostname
echo `/root/apps/spark` > /etc/mesos-slave/spark_home
# mesos-slave节点需要停止master服务并关闭开机启动
systemctl stop mesos-master.service
systemctl disable mesos-master.service
systemctl restart mesos-slave.service
```
#### 验证Mesos服务

通过浏览器登录<http://bda01:5050/>

### Kafka安装与配置

#### 安装kafka
```
cd /root/apps
wget http://mirrors.tuna.tsinghua.edu.cn/apache/kafka/0.10.2.1/kafka_2.11-0.10.2.1.tgz
tar zxvf kafka_2.11-0.10.2.1.tgz
ln –s kafka_2.11-0.10.2.1 kafka
```
#### 配置kafka

为kafka配置zookeeper，修改kafka配置文件
conf/server.properties中zookeeper.connect配置项，设置为当前集群的Zookeeper。

>zookeeper.connect=bda01:2181/kafka

#### 验证kafka

### MySQL安装与配置

#### 安装MySQL

>yum install –y mariadb-server

#### 配置MySQL

设置mysql root用户密码以及远程登录。root用户默认密码为123456
```
mysql -u root
mysql>use mysql;
mysql>update user set password=PASSWORD('123456') where User='root';
mysql>update user set host = '%' where user = 'root';
mysql>exit
```

#### 验证MySQL

登录mysql
```
mysql -u root –p
Enter password:
Welcome to the MariaDB monitor. Commands end with ; or \\g.
Your MariaDB connection id is 344
Server version: 5.5.52-MariaDB MariaDB Server
Copyright (c) 2000, 2016, Oracle, MariaDB Corporation Ab and others.
Type 'help;' or '\\h' for help. Type '\\c' to clear the current input statement.
MariaDB [(none)]>
```
### Spark安装与配置

#### 安装spark

在bda01节点上安装spark
```
cd /root/apps
wget https://d3kbcqa49mib13.cloudfront.net/spark-2.1.0-bin-hadoop2.6.tgz
tar zxvf spark-2.1.0-bin-hadoop2.6.tgz
ln -s spark-2.1.0-bin-hadoop2.6 spark
```
#### 配置spark

设置spark与mesos联动，需要将spark安装包上传到hadoop中，上传位置为/user/root/spark-2.1.0-bin-hadoop2.6.tgz。

编辑spark配置文件spark-defaults.conf和spark-env.sh

spark-defaults.conf

>spark.executor.uri hdfs://bda01:9000/user/root/spark-2.1.0-bin-hadoop2.6.tgz
>spark.master mesos://bda01:5050

spark-env.sh

>export MESOS_NATIVE_JAVA_LIBRARY=/usr/local/lib/libmesos.so
>export MASTER=mesos://zk://bda01:2181/mesos
>export JAVA_HOME=\$(readlink -f /usr/bin/java \| sed "s:bin/java::")

执行命令sbin/start-mesos-dispatcher.sh --master mesos://bda01:5050启动spark

#### 验证spark

执行bin/spark-shell
命令，等待启动完成后，登录<http://bda01:5050>查看spark任务是否启动。

### Sparta安装与配置
