package spittr.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import spittr.web.WebConfig;
/**
 *    第一步！！！
 *    该类是配置核心Servlet组件DispatherServlet。
 *    任何扩展AbstractAnnotationConfigDispatcherServletInitializer 的类都会自动的配置
 *    Dispather-Servlet和Spring应用上下文
 * */

public class SpitterWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  
  @Override
  //ContextLoaderListener   使用ROOTConfig.class 来加载应用中其他的bean 也就是常说的应用上下文中的bean
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[] { RootConfig.class };
  }

  @Override
  //DispatherServlet   使用webConfig.class 来加载web组件使用的bean,例如视图解析器,控制器,处理器映射等
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] { WebConfig.class };
  }

  @Override
  //将一个或者多个路径映射到DispatherServlet上,该映射'/'表示它是应用的默认Servlet,可以处理进入应用的所有请求
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

}