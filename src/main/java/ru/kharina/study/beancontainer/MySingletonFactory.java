package ru.kharina.study.beancontainer;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class MySingletonFactory {

    private Map<String, Object> currentBeans = new HashMap<>();

    public <T> T getMyBean(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends T> curClass = clazz;
        Class[] parameterType = null;
        T bean = curClass.getDeclaredConstructor(parameterType).newInstance();
        if (!currentBeans.containsKey(clazz.getName()))
            currentBeans.put(curClass.getName(), bean);
        bean = (T) currentBeans.get(curClass.getName());
        return bean;
    }
}
