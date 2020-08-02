package org.bling.dom4j.use;

//public class Dom4jTest {
//}

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jTest {
    private static String filePath = "dom4j-use/src/address.xml";

    public static void main(String[] args) {
        try {
            init();
            add();
            traverse();
            edit(1);
            del(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void del(int i) throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(filePath));
        Element root = document.getRootElement();
        Iterator<Element> it = root.elementIterator();
        while (it.hasNext()) {
            Element e = it.next();
            if (Integer.parseInt(e.attributeValue("id")) == i) {
                root.remove(e);
                break;
            }
        }
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        XMLWriter writer = new XMLWriter(new FileOutputStream(filePath), format);
        writer.write(document);
        writer.close();
    }

    private static void edit(int i) throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(filePath));
        Element root = document.getRootElement();
        Iterator<Element> it = root.elementIterator();

        while (it.hasNext()) {
            Element e = it.next();
            if (Integer.parseInt(e.attributeValue("id")) == i) {
                e.element("name").setText("你好");
                e.element("email").setText("lichao51126@gmail.com");
                break;
            }

        }

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        XMLWriter writer = new XMLWriter(new FileOutputStream(filePath), format);
        writer.write(document);
        writer.close();
    }

    private static void traverse() throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(filePath));
        Element root = document.getRootElement();
        Iterator<Element> it = root.elementIterator();

        while (it.hasNext()) {
            Element e = it.next();
            System.out.println("id ：" + e.attributeValue("id"));
            System.out.println("姓名： " + e.element("name").getText());
            System.out.println("邮箱：" + e.elementText("email"));
            System.out.println("性别：" + e.elementText("gender"));
        }

    }


    private static void add() throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(filePath));
        Element root = document.getRootElement();
        Element parent = root.addElement("linkman");
        parent.addAttribute("id", "2");
        Element name = parent.addElement("name");
        name.setText("李丽");
        Element email = parent.addElement("email");
        email.setText("lichao511216@163.com");
        Element gender = parent.addElement("gender");
        gender.setText("female");
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        XMLWriter writer = new XMLWriter(new FileOutputStream(filePath), format);
        writer.write(document);
        writer.close();
    }

    // @SuppressWarnings("deprecation")  // 关闭waring
    private static void init() throws IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("addresslist");
        Element parent = root.addElement("linkman");
        parent.addAttribute("id", "1");
        Element name = parent.addElement("name");
        name.setText("李超");
        Element email = parent.addElement("email");
        email.setText("953166286@qq.com");
        Element gender = parent.addElement("gender");
        gender.setText("male");
        // OutputFormat out = new OutputFormat("  ", true, "utf-8");
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        //format.setExpandEmptyElements(true);
        //format.setIndent(" ");
        //format.setNewlines(true);
        //format.setNewLineAfterDeclaration(false);
        XMLWriter writer = new XMLWriter(new FileOutputStream(filePath), format);
        writer.write(document);
        writer.close();
    }

}
