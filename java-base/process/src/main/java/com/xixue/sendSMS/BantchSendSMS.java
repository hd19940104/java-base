package com.xixue.sendSMS;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.xixue.entity.UserEntity;
import com.xixue.process.ThreadDemoRunable;


class UserThread extends Thread{
	private static Logger logger = Logger.getLogger(UserThread.class);
	 private List<UserEntity> listUser;
	 public UserThread(List<UserEntity> list) {
		 this.listUser = list;
	 }
	@Override
	public void run() {
		for (UserEntity userEntity : listUser) {
			logger.info("["+getName()+"]"+userEntity.toString());
			
		}
	}
}
/**
 * 批量发送短信
 * @author houdo
 *
 */
public class BantchSendSMS {
	private static Logger logger = Logger.getLogger(BantchSendSMS.class);
	public static void main(String[] args) {
		//初始化用户
		List<UserEntity> setUserList = setUserList(10000000);
		//计算线程数
		List<List<UserEntity>> splitList = splitList(setUserList, 10000);
		//开始进行异步处理数据
		for (List<UserEntity> list : splitList) {
			UserThread thread  = new UserThread(list);
			thread.start();
		}
		
	}
	/**
	 * 创建用户
	 * @return
	 */
	public static List<UserEntity>  setUserList(int num) {
		List<UserEntity> listUser = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			listUser.add(new UserEntity("userId:"+i,"userName:"+i));
		}
		return listUser;
				 
		
	}
	
	
	/**
	 *  功能描述:(list 集合分批切割)
	 * @param list
	 * @param pageSize   分页长度
	 * @return
	 */
	static public <T> List<List<T>> splitList(List<T> list, int pageSize) {
		int listSize = list.size();
		int page = (listSize + (pageSize - 1)) / pageSize; //页数
		List<List<T>> listArray = new ArrayList<List<T>>();
		for (int i = 0; i < page; i++) {
			List<T> subList = new ArrayList<T>();
			for (int j = 0; j < listSize; j++) {
				int pageIndex = ((j + 1) + (pageSize - 1)) / pageSize;
				if (pageIndex == (i + 1)) {
					subList.add(list.get(j));
				}
				if ((j + 1) == ((j + 1) * pageSize)) {
					break;
				}
			}
			listArray.add(subList);
		}
		logger.info("切割完成");
		return listArray;
	}
	
}
