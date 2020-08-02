package com.hadoop.hdfs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.junit.Test;

/**
 * 描述：搭建hadoop文件基本传输
 * @param args
 * @throws IOException 
 * @throws URISyntaxException 
 * @throws InterruptedException 
 */
public class HDFSClient
{
    /**
     * 描述：获取连接
     * @throws URISyntaxException 
     * @throws InterruptedException 
     * @throws IOException 
     */
    public static FileSystem getFileSystemCon() 
    {
        //获取配置信息
        Configuration configuration = new Configuration();
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
    
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException
    {
        //获取配置信息
        Configuration configuration = new Configuration();
        //        configuration.set("fs.defaultFS", "hdfs://hadoop108:8020");
        
        //获取文件系统
        //        FileSystem fs = FileSystem.get(configuration);
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop108:8020"), configuration, "hadoop");
        //拷贝本地数据到集群
        //        fs.copyFromLocalFile(new Path("d:/work/workplace/hadoop/xiyou1.txt"), new Path("/user/hadoop/input/xiyou1.txt"));
        FileSystem[] childFileSystems = fs.getChildFileSystems();
        if ( childFileSystems != null && childFileSystems.length > 0 )
        {
            System.out.println(childFileSystems);
        }
        else
        {
            System.out.println("childFileSystems is null");
        }
        
        //关闭集群
        fs.close();
        
    }
    
    /**
     * 描述：获取文件系统
     * @throws URISyntaxException 
     * @throws InterruptedException 
     * @throws IOException 
     */
    @Test
    public void getFileSystem() throws IOException, InterruptedException, URISyntaxException
    {
        //获取文件系统
        FileSystem fSystem = FileSystem.get(new URI("hdfs://hadoop108:8020"), new Configuration(), "hadoop");
        //打印
        System.out.println(fSystem);
        fSystem.close();
        
    }
    
    /**
     * 描述:上传文件
     * @throws URISyntaxException 
     * @throws InterruptedException 
     * @throws IOException 
     */
    @Test
    public void putFileSystem()
    {
        //获取文件系统
        FileSystem fs = null;
        
        try
        {
            fs = getFileSystemCon();
            
            //执行上传文件命令
            
            fs.copyFromLocalFile(true, new Path("d:/work/workplace/hadoop/xiyou.txt"), new Path("/user/hadoop/"));
            
            System.out.println("文件上传成功...");
        }
        catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      
        finally
        {
            if ( fs != null )
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
     * 描述：下载文件
     */
    @Test
    public void getFileSystemHDFS()
    {
        FileSystem fs = null;
        try
        {
            fs = getFileSystemCon();
            fs.copyToLocalFile(false, new Path("/user/hadoop/xiyou1.txt"), new Path("d:/work/workplace/hadoop/"), true);
            System.out.println("下载成功...");
        }
        catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        finally
        {
            if ( fs != null )
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
     * 描述：创建目录
     * 
     */
    @Test
    public void mkdirATHDFS()
    {
        
        FileSystem fs = null;
        try
        {
            fs = getFileSystemCon();
            fs.mkdirs(new Path("/user/hadoop/diyu/haha"));
            System.out.println("创建成功");
        }
        catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e);
        }
       
        finally
        {
            if ( fs != null )
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
     * 描述：删除文件
     */
    @Test
    public void deleteHDFS()
    {
        FileSystem fs = null;
        try
        {
            fs = getFileSystemCon();
            fs.delete(new Path("/user/hadoop/xiyou.txt"), true);
            System.out.println("删除成功");
        }
        catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        finally
        {
            if ( fs != null )
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
     * 描述：更改文件名称
     */
    @Test
    public void renameHDFS()
    {
        
        FileSystem fs = null;
        try
        {
            fs = getFileSystemCon();
            fs.rename(new Path("/user/hadoop/xiyou1.txt"), new Path("/user/hadoop/xiyu.txt"));
            
            System.out.println("修改成功");
        }
        catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      
        finally
        {
            if ( fs != null )
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
     *描述：查看文件详情
     */
    @Test
    public void readFileHDFS()
    {
        
        FileSystem fs = null;
        try
        {
            fs = getFileSystemCon();
            RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
            while (listFiles.hasNext())
            {
                //                System.out.println("------------------");
                LocatedFileStatus next = listFiles.next();
                System.out.println(next.getPath().getName());
                /*System.out.println(next.getBlockSize()/(1024*1024));
                System.out.println(next.getLen());
                System.out.println(next.getOwner());
                System.out.println(next.getPermission());
                System.out.println(next.getAccessTime());*/
                //文件块的具体信息
                BlockLocation[] blockLocations = next.getBlockLocations();
                for (BlockLocation blockLocation : blockLocations)
                {
                    System.out.println(blockLocation.getOffset());
                    String[] hosts = blockLocation.getHosts();
                    for (String string : hosts)
                    {
                        System.out.println(string);
                    }
                    
                }
                
            }
            
            System.out.println("查看成功");
        }
        catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        finally
        {
            if ( fs != null )
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
    
   
    
    
    
    
    
}
