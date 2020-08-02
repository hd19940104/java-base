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
 * �������hadoop�ļ���������
 * @param args
 * @throws IOException 
 * @throws URISyntaxException 
 * @throws InterruptedException 
 */
public class HDFSClient
{
    /**
     * ��������ȡ����
     * @throws URISyntaxException 
     * @throws InterruptedException 
     * @throws IOException 
     */
    public static FileSystem getFileSystemCon() 
    {
        //��ȡ������Ϣ
        Configuration configuration = new Configuration();
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
    
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException
    {
        //��ȡ������Ϣ
        Configuration configuration = new Configuration();
        //        configuration.set("fs.defaultFS", "hdfs://hadoop108:8020");
        
        //��ȡ�ļ�ϵͳ
        //        FileSystem fs = FileSystem.get(configuration);
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop108:8020"), configuration, "hadoop");
        //�����������ݵ���Ⱥ
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
        
        //�رռ�Ⱥ
        fs.close();
        
    }
    
    /**
     * ��������ȡ�ļ�ϵͳ
     * @throws URISyntaxException 
     * @throws InterruptedException 
     * @throws IOException 
     */
    @Test
    public void getFileSystem() throws IOException, InterruptedException, URISyntaxException
    {
        //��ȡ�ļ�ϵͳ
        FileSystem fSystem = FileSystem.get(new URI("hdfs://hadoop108:8020"), new Configuration(), "hadoop");
        //��ӡ
        System.out.println(fSystem);
        fSystem.close();
        
    }
    
    /**
     * ����:�ϴ��ļ�
     * @throws URISyntaxException 
     * @throws InterruptedException 
     * @throws IOException 
     */
    @Test
    public void putFileSystem()
    {
        //��ȡ�ļ�ϵͳ
        FileSystem fs = null;
        
        try
        {
            fs = getFileSystemCon();
            
            //ִ���ϴ��ļ�����
            
            fs.copyFromLocalFile(true, new Path("d:/work/workplace/hadoop/xiyou.txt"), new Path("/user/hadoop/"));
            
            System.out.println("�ļ��ϴ��ɹ�...");
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
     * �����������ļ�
     */
    @Test
    public void getFileSystemHDFS()
    {
        FileSystem fs = null;
        try
        {
            fs = getFileSystemCon();
            fs.copyToLocalFile(false, new Path("/user/hadoop/xiyou1.txt"), new Path("d:/work/workplace/hadoop/"), true);
            System.out.println("���سɹ�...");
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
     * ����������Ŀ¼
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
            System.out.println("�����ɹ�");
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
     * ������ɾ���ļ�
     */
    @Test
    public void deleteHDFS()
    {
        FileSystem fs = null;
        try
        {
            fs = getFileSystemCon();
            fs.delete(new Path("/user/hadoop/xiyou.txt"), true);
            System.out.println("ɾ���ɹ�");
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
     * �����������ļ�����
     */
    @Test
    public void renameHDFS()
    {
        
        FileSystem fs = null;
        try
        {
            fs = getFileSystemCon();
            fs.rename(new Path("/user/hadoop/xiyou1.txt"), new Path("/user/hadoop/xiyu.txt"));
            
            System.out.println("�޸ĳɹ�");
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
     *�������鿴�ļ�����
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
                //�ļ���ľ�����Ϣ
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
            
            System.out.println("�鿴�ɹ�");
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
