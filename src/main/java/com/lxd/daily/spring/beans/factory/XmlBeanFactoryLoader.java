package com.lxd.daily.spring.beans.factory;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * xml配置加载bean
 * Created by liaoxudong
 * Date:2018/5/10
 */

public class XmlBeanFactoryLoader {

    private int param;

    public XmlBeanFactoryLoader(int param) {
        this.param = param;
    }

    /**
     * XmlBeanFactory的内部实现
     */
    public static void main(String[] args){
        ClassPathResource resource = new ClassPathResource("beans_1.xml");
        /*DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(factory);
        definitionReader.loadBeanDefinitions(resource);
        XmlBeanFactoryLoader bean = factory.getBean(XmlBeanFactoryLoader.class, 1);
        System.out.println(bean.param);*/

        // 上面代码即XmlBeanFactory的内部实现，等同于下面代码：
        XmlBeanFactory beanFactory = new XmlBeanFactory(resource);
        XmlBeanFactoryLoader bean = beanFactory.getBean(XmlBeanFactoryLoader.class, 1);
        System.out.println(bean.param);


    }
}
