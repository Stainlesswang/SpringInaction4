Chapter 3 Examples
==================
This folder contains example code for chapter 3 of Spring in Action, 4th Edition.
The samples are split across multiple directories:

 * profiles     : Examples illustrating profile configuration for section 3.1.
 * conditionals : Examples illustrating conditional configuration for section 3.2.
 * scopedbeans  : Examples illustrating scoped bean configuration for section 3.4.
 * externals    : Examples illustrating external configuration for section 3.5.

Note that because the examples evolve throughout the chapter and the book's text sometimes
shows multiple ways of accomplishing a goal, not all variations of the code in the book will
be represented in these samples. You are invited to use this source code as a starting point
and experiment using the variations presented in the text.
第三章;高级装配
 1.Spring profile;
   profile用来让部署单元不需要重新构建,就可以自动根据环境来决策哪个,profile处于激活状态,
     就创建相应的bean
 2.条件化的bean声明:
     顾名思义,当一些条件满足时才创建带有@Conditional注解的bean,设置给@Conditional注解的类可以是任意实现
     如下接口的类,并且有matches()方法返回true or false
     public interface Condition {
         boolean matches(ConditionContext var1, AnnotatedTypeMetadata var2);
     }
     例如 condition/MyCondition.java
 3.处理自动装配的歧义性:
    当一个接口同时被三个类实现了以后,自动装配的时候Spring会不知道该选择哪个,就会出现歧义性 解决方法有如下
   ①使用 @Component+ @Primary 来设置首选
   ②使用 @Qualifier 是使用限定符的主要方式,限定符的名称匹配当生成bean的时候将类名首字母小写或者可以自己
      定义限定符的名称,来实现缩小范围
      解决当进一步缩小范围的时候还需要@Qualifier 注解 编译器无法通过的问题,就是自定义注解来实现自己想要的
      限定符
 4.bean的作用域
    见scope包下的类注解.@Scope 注解属性
    @Scope属性的代理模式(proxyMode) 属性怎么使用

 5.Spring表达式语言
   简称SpEL:
   表达式放入#{...} 当中
