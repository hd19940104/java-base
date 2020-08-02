package com.hadoop.hdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

/**
 * 描述：文件的上传、下载
 * @author 候东
 *
 */
public class IOToHDFS
{
   //获取配置信息
    static Configuration configuration = new Configuration();
    /**
     * 描述：获取连接
     * @throws URISyntaxException 
     * @throws InterruptedException 
     * @throws IOException 
     */
    public static FileSystem getFileSystemCon() 
    {
        
        //获取文件系统
        //     FileSystem fs = FileSystem.get(configuration);
        FileSystem fs = null;
        try
        {
            fs = FileSystem.get(new URI("hdfs://hadoop108:8020"), configuration, "hadoop");
        }
        catch (IOException | InterruptedException | URISyntaxException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if ( fs != null )
        {
            System.out.println("文件系统连接成功...");
        }
        else
        {
            System.out.println("文件系统连接失败...");
        }
        
        return fs;
        
    }
    /**
     * 描述：文件上传IO流方式
     * @throws IOException 
     * @throws IllegalArgumentException 
     */
    @SuppressWarnings("resource")
    @Test
    public void putFileToHDFS()
    {
        
        FileSystem fs =null;
        //获取文件系统
        fs = getFileSystemCon();
        //获取输出流
        try
        {
            FSDataOutputStream outputStream = fs.create(new Path("/hadoop/data0/xiyou3.txt"));
            //获取输入流
            FileInputStream inputStream = new FileInputStream(new File("D:/work/workplace/hadoop/xiyou1.txt"));
            //流对接
            IOUtils.copyBytes(inputStream, outputStream,configuration);
            System.out.println("success");
        }
        catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            
            if ( fs!=null )
            {
                try
                {
                    fs.close();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
        
        
        
        
    }
    
    /*
     * 描述:下载
     * 
     */
    @Test
    public void getFileHDFS(){

        
        FileSystem fs =null;
        //获取文件系统
        fs = getFileSystemCon();
        //获取输出流
        try
        {
//            FSDataOutputStream outputStream = fs.create(new Path("/user/hadoop/input/xiyou1.txt"));
            FileOutputStream outputStream = new FileOutputStream(new File("D:/work/workplace/hadoop/xiyou.xml"));
            FSDataInputStream inputStream = fs.open(new Path("/user/hadoop/input/capacity-scheduler.xml"));
            //获取输入流
//            FileInputStream inputStream = new FileInputStream(new File("D:/work/workplace/hadoop/xiyou1.txt"));
            //流对接
            IOUtils.copyBytes(inputStream, outputStream,configuration);
            System.out.println("success");
        }
        catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            
            if ( fs!=null )
            {
                try
                {
                    fs.close();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    /**
     * 描述：定位下载
     * @throws IOException 
     * @throws IllegalArgumentException 
     */
    @SuppressWarnings("resource")
    @Test
    public void getBlockFileHDFS1() throws IllegalArgumentException, IOException{
        FileSystem fs =null;
        fs = getFileSystemCon();
        //获取输入流
        FSDataInputStream inputStream = null;
        //创建输出流
        FileOutputStream outputStream = null;
        try
        {
            inputStream = fs.open(new Path("/user/hadoop/input/jdk-7u79-linux-x64.tar.gz"));
            outputStream = new FileOutputStream(new File("D:/work/workplace/hadoop/jdk-7u79-linux-x64.tar.gz.part1"));
            //流对接(制度128M)
            byte[] buf =new byte[1024];
            // 1024 * 1024 * 128 
            for (int i = 0; i < 1024*128; i++)
            {
                inputStream.read(buf);
                outputStream.write(buf);
                
            }
            System.out.println("success");
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        //关闭资源
        IOUtils.closeStream(outputStream);
        IOUtils.closeStream(inputStream);
        IOUtils.closeStream(fs);
        
    }
    /**
     * 下载第二块
     * @throws IllegalArgumentException
     * @throws IOException
     */
    @Test
    public void getBlockFileHDFS2() throws IllegalArgumentException, IOException{
        FileSystem fs =null;
        fs = getFileSystemCon();
        //获取输入流
        FSDataInputStream inputStream = null;
        //创建输出流
        FileOutputStream outputStream = null;
        try
        {
            inputStream = fs.open(new Path("/user/hadoop/input/jdk-7u79-linux-x64.tar.gz"));
            outputStream = new FileOutputStream(new File("D:/work/workplace/hadoop/jdk-7u79-linux-x64.tar.gz.part2"));
            //流对接(制度128M)
            //定位到第一块
            inputStream.seek(1024*1024*128);
      IOUtils.copyBytes(inputStream, outputStream, configuration);
      System.out.println("success");
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        //关闭资源
        IOUtils.closeStream(outputStream);
        IOUtils.closeStream(inputStream);
        IOUtils.closeStream(fs);
        
    }
    
    
    
    
    
}
