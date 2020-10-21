package cn.edu.zzu.tasks._4_;

class ObjectTool<T>{
    private T obj;
    public void setObj(T obj){
        this.obj = obj;
    }
    public T getObj(){
        return obj;
    }
}
public class oop {
    public static void main(String[] args) {
        ObjectTool<String> obj = new ObjectTool();
        obj.setObj("hi");
        System.out.println(obj.getObj());
    }
}
