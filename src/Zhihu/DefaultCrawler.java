/**
 * @author adventure
 * @time 2016/12/28
 * @description ����ȡ֪�������û�����Ϊ���ӣ��̳�AbstractCrawler
 * 
 */
package Zhihu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.QueueColleciton;
import util.URLManager;
import util.URLQueueManager;

public class DefaultCrawler extends AbstractCrawler {
	
	public DefaultCrawler() {
		// TODO Auto-generated constructor stub
		resultList = new ArrayList<>();
		//setPageGenerator(new PageGenerator());
	}
	
	public DefaultCrawler(String startURL) {
		this();
		setStartURL(startURL);
		setPageGenerator(new PageGenerator());
	}
	
	public void setStartURL(String startURL) {
		this.startURL = startURL;
	}
	
	public void setPageRegex(String pageRegex) {
		this.pageRegex = pageRegex;
	}

	@Override
	public Object setCrawlerPolicy(String page, List<String> resultRegexList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeResPolicy(Object result) {
		// TODO Auto-generated method stub
		resultList.add(result);
	}
	
	private class PageGenerator implements Generator {
		
		public PageGenerator() {
			// TODO Auto-generated constructor stub
			urlPool = new URLQueueManager();
			urlPool.add(startURL);
			urlManager = URLManager.getInstance();
			urlManager.add(startURL);
			
		}

		@Override
		public String next() {
			// TODO Auto-generated method stub
			String url = (String) urlPool.poll();
			String page;
			if (url == null) 
				return null;
			else {
				page = getPage(url);
				List<String> AllPageURL = getAllPageURL(page, pageRegex);
				for (String string : AllPageURL) {
					if (urlManager.add(string)) {
						urlPool.add(string);
					}
				}
				
				return page;
			}
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return !urlPool.isEmpty();
		}
		
		private String getPage(String uri) {
			String result = "";
			URL url = null;
			BufferedReader in = null;
			try {
				url = new URL(uri);
				
				in = null;
				URLConnection connection = url.openConnection();
				connection.connect();
				in = new BufferedReader(new InputStreamReader(  
	                    connection.getInputStream(), "UTF-8"));  
	            // ������ʱ�洢ץȡ����ÿһ�е�����  
	            String line;
	            while ((line = in.readLine()) != null) {  
	                // ����ץȡ����ÿһ�в�����洢��result����  
	                result += line;  
	            }  
	        } catch (Exception e) {  
	            System.out.println("����GET��������쳣��" + e);  
	            e.printStackTrace();  
	        }  
	        // ʹ��finally���ر�������  
	        finally {  
	            try {  
	                if (in != null) {  
	                    in.close();  
	                }  
	            } catch (Exception e2) {  
	                e2.printStackTrace();  
	            }  
	        }
	        return result; 
		}
		
		private List<String> getAllPageURL(String page, String regex) {
			//return new ArrayList<String>();
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(page);
			List<String> list = new ArrayList<>();
			URL tmpUrl;
			try {
				tmpUrl = new URL(startURL);
				String protocol = tmpUrl.getProtocol();
				String host = tmpUrl.getHost();
				boolean bool = matcher.find();
				while (bool) {
					String result = matcher.group(1);
					//String next = "https://" + (new URL(startURL)).getHost() + matcher.group(1) + "/hot";
					String next = protocol + "://" + host + matcher.group(1) + "/following";
					list.add(next);
					bool = matcher.find();
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			return list;
		}
		
		private URLManager urlManager;
		private QueueColleciton urlPool;
		
	}
	
	private String startURL;
	private String pageRegex;
	
	private List<Object> resultList;

}
