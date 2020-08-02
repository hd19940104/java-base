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
 * �������ļ����ϴ�������
 * @author ��
 *
 */
public class IOToHDFS
{
   //��ȡ������Ϣ
    static Configuration configuration = new Configuration();
    /**
     * ��������ȡ����
     * @throws URISyntaxException 
     * @throws InterruptedException 
     * @throws IOException 
     */
    public static FileSystem getFileSystemCon() 
    {
        
        //��ȡ�ļ�ϵͳ
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
            System.out.println("�ļ�ϵͳ���ӳɹ�...");
        }
        else
        {
            System.out.println("�ļ�ϵͳ����ʧ��...");
        }
        
        return fs;
        
    }
    /**
     * �������ļ��ϴ�IO����ʽ
     * @throws IOException 
     * @throws IllegalArgumentException 
     */
    @SuppressWarnings("resource")
    @Test
    public void putFileToHDFS()
    {
        
        FileSystem fs =null;
        //��ȡ�ļ�ϵͳ
        fs = getFileSystemCon();
        //��ȡ�����
        try
        {
            FSDataOutputStream outputStream = fs.create(new Path("/hadoop/data0/xiyou3.txt"));
            //��ȡ������
            FileInputStream inputStream = new FileInputStream(new File("D:/work/workplace/hadoop/xiyou1.txt"));
            //���Խ�
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
     * ����:����
     * 
     */
    @Test
    public void getFileHDFS(){

        
        FileSystem fs =null;
        //��ȡ�ļ�ϵͳ
        fs = getFileSystemCon();
        //��ȡ�����
        try
        {
//            FSDataOutputStream outputStream = fs.create(new Path("/user/hadoop/input/xiyou1.txt"));
            FileOutputStream outputStream = new FileOutputStream(new File("D:/work/workplace/hadoop/xiyou.xml"));
            FSDataInputStream inputStream = fs.open(new Path("/user/hadoop/input/capacity-scheduler.xml"));
            //��ȡ������
//            FileInputStream inputStream = new FileInputStream(new File("D:/work/workplace/hadoop/xiyou1.txt"));
            //���Խ�
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
     * ��������λ����
     * @throws IOException 
     * @throws IllegalArgumentException 
     */
    @SuppressWarnings("resource")
    @Test
    public void getBlockFileHDFS1() throws IllegalArgumentException, IOException{
        FileSystem fs =null;
        fs = getFileSystemCon();
        //��ȡ������
        FSDataInputStream inputStream = null;
        //���������
        FileOutputStream outputStream = null;
        try
        {
            inputStream = fs.open(new Path("/user/hadoop/input/jdk-7u79-linux-x64.tar.gz"));
            outputStream = new FileOutputStream(new File("D:/work/workplace/hadoop/jdk-7u79-linux-x64.tar.gz.part1"));
            //���Խ�(�ƶ�128M)
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
        
        
        //�ر���Դ
        IOUtils.closeStream(outputStream);
        IOUtils.closeStream(inputStream);
        IOUtils.closeStream(fs);
        
    }
    /**
     * ���صڶ���
     * @throws IllegalArgumentException
     * @throws IOException
     */
    @Test
    public void getBlockFileHDFS2() throws IllegalArgumentException, IOException{
        FileSystem fs =null;
        fs = getFileSystemCon();
        //��ȡ������
        FSDataInputStream inputStream = null;
        //���������
        FileOutputStream outputStream = null;
        try
        {
            inputStream = fs.open(new Path("/user/hadoop/input/jdk-7u79-linux-x64.tar.gz"));
            outputStream = new FileOutputStream(new File("D:/work/workplace/hadoop/jdk-7u79-linux-x64.tar.gz.part2"));
            //���Խ�(�ƶ�128M)
            //��λ����һ��
            inputStream.seek(1024*1024*128);
      IOUtils.copyBytes(inputStream, outputStream, configuration);
      System.out.println("success");
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        //�ر���Դ
        IOUtils.closeStream(outputStream);
        IOUtils.closeStream(inputStream);
        IOUtils.closeStream(fs);
        
    }
    
    
    
    
    
}
