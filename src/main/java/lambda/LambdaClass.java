package lambda;

public class LambdaClass {
    public static void forEg() {
        lambdaInterfaceDemo(()-> System.out.println("自定义函数式接口"));
    }
    //函数式接口参数
    static void lambdaInterfaceDemo(LambdaInterface i){
        System.out.println(i);
    }

    public static void main(String[] args) {
        LambdaClass.forEg();
    }
}