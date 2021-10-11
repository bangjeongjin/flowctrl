package com.flow.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardDownload
 */
@WebServlet("/BoardDownloadServlet")
public class BoardDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("application/download;charset=UTF-8");
    	response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
    	//file download
    	String file_name = request.getParameter("file_name");
    	String upDir = "C:/upload_files";   	
    	String path = upDir +"/"+ file_name;
    	
    	File file = new File(path);
    	OutputStream out= response.getOutputStream();
    	String userAgent = request.getHeader("User-Agent");
    	boolean ie = userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("rv:11") > -1;
    	
    	String fileDownName = null;
    	
    	if(ie) {
    		fileDownName = URLEncoder.encode(file.getName(), "utf-8");
    	}else {
    		fileDownName = new String(file_name.getBytes("EUC-KR"),"8859_1");
		}
    	
    	response.setHeader("Content-Disposition",  "attachment; filename=\"" + fileDownName + "\";");
    	response.setHeader("Content-Transfer-Encoding", "binary");    	
    	
    	FileInputStream fin = null;
        FileChannel inputChannel = null;
        WritableByteChannel outputChannel = null;
        
        try {
            fin = new FileInputStream(file);
            inputChannel = fin.getChannel();
            outputChannel = Channels.newChannel(response.getOutputStream());
            inputChannel.transferTo(0, fin.available(), outputChannel);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            try {
                if (outputChannel.isOpen())
                    outputChannel.close();
            } catch (Exception e) {
            	
            }
            try {
                if (inputChannel.isOpen())
                    inputChannel.close();
            } catch (Exception e) {
            	
            }
            try {
                if (fin != null)
                    fin.close();
            } catch (Exception e) {
            	
            }
        }		
}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
