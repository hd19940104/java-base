package com.hadoop.hdfs;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.net.DNSToSwitchMapping;
/**
 * �������Զ�����ܸ�֪
 * @author ��
 *
 */
public class AutoRack implements DNSToSwitchMapping
{

    @Override
    public List<String> resolve(List<String> ips)
    {
        // ׼��һ�����ػ��ܵļ���
        ArrayList<String> lists = new ArrayList<>();
        
        
        int ipnumber = 0 ;
        //ips��hadoop108  192.168.1.108
        //��ȡ���ܵ�ip
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
                //�Զ�����ܸ�֪����108��109��110����Ϊ����1����111����Ϊ����2��
                
                if ( ipnumber < 111 )//����Ϊ����1
                {
                    lists.add("/rack1/"+ipnumber);
                    
                }else {//����Ϊ����2
                    lists.add("/rack2/"+ipnumber);
                }
            }
          
            
        }
        
       
        
        //����
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
