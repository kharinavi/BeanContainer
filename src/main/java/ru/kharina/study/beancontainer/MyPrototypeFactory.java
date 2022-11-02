package ru.kharina.study.beancontainer;

import java.lang.reflect.InvocationTargetException;

public class MyPrototypeFactory {

    public <T> T getMyBean(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends T> myClass = clazz;

        System.out.println("Class represented by myClass: "
                + myClass.toString());

        Class[] parameterType = null;

        T bean = null;
        bean = myClass.getDeclaredConstructor(parameterType).newInstance();
        return bean;
    }
}
