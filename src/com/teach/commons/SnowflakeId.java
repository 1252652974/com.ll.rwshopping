package com.teach.commons;

import java.text.SimpleDateFormat;

/**
 * 分布式Id算法
 * 雪花数
 * Twitter_Snowflake<br>
 * SnowFlake的结构如下(每部分用-分开):<br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0<br>
 * 41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)
 * 得到的值），这里的的开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的（如下下面程序IdWorker类的startTime属性）。41位的时间截，可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69<br>
 * 10位的数据机器位，可以部署在1024个节点，包括5位datacenterId和5位workerId<br>
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号<br>
 * 加起来刚好64位，为一个Long型。<br>
 * SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)，并且效率较高，经测试，SnowFlake每秒能够产生26万ID左右。
 * @author J.L.Zhou
 *
 */
public class SnowflakeId {
	
	
	/**
	 * 开始的时间戳
	 */
	private static long TIME_START;
	
	
	/**
	 * 当前时间戳
	 */
	private long time;
	
	/**
	 * 上一次获取的时间戳
	 */
	private long timeOld;
	
	/**
	 * 数据中心编号
	 */
	private long centerId;
	
	/**
	 * 工作节点编号
	 */
	private long workerId;
	
	/**
	 * 毫秒内的计数器
	 */
	private long index = 0;
	
	static{
		try{
			TIME_START = new SimpleDateFormat("yyyy-MM-dd").parse("2018-09-1").getTime();
		}catch(Exception ex){
			ex.printStackTrace();
			System.exit(0);
		}
	}
	
	

	/**
	 * 
	 * @param centerId 数据中心编号
	 * @param workerId 工作节点编号
	 */
	public SnowflakeId(long centerId, long workerId) {
		super();
		if(centerId>=32){
			throw new RuntimeException("数据中心编号不能大于32");
		}
		if(workerId>=32){
			throw new RuntimeException("工作节点编号不能大于32");
		}
		this.centerId = centerId;
		this.workerId = workerId;
	}
	
	
	
	
	
	public long nextId(){
		time = System.currentTimeMillis();
		if(time==timeOld){
			++index;
			if(index>=4096){
				time = getNextTime();
				index = 0L;
			}
			
		}else{
			index = 0L;
		}
		timeOld = time;
		
		//1 41 5 5 12

//		System.out.println(Long.toBinaryString((time-TIME_START)));
//		System.out.println(Long.toBinaryString((time-TIME_START)<<22));
//		System.out.println(Long.toBinaryString(centerId));
//		System.out.println(Long.toBinaryString(centerId<<17));
		return (time-TIME_START)<<22 | centerId<<17 | workerId<<12 | index;
	}
	
	/**
	 * 获取下一毫秒，（本毫秒内计数器已经用完了）
	 * @return
	 */
	public synchronized long getNextTime(){
		while(time==System.currentTimeMillis()){
		}
		return System.currentTimeMillis();
	}
	
	private static SnowflakeId id = new SnowflakeId(0, 0);
	
	/**
	 * 获取0号机房第0台电脑的雪花数生产对象来生产ID
	 * @return
	 */
	public static long next(){
		return id.nextId();
	}
	
	public static void main(String[] args) {
		SnowflakeId id = new SnowflakeId(1,2);
		long time = System.currentTimeMillis();
		for(int i=0;i<1;i++){
			long l = id.nextId();
			System.out.println(l);
			System.out.println(Long.toBinaryString(l)+"\t"+l);
		}
		System.out.println(System.currentTimeMillis()-time);
		System.out.println("11000111101101011110001001".length());
	}
}
