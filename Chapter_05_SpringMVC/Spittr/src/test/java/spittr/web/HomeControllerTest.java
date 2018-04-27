package spittr.web;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import spittr.web.HomeController;

public class HomeControllerTest {

  @Test
  public void testHomePage() throws Exception {
    HomeController controller = new HomeController();
    //搭建MockMvc
    MockMvc mockMvc = standaloneSetup(controller).build();
    //使用mockMvc执行get请求，路径为“/”
    mockMvc.perform(get("/"))
            //预期得到视图名称
           .andExpect(view().name("home"));
  }

}
