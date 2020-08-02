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
public class Book   //����һ��entity
{
    private  @Getter Integer id;            //Field
    private  @Setter@Getter String  bookName;
    private  @Getter double  price;
    private  @NonNull String  author;
    
}
/**
1   �½�maven���̣�pom
            <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.16.6</version>
            <scope>provided</scope>
        </dependency>
        
2   ����lombok��������eclipse
    java -jar lombokxxx.jar
    �ڵ����Ĵ����У�������ʾָ����lombok������eclipse.exe��ʲô·��
    
3   ��ע����ж�Ӧ��entity�Ĳ���
 */