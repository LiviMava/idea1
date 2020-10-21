package cn.edu.zzu.tasks._4_;

public class Person {
    int age;
    String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.toUpperCase().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Person){
            Person person = (Person) obj;
            return name.equals(person.name) && age == person.age;
        }
        return false;
    }

    @Override
    public String toString() {
        return age+" and "+name;
    }
}
