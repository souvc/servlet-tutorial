package com.souvc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试连接数据库
 */
//@WebServlet("/DatabaseAccess")
public class DatabaseAccess extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	// JDBC 驱动名及数据库 URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/test";
	
	// 数据库的用户名与密码，需要根据自己的设置
	static final String USER = "root";
	static final String PASS = "123456"; 

	public DatabaseAccess() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Connection conn = null;
		Statement stmt = null;
		
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String title = "Servlet Mysql 测试 - 教程";
		String docType = "<!DOCTYPE html>\n";
		out.println(docType +
		"<html>\n" +
		"<head><title>" + title + "</title></head>\n" +
		"<body bgcolor=\"#f0f0f0\">\n" +
		"<h1 align=\"center\">" + title + "</h1>\n");
		try{
			// 注册 JDBC 驱动器
			Class.forName("com.mysql.jdbc.Driver");
			
			// 打开一个连接
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			// 执行 SQL 查询
			stmt = conn.createStatement();
			String sql = "SELECT id, name, url FROM websites";
			ResultSet rs = stmt.executeQuery(sql);

			// 展开结果集数据库
			while(rs.next()){
				
				// 通过字段检索
				int id  = rs.getInt("id");
				String name = rs.getString("name");
				String url = rs.getString("url");
	
				// 输出数据
				out.println("ID: " + id);
				out.println(", 站点名称: " + name);
				out.println(", 站点 URL: " + url);
				out.println("<br />");
			}
			out.println("</body></html>");

			// 完成后关闭
			rs.close();
			stmt.close();
			conn.close();
		} catch(SQLException se) {
			// 处理 JDBC 错误
			se.printStackTrace();
		} catch(Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		}finally{
			// 最后是用于关闭资源的块
			try{
				if(stmt!=null)
				stmt.close();
			}catch(SQLException se2){
			}
			try{
				if(conn!=null)
				conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
	   
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}