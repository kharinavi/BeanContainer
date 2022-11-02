package ru.kharina.study.beancontainer;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class MyApplicationContext {
    MyPrototypeFactory myPrototypeFactory;
    MySingletonFactory mySingletonFactory;

    public List<String> scanPackage(String packName) {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(MyComponent.class));
        List<String> collect = scanner
                .findCandidateComponents(packName)
                .stream()
                .map(BeanDefinition::getBeanClassName)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return collect;
    }

    public void parsePackage(String packName) {
        myPrototypeFactory = new MyPrototypeFactory();
        mySingletonFactory = new MySingletonFactory();

        List<String> collect = scanPackage(packName);

        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        for (String className: collect){
            System.out.println(String.format("%s",className));
            try {
                Class<?> currentClass = Class.forName(className);
                classes.add(currentClass);
            }
            catch (ClassNotFoundException e) {
                throw new RuntimeException("ClassNotFoundException loading " + className);
            }
        }
    }

    public <T> T getMyBean(Class<T> clazz) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends T> myClass = clazz;
        T bean = null;
        boolean getSingleton = true;
        if (myClass.isAnnotationPresent(MyScope.class)) {
            if (myClass.getDeclaredAnnotation(MyScope.class).value() == "prototype") {
                bean = myPrototypeFactory.getMyBean(clazz);
                getSingleton = false;
            }
        }
        if (getSingleton) {
            bean = mySingletonFactory.getMyBean(clazz);
        }
        return bean;
    }
}
