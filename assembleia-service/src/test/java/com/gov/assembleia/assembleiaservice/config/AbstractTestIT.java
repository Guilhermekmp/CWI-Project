package com.gov.assembleia.assembleiaservice.config;

import com.gov.assembleia.assembleiaservice.AssembleiaServiceApplication;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = AssembleiaServiceApplication.class)
@ExtendWith(SpringExtension.class)
public abstract class AbstractTestIT<T> {

  @Autowired
  private MappingJackson2HttpMessageConverter converter;

  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  public void init(T resource) {

    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(resource)
            .setMessageConverters(this.converter, new ResourceHttpMessageConverter()).build();
  }

  public MockMvc getMockMvc() {
    return mockMvc;
  }

  @Before
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Autowired
  public void setWebApplicationContext(WebApplicationContext pWebApplicationContext) {
    webApplicationContext = pWebApplicationContext;
  }

}
