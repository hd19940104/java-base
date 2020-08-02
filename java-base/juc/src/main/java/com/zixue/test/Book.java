package com.zixue.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain=true)
public class Book   //定义一个entity
{
    private  @Getter Integer id;            //Field
    private  @Setter@Getter String  bookName;
    private  @Getter double  price;
    private  @NonNull String  author;
    
}
/**
1   新建maven工程，pom
            <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.16.6</version>
            <scope>provided</scope>
        </dependency>
        
2   进行lombok的引导到eclipse
    java -jar lombokxxx.jar
    在弹出的窗口中，依照提示指定给lombok本机的eclipse.exe在什么路径
    
3   用注解进行对应的entity的测试
 */