package com.bfsu.myproject_01.controller.config;

/*import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;*/
import org.eclipse.jetty.server.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    /*
    * 第一种改变嵌入式tomcat的配置是在  配置文件中 server.tomcat.XXXX=xxxx直接配置
    *
    * 第二种  1.在springboot1.x中 是向容器中传建一个EmbeddedServletContainerCustomizer对象
    *           @Bean //一定要将这个定制器加入到容器中
                public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
                return new EmbeddedServletContainerCustomizer() {
                //定制嵌入式的Servlet容器相关的规则
                @Override
                public void customize(ConfigurableEmbeddedServletContainer container) {
                container.setPort(8083);
                }
                };
                }
              2.在springboot2.0以上版本,就没有EmbeddedServletContainerCustomizer类了,二十采用下面的这种方法实现配置
              嵌入式的servlet容器(tomcat容器)
    *
    *
    * */
   /* @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer(){

        return  new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>(){
            @Override
            public void customize(ConfigurableServletWebServerFactory factory) {
                ((TomcatServletWebServerFactory)factory).addConnectorCustomizers(new TomcatConnectorCustomizer() {
                    @Override
                    public void customize(Connector connector) {
                        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
                        protocol.setMaxConnections(200);
                        protocol.setMaxThreads(200);
                        protocol.setSelectorTimeout(3000);
                        protocol.setSessionTimeout(3000);
                        protocol.setConnectionTimeout(3000);
                        protocol.setPort(8089);
                    }
                });

            }
        };

    }*/
}
