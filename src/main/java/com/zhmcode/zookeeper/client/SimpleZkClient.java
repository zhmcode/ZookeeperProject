package com.zhmcode.zookeeper.client;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by zhm on 2017/12/19.
 */
public class SimpleZkClient {

	private static final String connectString="192.168.133.11:2181,192.168.133.12:2181,192.168.133.13:2181";
	private static final int sessionTimeout = 2000;
	ZooKeeper zkClient = null;
    Watcher wh = new Watcher() {
		public void process(WatchedEvent watchedEvent) {
			System.out.println(watchedEvent.toString());
		}
	};
	/**
	 * 初始化zookeeper实例
	 */
	@Before
	public void init() throws Exception{
		zkClient = new ZooKeeper(connectString,SimpleZkClient.sessionTimeout,wh);
	}
	/**
	 * 创建节点
	 */
	@Test
	public void createNodeTest() throws  Exception{
		zkClient.create("/z200","i going done !!!".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		zkClient.close();
	}
	/**
	 * 创建节点
	 */
	@Test
	public void getNodeTest() throws  Exception{
		byte[] data = zkClient.getData("/z200", false, null);
		String s = new String(data);
		System.out.println(s);
		zkClient.close();
	}

	/**
	 * 设置数据
	 */
	@Test
	public void setNodeTest() throws Exception{
		zkClient.setData("/z200","i hate you very much !!!!".getBytes(),-1);
		zkClient.close();
	}

	/**
	 * 删除节点
	 * @throws Exception
	 */
	@Test
	public void deleteNodeTest() throws Exception{
		zkClient.delete("/z200",-1);
		zkClient.close();
	}

	/**
	 * 是否存在节点
	 */
	@Test
	public void isExistTest() throws  Exception{
		Stat exists = zkClient.exists("/z200", false);
		zkClient.close();
	}

	@Test
	public void getchildrenTest() throws  Exception{
		List<String> children = zkClient.getChildren("/", null);
		for(String item : children){
			System.out.println(item);
		}
	}
}
