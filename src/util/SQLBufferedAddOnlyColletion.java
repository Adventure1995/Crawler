/**
 * adventure
 * @time 2016/12/28
 * 
 * @description
 * 		ʵ����AddOnlyColletion�ӿڵ���
 * 		ʹ��SQL��Ϊ����
 * 		�����ڴ���������Ҫ��¼�����
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import exception.SQLInitFailException;

public class SQLBufferedAddOnlyColletion implements AddOnlyColletion {
	
	public SQLBufferedAddOnlyColletion() {
		// TODO Auto-generated constructor stub
		innerSet = new HashSet<>();
		size = 0;
		try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      connection = DriverManager.getConnection(DB_URL, USER, PASS);

		      //STEP 4: Execute a query
		      System.out.println("Creating database...");
		      pds = connection.createStatement();
		} catch (ClassNotFoundException | SQLException exception) {
			//exception.printStackTrace();
			System.out.println("��ʼ��ʧ��,ʹ����ͨ����ģʽ");
		}
	}

	@Override
	public boolean add(Object object) {
		// TODO Auto-generated method stub
		//����һ��URL���뻺��
		//	����ֵ
		//		true����ӳɹ�
		//		false�����ʧ�ܣ��л����¼
		
		try {
			
			if (pds == null || connection == null) throw new SQLInitFailException();
			
			if (checkHasURL((String)object))
				return false;
			else {
				if (innerSet.size() < innerSetSize) {
					innerSet.add((String)object);
					size += 1;
					return true;
				} else {
					if (!hasEleInSQL()) {
						String repalceString = replacePolicy();
						innerSet.remove(repalceString);
						innerSet.add((String)object);
						addEleInSQL(repalceString);
						size += 1;
						return true;
					} else {
						return false;
					}
				}
			}	
		} catch (SQLInitFailException exception) {
			//�������ݿ��ʼ��ʧ�����
			innerSet.add((String)object);
			return true;
		}
	}
	
	protected String replacePolicy() {
		return null;
		
	}
	
	private boolean checkHasURL(String url) {
		if (innerSet.contains(url))
			return true;
		else {
			if (innerSet.size() < innerSetSize) {
				return false;
			} else {
				return false;
			}
		}
	}
	
	private boolean hasEleInSQL() {
		try {
			ResultSet ret = pds.executeQuery("");
			if (!ret.next())
				return false;
			else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	private void addEleInSQL(String element) {
		try {
			pds.executeUpdate("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Object get(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size == 0);
	}
	
	private HashSet<String> innerSet;
	private Connection connection;
	private Statement pds;
	private int innerSetSize;
	
	private int size;
	
	private static final int DEFAULTSIZE = 1000;
	public static final String DB_URL = "jdbc:mysql://127.0.0.1/student";  
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    public static final String USER = "root";
    public static final String PASS = "root";
    public static final String SQLSTAT = "";
    public static final String CHECKSTAT = "";

}
