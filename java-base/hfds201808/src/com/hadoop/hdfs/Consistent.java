package com.hadoop.hdfs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Consistent
{
  //��ȡ������Ϣ
    static Configuration configuration = new Configuration();
    /**
     * ��ȡ�ļ�ϵͳ
     * @return
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
    public static void main(String[] args) throws IllegalArgumentException, IOException
    {
        //��ȡ�ļ�ϵͳ
        FileSystem fileSystem = getFileSystemCon();
        //��ȡ�����
        FSDataOutputStream outputStream = fileSystem.create(new Path("/user/hadoop/xiaoqiao.txt"));
        //�����ϴ�����
        outputStream.write("helloWorld12 12".getBytes());
        outputStream.hflush();//һ����ˢ��
        //�ر���Դ
        outputStream.close();
        
    }
   
}
