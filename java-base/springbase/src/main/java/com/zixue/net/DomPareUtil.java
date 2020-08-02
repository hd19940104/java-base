package com.zixue.net;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @ClassName  DomPareUtil 
 * @Description TODO
 * @author 一只会飞的小猴子
 * @date  2019年8月8日 下午6:22:14 
 *
 */
public class DomPareUtil {
	static int imageNumber = 0;
    public static void getInfo(String str){
        Document doc = Jsoup.parse(str);
        //定义一个list 存储所有图片访问路径
        List<String> imgSrcs = new ArrayList<String>();
        //先获取所有的图片元素块
        Elements rows = doc.select(".new-search-works-item");
        //遍历取出每一行所有图片块儿
        Iterator<Element> iterator = rows.iterator();
        while (iterator.hasNext()){
            Element element = iterator.next();
            //获取图片跳转路径
            String imgHref = element.select("a").attr("href");
            if (imgHref.indexOf("html")>0){
                imgSrcs.add(imgHref);
            }
        }
        List imgs = new ArrayList();
        //遍历获取每一个图片的真实路径并下载
        for (String imgSrc:imgSrcs){
        	
            try {
				String imgInfoStr=ApacheHttpUtil.getHttpContent(imgSrc);
				Document imgDoc = Jsoup.parse(imgInfoStr);
				String img = imgDoc.selectFirst(".works-img").attr("src");
				downloadPicture(img);
				imgs.add(img);
				System.out.println(img == null ? "":img );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          
        }
        //下载图片
        //downloadPictures(imgs);
    }
    private static void downloadPicture(String urlString) {
        URL url = null;
       

        try {
            url = new URL(urlString);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            String imageName = imageNumber + ".jpg";
            File file = new File(imageName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }
            dataInputStream.close();
            fileOutputStream.close();
            imageNumber++;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    private static void downloadPictures(List<String> urlList) {
        URL url = null;
        int imageNumber = 0;
        for (String urlString : urlList) {
            try {
                url = new URL(urlString);
                DataInputStream dataInputStream = new DataInputStream(url.openStream());
                String imageName = imageNumber + ".jpg";
                File file = new File(imageName);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = dataInputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, length);
                }
                dataInputStream.close();
                fileOutputStream.close();
                imageNumber++;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        //测试GET方式抓取图虫信息
        String getStr=ApacheHttpUtil.getHttpContent("http://soso.nipic.com/?q=%E7%BE%8E%E5%A5%B3&or=0&y=40&g=1");
        DomPareUtil.getInfo(getStr);

//        //测试POST方式抓取图虫信息
//        String postStr=ApacheHttpUtil.postHttpContent("https://stock.tuchong.com/creative",new HashMap<String, String>());
//        System.out.println(getStr);
//        System.out.println(postStr);
    }

}
