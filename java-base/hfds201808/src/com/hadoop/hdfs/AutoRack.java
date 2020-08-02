package com.hadoop.hdfs;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.net.DNSToSwitchMapping;
/**
 * 描述：自定义机架感知
 * @author 候东
 *
 */
public class AutoRack implements DNSToSwitchMapping
{

    @Override
    public List<String> resolve(List<String> ips)
    {
        // 准备一个返回机架的集合
        ArrayList<String> lists = new ArrayList<>();
        
        
        int ipnumber = 0 ;
        //ips：hadoop108  192.168.1.108
        //获取机架的ip
        if ( ips != null && ips.size() > 0 )
        {
            for (String ip : ips)
            {
              
                //hadoop108
                if(ip.startsWith("hadoop")){
                    String ipnum = ip.substring(6);
                    ipnumber = Integer.parseInt(ipnum);
                    
                    //192.168.1.108
                }else if (ip.startsWith("192")) {
                    int index = ip.lastIndexOf(".");
                    String ipnum = ip.substring(index+1);
                    ipnumber = Integer.parseInt(ipnum);
                }
                //自定义机架感知（把108、109、110定义为机架1；、111定义为机架2）
                
                if ( ipnumber < 111 )//定义为机架1
                {
                    lists.add("/rack1/"+ipnumber);
                    
                }else {//定义为机架2
                    lists.add("/rack2/"+ipnumber);
                }
            }
          
            
        }
        
       
        
        //返回
        return lists;
    }
    @Override
    public void reloadCachedMappings()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void reloadCachedMappings(List<String> arg0)
    {
        // TODO Auto-generated method stub
        
    }

  
    
}
