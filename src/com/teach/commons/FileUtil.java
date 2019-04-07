package com.teach.commons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

/**
 * 文件操作相关工具类
 * @author J.L.Zhou
 *
 */
public class FileUtil {
	
	/**
	 * 缓冲大小
	 */
	public final static int BUFFERD_MAX = 1024;
	
	/**
	 * 默认字符编码
	 */
	public final static String CHARSET = "UTF-8";
	
	/**
	 * 回车换号父
	 */
	public final static String LN = "\n";
	
	private FileUtil(){}

	/**
	 * 文件拷贝
	 * @param sourceFile 要拷贝的源文件对象
	 * @param targetFile 拷贝后的目标文件对象
	 * @throws Exception 
	 */
	public static void copy(File sourceFile,File targetFile)throws IOException{
		copy(new FileInputStream(sourceFile), new FileOutputStream(targetFile));
	}
	
	/**
	 * 将输入流读取后写入输出流中
	 * @param in
	 * @param out
	 * @throws Exception
	 */
	public static void copy(InputStream in,OutputStream out)throws IOException {
		try{
			byte[] bs = new byte[BUFFERD_MAX];
			int len;
			while((len = in.read(bs))>-1){
				out.write(bs,0,len);
			}
			out.flush();
		}finally{
			try{in.close();}catch(Exception ex){}
			try{out.close();}catch(Exception ex){}
		}
	}
	


	/**
	 * 获取文件大小描叙
	 * 
	 * @param size
	 * @return
	 */
	public static String getSizeDescribe(long size) {
		try {
			if (size < 1024l) {
				return size + " bytes";
			} else if (size < 1048576l) {
				return (Math.round(((size * 10) / 1024)) / 10) + " KB";
			} else if (size < 1073741824l) {
				return (Math.round(((size * 10) / 1048576)) / 10) + " MB";
			} else if (size < 1099511627776l) {
				return (Math.round(((size * 10) / 1073741824)) / 10) + " GB";
			} else {
				return (Math.round(((size * 10) / 1099511627776l)) / 10)
						+ " TB";
			}
		} catch (Exception ex) {
			return Long.toString(size);
		}
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileExt(String fileName) {
		try {
			return fileName.substring(fileName.lastIndexOf(".") + 1)
					.toLowerCase();
		} catch (Exception ex) {
			return "unknow";
		}
	}
	
	public static void writeText(String text,File file)throws IOException{
		writeText(text, file, CHARSET);
	}
	public static void writeText(String text,File file,String charset)throws IOException{
		writeText(text, new FileOutputStream(file), charset);
	}
	
	public static void writeText(String text,OutputStream out)throws IOException{
		writeText(text, out, CHARSET);
	}
	
	public static void writeText(String text,OutputStream out,String charset)throws IOException{
		Writer w = null;
		try{
			w = new OutputStreamWriter(out, "UTF-8");
			w.write(text);
			w.flush();
		}finally{
			try{w.close();}catch(Exception ex){}
		}
	}
	
	
	public static String readText(File file)throws IOException{
		return readText(file, CHARSET);
	}
	public static String readText(File file,String charset)throws IOException{
		return readText(new FileInputStream(file), charset);
	}
	
	public static String readText(InputStream in)throws IOException{
		return readText(in, CHARSET);
	}
	
	/**
	 * 从流in中读取字符编码为charset的所有字符串
	 * @param in
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public static String readText(InputStream in,String charset)throws IOException{
		final StringBuffer sb = new StringBuffer();
		readText(in, charset, new LineReadListener() {
			@Override
			public void readLine(String line) {sb.append(line);sb.append(LN);}
		});
		return sb.toString();
	}
	
	public static void readText(File file,LineReadListener listener)throws IOException{
		readText(file, CHARSET, listener);
	}
	
	public static void readText(File file,String charset,LineReadListener listener)throws IOException{
		readText(new FileInputStream(file), charset, listener);
	}
	
	public static void readText(InputStream in,LineReadListener listener)throws IOException{
		readText(in, CHARSET, listener);
	}
	
	/**
	 * 从流in中按字符编码charset读取字符，每读取一行执行一次listener
	 * @param in
	 * @param charset
	 * @param listener
	 * @throws IOException
	 */
	public static void readText(InputStream in,String charset,LineReadListener listener)throws IOException{
		Reader r = null;
		BufferedReader br = null;
		try{
			r = new InputStreamReader(in,charset);
			br = new BufferedReader(r);
			String line = null;
			while((line = br.readLine())!=null){
				listener.readLine(line);
			}
		}finally{
			try{br.close();}catch(Exception ex){}
			try{r.close();}catch(Exception ex){}
			try{in.close();}catch(Exception ex){}
		}
	}
	
	
	/**
	 * 行读取监听器接口
	 * @author J.L.Zhou
	 *
	 */
	@FunctionalInterface
	interface LineReadListener {
		
		/**
		 * 读取一行回调方法
		 * @param line
		 */
		void readLine(String line);
	}
}
