/**
 * adventure
 * @time 2016/12/28
 * 
 * @description
 * 		ʵ����QueueCollection�ӿڵ���
 * 		ʹ��SQL��Ϊ����
 * 		�����ڴ���������Ҫ��¼�����
 */
package util;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SQLBufferedQueueCollection implements QueueColleciton {

	public SQLBufferedQueueCollection() {
		// TODO Auto-generated constructor stub
		result = new LinkedList<String>();
	}
	
	/* (non-Javadoc)
	 * @see util.QueueColleciton#add(java.lang.Object)
	 */
	@Override
	public boolean add(Object object) {
		// TODO Auto-generated method stub
		return result.offer((String) object);
	}

	/* (non-Javadoc)
	 * @see util.QueueColleciton#peek()
	 */
	@Override
	public Object peek() {
		// TODO Auto-generated method stub
		return result.peek();
	}

	/* (non-Javadoc)
	 * @see util.QueueColleciton#poll()
	 */
	@Override
	public Object poll() {
		// TODO Auto-generated method stub
		return result.poll();
	}
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return result.isEmpty();
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return result.size();
	}
	
	Queue<String> result;

}
