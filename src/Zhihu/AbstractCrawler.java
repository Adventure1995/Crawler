package Zhihu;
/**
 * @author adventure
 * @time 2016.12.25
 * 
 * @description
 * 	����������
 * 		��Ҫ���������󷽷�
 * 			Object setCrawlerPolicy(String page)
 * 				����ΪĿ��ҳ��
 * 				���ش�Ŀ��ҳ���õĽ������
 * 			void storeResPolicy(Object result)
 * 				����Ϊ��ҳ���õĽ������
 *		ʵ������
 *			void run()
 *				�޲���������һ���߳�ִ���������
 *		ʵ������
 *			generator pageGenerator
 *				ҳ������������������ҳ����߼� 
 */

public abstract class AbstractCrawler {
	
	public AbstractCrawler() {
		// TODO Auto-generated constructor stub
		
	}
	
	public AbstractCrawler(Generator pageGenerator) {
		this();
		this.pageGenerator = pageGenerator;
	}
	
	public void run() {
		
		if (pageGenerator == null) {
			System.out.println("û����ƥ���ҳ����������˳�");
		} else {
			while (pageGenerator.hasNext()) {
				String page = pageGenerator.next();
				Object result;
				result = setCrawlerPolicy(page);
				storeResPolicy(result);
			}
		}
		
	}
	
	//ץȡ����Ϊץȡ�ṩ�����Է���
	public abstract Object setCrawlerPolicy(String page);
	
	//���ý���������
	public abstract void storeResPolicy(Object result);
	
	//����ҳ��������
	public void setPageGenerator(Generator generator) {
		pageGenerator = generator;
	}	
	
	private Generator pageGenerator;

}
