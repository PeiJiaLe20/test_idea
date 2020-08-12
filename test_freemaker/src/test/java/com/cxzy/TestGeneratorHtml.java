package com.cxzy;

import com.czxy.TestFreeMarkerApplication;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestFreeMarkerApplication.class)
public class TestGeneratorHtml {

    @Test
    public void testDemo01() throws Exception {
        //根据模板生成 html
        //1 核心配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        //2 设置模板文件夹
        // 2.1 获得类路径 /test_freemarker/target/test-classes/
        String classpath = this.getClass().getResource("/").getPath();
        // 2.2 静态目录
        File templateDir = new File(classpath);
        // 2.3 给配置类设置操作目录
        configuration.setDirectoryForTemplateLoading(templateDir);
        //3 设置编码
        configuration.setDefaultEncoding("UTF-8");
        //4 获得具体模板 test.ftl
        Template template = configuration.getTemplate("test.ftl");
        //5 准备数据
        Map<String,String> map = new HashMap<>();
        map.put("name","heheheeeeeeeeeeeeeeeeeeeee");
        //6 静态化生产，字符串内容
        String string = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        System.out.println(string);
        //7 将“字符串”写入文件中
        File file = new File(templateDir, "test.html");
        FileUtils.writeStringToFile(file,string);

    }



    @Test
    public void testGenerateHtmlByString() throws Exception {
        // 模板字符串
        String templatesString =
                "<html>" +
                        "    <head>" +
                        "        <meta charset=\"UTF-8\">" +
                        "        <title>标题</title>" +
                        "    </head>" +
                        "    <body>" +
                        "        ${name}" +
                        "    </body>" +
                        "</html>";

        // 模板加载器
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate("myTemplate", templatesString);
        //创建配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setTemplateLoader( stringTemplateLoader );
        //获得模板
        Template template = configuration.getTemplate("myTemplate", "UTF-8");
        //模型数据
        Map<String,Object> map = new HashMap<>();
        map.put("name","大傻子");

        //静态化
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        //输出
        String classpath = this.getClass().getResource("/").getPath();
        File file = new File(classpath , "test2.html");
        FileUtils.writeStringToFile( file ,content);
    }

}
