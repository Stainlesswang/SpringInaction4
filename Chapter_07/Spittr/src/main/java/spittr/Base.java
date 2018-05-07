package spittr;

/**
 * 该方法验证
 * Java中的static方法和final方法（private属于final方法，）属于前期绑定，子类无法重写final方法，成员变量（包括静态及非静态）也属于前期绑定。
 * 除了static方法和final方法（private属于final方法）之外的其他方法属于后期绑定，运行时能判断对象的类型进行绑定。
 * */
class Base
{
    //成员变量，子类也有同样的成员变量名
    public String test="Base Field";

    public Base(){
        System.out.println("Base Construct");

    }
    //静态方法，子类也有同样签名的静态方法
    public static void staticMethod()
    {
        System.out.println("Base staticMethod()");
    }
    //子类将对此方法进行覆盖
    public void notStaticMethod()
    {
        System.out.println("Base notStaticMethod()");
    }

}



 class Derive extends Base
{
    public String test="Derive Field";

    public Derive(){
        System.out.println("Derive Construct");

    }

    public static void staticMethod()
    {
        System.out.println("Derive staticMethod()");
    }
    @Override
    public void notStaticMethod()
    {
        System.out.println("Derive notStaticMethod()");
    }
    //输出成员变量的值，验证其为前期绑定。
    public static void testFieldBind(Base base)
    {
        System.out.println(base.test);
    }
    //静态方法，验证其为前期绑定。
    public static void testStaticMethodBind(Base base)
    {
        //The static method test() from the type Base should be accessed in a static way
        //使用Base.test()更加合理，这里为了更为直观的展示前期绑定才使用这种表示。
        base.staticMethod();
    }
    //调用非静态方法，验证其为后期绑定。
    public static void testNotStaticMethodBind(Base base)
    {
        base.notStaticMethod();
    }



    public static void main(String[] args)
    {
        Derive d=new Derive();
        testFieldBind(d);
        testStaticMethodBind(d);
        testNotStaticMethodBind(d);
    }
}
/*程序输出:
Base Field
Base staticMethod()
Derive notStaticMethod()
*/
