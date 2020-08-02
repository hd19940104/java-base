package org.bling.freemark.engine;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class User {
    public int age;
    public String name;
    User(int i, String s){
        this.name = s;
        this.age = i;
    }
}

public class GenList {

    public static void main(String[] args) throws IOException {
        // 参数值
        Map<String, Object> map = new HashMap<String, Object>();


        List<User> parameters = new ArrayList<User>();
        parameters.add(new User(1,"A"));
        parameters.add(new User(2,"B"));
        parameters.add(new User(3,"C"));

/*
        List<String> parameters = new ArrayList<>();
        parameters.add("A");
        parameters.add("B");
*/
        // System.out.println("a = " + parameters.get(1).s);

        map.put("parameters", parameters);

        // 模板目录
        String templateDirectory = "freemark-use/src/main/resources/template";
        // 模板名称
        String templateFile = "GenList.ftl";
        // 模板生成后存放目录
        String targetPath = "freemark-use/src/main/java";
        // 模板生成后新文件名
        String fileName = "GenList" + ".proto";
        // 创建文件夹
        boolean mkdirs = new File(targetPath).mkdirs();
        if (!mkdirs){
            System.out.println("dir exit");
        }
        File nFile = new File(targetPath +"/"+ fileName);

        // 生成目标文件
        try (Writer writer = new FileWriter(nFile)) {

            Template template = getConfiguration(templateDirectory).getTemplate(templateFile, "UTF-8");
            template.process(map, writer);

        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Configuration getConfiguration(String templateDirectory) {

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
        try {
            configuration.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);
            configuration.setDirectoryForTemplateLoading(new File(templateDirectory));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configuration;
    }
}
