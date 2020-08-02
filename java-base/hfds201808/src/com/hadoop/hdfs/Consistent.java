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
  //获取配置信息
    static Configuration configuration = new Configuration();
    /**
     * 获取文件系统
     * @return
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
    public static void main(String[] args) throws IllegalArgumentException, IOException
    {
        //获取文件系统
        FileSystem fileSystem = getFileSystemCon();
        //获取输出流
        FSDataOutputStream outputStream = fileSystem.create(new Path("/user/hadoop/xiaoqiao.txt"));
        //具体上传数据
        outputStream.write("helloWorld12 12".getBytes());
        outputStream.hflush();//一致性刷新
        //关闭资源
        outputStream.close();
        
    }
   
}
