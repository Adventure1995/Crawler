package Zhihu;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCrawler {
	
	public AbstractCrawler() {
		// TODO Auto-generated constructor stub
		resultRegexList = new ArrayList<>();
		
	}
	
	public AbstractCrawler(Generator pageGenerator) {
		this();
		this.pageGenerator = pageGenerator;
	}
	
	public void run() {
		
		if (pageGenerator == null) {
			System.out.println("û��2ƥ���ҳ����������˳�");
		} else {
			while (pageGenerator.hasNext()) {
				String page = pageGenerator.next();
				Object result;
				result = setCrawlerPolicy(page, resultRegexList);
				storeResPolicy(result);
			}
		}
		
	}
	
	public void setResultFilter(List<String> rList) {
		resultRegexList = rList;
	}
	
	//ץȡ����Ϊץȡ�ṩ�����Է���
	public abstract Object setCrawlerPolicy(String page, List<String> resultRegexList);
	
	//���ý���������
	public abstract void storeResPolicy(Object result);
	
	//����ҳ��������
	public void setPageGenerator(Generator generator) {
		pageGenerator = generator;
	}	
	
	private List<String> resultRegexList;
	private Generator pageGenerator;

}
