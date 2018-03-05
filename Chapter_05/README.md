第五章:构建Spring web应用程序
  本章内容: 映射请求到Spring控制器
            透明的绑定本地参数
            校验表单信息

    1.Spring MVC
            跟踪Spring MVC的请求:
                ①请求的第一站为 DispatcherServlet (前端控制器) ,前端控制器是一个单实例的Servlet,将请求委托给其他组件来处理任务
                ②DispatcherServlet会根据请求所携带的URL信息来进行决策,查询一个或者多个处理器映射(handler mapping),来确定请求的下一站去哪个控制器
                ③前端控制器会把请求发送给控制器,一旦到了控制器,就会卸下负载信息(用户提交的信息)
                ④控制器在完成处理后,通常会产生些信息,这些信息要返回给用户浏览器并且显示,这些信息被称为模型(Model)   通常这些信息必须以用户友好的方式进行格式化,一般为HTML
                ⑤控制器将模型数据进行打包,并且标示出用于渲染输出的视图名,接下来会将请求和视图名发给DispatcherServlet ,这样控制器就不会和特定的视图相耦合
                ⑥现在DispatcherServlet 含有视图名,实际上它仅仅传递了一个逻辑名称,这个名字会查找产生结果的真正视图,就要发送给视图解析器(view resolver),由视图解析器匹配特定试图实现
                ⑦就是交付数据模型进行视图的渲染  请求就结束了

   2，构建Spring web应用
       @RequestMapping注解编写Controller里边的每一个方法就是映射
       理解DispatcherServlet 和 ContextLoaderListener 区别：
            DispatcherServlet启动的时候，它会创建Spring应用上下文，并且加载配置文件或者配置类中的bean
            ContextLoaderListener 加载应用中的其他bean，这些bean通常是驱动应用后端的中间层和数据层组件

       核心是创建 DispatcherServlet  总管
       然后配置JSP视图解析器
       配置静态资源的处理
       启动Spring MVC 两种方式  可以使用注解的方式<mvc:annotation-driven>
       使用带有 @Configuration  @EnableWebMvc注解的配置来来启动
   3.接受请求的输入
       查询参数（Query Parameter）
         形式：
              @RequestMapping(method=RequestMethod.GET)
              public List<Spittle> spittles(
                  @RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max,
                  @RequestParam(value="count", defaultValue="20") int count) {
                return spittleRepository.findSpittles(max, count);
              }
       表单参数（Form Parameter）
         形式：
         @RequestMapping(method=RequestMethod.POST)
           public String saveSpittle(SpittleForm form, Model model) throws Exception {
             spittleRepository.save(new Spittle(null, form.getMessage(), new Date(),
                 form.getLongitude(), form.getLatitude()));
             return "redirect:/spittles";
           }
       路径变量（Path Variable）
          形式：
               @RequestMapping(value="/{spittleId}", method=RequestMethod.GET)
                 public String spittle(
                     @PathVariable("spittleId") long spittleId,
                     Model model) {
                   model.addAttribute(spittleRepository.findOne(spittleId));
                   return "spittle";
                 }