package com.qkzz.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

/**
 *
 * @author ibm
 */
public class OperationFile {

    public OperationFile() {
    }
    
    /**获得控制台用户输入的信息
     * 可以返回用户输入的信息，不足之处在于不支持中文输入
     * @return
     * @throws IOException
     */
    public static String getInputMessage() throws IOException{
        System.out.println("请输入您的命令∶");
        byte buffer[]=new byte[1024];
        int count=System.in.read(buffer);
        char[] ch=new char[count-2];//最后两位为结束符，删去不要
        for(int i=0;i<count-2;i++)
            ch[i]=(char)buffer[i];
        String str=new String(ch);
        return str;
    }
    
    /**以文件流的方式复制文件
     * 该方法经过测试，支持中文处理，并且可以复制多种类型，比如txt，xml，jpg，doc等多种格式
     * @param src 文件源目录
     * @param dest 文件目的目录
     * @throws IOException  
     */
    public static void copyFile(String src,String dest) throws IOException{
        FileInputStream in=new FileInputStream(src);
        File file=new File(dest);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file);
        int c;
        byte buffer[]=new byte[1024];
        while((c=in.read(buffer))!=-1){
            for(int i=0;i<c;i++)
                out.write(buffer[i]);        
        }
        in.close();
        out.close();
    }
    
    /**
     * 文件输出示例
     */
    public static void PrintStreamDemo(){
        try{
            FileOutputStream out=new FileOutputStream("D:/test.txt");
            PrintStream p=new PrintStream(out);
            for(int i=0;i<10;i++)
                p.println("This is "+i+" line");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    
    /**
     * 文件输出示例
     */
    public static void StringBufferDemo() throws IOException{
        File file=new File("/root/sms.log");
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true);
        for(int i=0;i<10000;i++){
            StringBuffer sb=new StringBuffer();
            sb.append("这是第"+i+"行:前面介绍的各种方法都不关用,为什么总是奇怪的问题 ");
            out.write(sb.toString().getBytes("utf-8"));
        }        
        out.close();
    }
    
    /**文件重命名
     * @param path 文件目录
     * @param oldname  原来的文件名
     * @param newname 新文件名
     */
    public static void renameFile(String path,String oldname,String newname){
        if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名
            File oldfile=new File(path+"/"+oldname);
            File newfile=new File(path+"/"+newname);
            if(newfile.exists()){//若在该目录下已经有一个文件和新文件名相同，则不允许重命名
                System.out.println(newname+"已经存在！");
            }else{
                oldfile.renameTo(newfile);
            }
        }
    }
    
    /**转移文件目录
     * 转移文件目录不等同于复制文件，复制文件是复制后两个目录都存在该文件，而转移文件目录则是转移后，只有新目录中存在该文件。
     * @param filename 文件名
     * @param oldpath 旧目录
     * @param newpath 新目录
     * @param cover 若新目录下存在和转移文件具有相同文件名的文件时，是否覆盖新目录下文件，cover=true将会覆盖原文件，否则不操作
     */
    public static void changeDirectory(String filename,String oldpath,String newpath,boolean cover){
        if(!oldpath.equals(newpath)){
            File oldfile=new File(oldpath+"/"+filename);
            File newfile=new File(newpath+"/"+filename);
            if(newfile.exists()){//若在待转移目录下，已经存在待转移文件
                if(cover)//覆盖
                    oldfile.renameTo(newfile);
                else
                    System.out.println("在新目录下已经存在："+filename);
            }else{
                oldfile.renameTo(newfile);
            }
        }       
    }
    
    /**读文件
     * @param path
     * @return
     * @throws IOException
     */
    public static String FileInputStreamDemo(String path) throws IOException{
        File file=new File(path);
        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();
        FileInputStream fis=new FileInputStream(file);
        byte[] buf = new byte[1024];
        StringBuffer sb=new StringBuffer();
        while((fis.read(buf))!=-1){
            sb.append(new String(buf));    
            buf=new byte[1024];//重新生成，避免和上次读取的数据重复
        }
        return sb.toString();
    }
    
    /**读文件
     * 在IO操作，利用BufferedReader和BufferedWriter效率会更高一点
     * @param path
     * @return
     * @throws IOException
     */
    public static String BufferedReaderDemo(String path) throws IOException{
        File file=new File(path);
        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();
        BufferedReader br=new BufferedReader(new FileReader(file));
        String temp=null;
        StringBuffer sb=new StringBuffer();
        temp=br.readLine();
        while(temp!=null){
            sb.append(temp+" ");
            temp=br.readLine();
        }
        return sb.toString();
    }
	
	/**
	 * 写入文本文件
	 * @param filepath
	 * @param fileName
	 * @param content
	 */
	public static void write(String filepath, String fileName, String content) {
		try {
			FileOutputStream fos = new FileOutputStream(filepath + fileName);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			osw.write(content);
			osw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取文本文件
	 * @param filepath
	 * @throws Exception
	 */
	public static String read(String filepath,String encode) {
		String line = "";
		try{
			File file = new File(filepath);
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), encode);
			BufferedReader reader = new BufferedReader(read);
			while (reader.ready()) {
				line = reader.readLine();
				System.out.println("line:"+line);
			}
			read.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return line;
	}
	
    
    /**创建文件夹
     * @param path  目录
     */
    public static void createDir(String path){
        File dir=new File(path);
        if(!dir.exists())
            dir.mkdir();
    }
    
    /**创建新文件
     * @param path 目录
     * @param filename 文件名
     * @throws IOException
     */
    public static void createFile(String path,String filename) throws IOException{
        File file=new File(path+"/"+filename);
        if(!file.exists())
            file.createNewFile();
    }
    
    /**删除文件
     * @param path 目录
     * @param filename 文件名
     */
    public static void delFile(String path,String filename){
        File file=new File(path+"/"+filename);
        if(file.exists() && file.isFile())
            file.delete();
    }
    
    /**递归删除文件夹
     * 要利用File类的delete()方法删除目录时，必须保证该目录下没有文件或者子目录，否则删除失败，
     * 因此在实际应用中，我们要删除目录，必须利用递归删除该目录下的所有子目录和文件，然后再删除该目录。
     * @param path
     */
    public static void delDir(String path){
        File dir=new File(path);
        if(dir.exists()){
            File[] tmp=dir.listFiles();
            for(int i=0;i<tmp.length;i++){
                if(tmp[i].isDirectory()){
                    delDir(path+"/"+tmp[i].getName());
                }else{
                    //这里用return,表示只删除空目录
                    continue ;
                    //如果用tmp[i].delete()，表示递归删除目录及目录下的所有文件
                    //tmp[i].delete();
                }
            }
            dir.delete();
        }
    }
    
    public static void main(String [] args){
        OperationFile.delDir("d:/abc");
        System.out.println("............");
    }

}
