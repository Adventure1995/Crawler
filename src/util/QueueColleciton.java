/**
 * @author adventure
 * @time 2016/12/25
 * 
 * @description
 * 		�Զ�����ʽ�������ɾ���ļ��Ͻӿ�
 */
package util;

public interface QueueColleciton {
	
	public boolean add(Object object);
	
	public Object peek();
	
	public Object poll();
	
	public boolean isEmpty();
	
	public int size();

}
