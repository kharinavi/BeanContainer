package ru.kharina.study.beancontainer;

@MyComponent
@MyScope(value = "prototype")
public class SomeClass {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
