package org.bling.freemark.engine;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class GenMain {
    public static void main(String[] args) throws IOException {
        // 参数值
        Map<String, Object> map = new HashMap<String, Object>();

        String className = "User";
        map.put("ClassName", "User");
        map.put("type", "String");
        map.put("name", "testName");

        // 模板目录
        String templateDirectory = "freemark-use/src/main/resources/template";
        // 模板名称
        String templateFile = "test.java";
        // 模板生成后存放目录
        // String targetPath = "freemark-use/src/main/java";
        String targetPath = "freemark-use/src/main/java/org/bling/freemark/gen/test/entry";
        // 模板生成后新文件名
        String fileName = className + ".java";
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
