package ru.kharina.study.beancontainer;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainClass {
        public static void main (String[]args) throws Exception {

            MyApplicationContext appContext = new MyApplicationContext();
            String packageName = new Object(){}.getClass().getPackage().getName();
            appContext.parsePackage(packageName);
            NewClass class1 = appContext.getMyBean(NewClass.class);
            NewClass class2 = appContext.getMyBean(NewClass.class);
            class1.setName("1");
            class2.setName("2");
            System.out.println(class1.getName());
            System.out.println(class2.getName());

            SomeClass class3 = appContext.getMyBean(SomeClass.class);
            class3.setName("3");
            System.out.println(class3.getName());
        }
}
