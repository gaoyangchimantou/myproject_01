package com.bfsu.myproject_01;

import com.bfsu.myproject_01.entities.Article;
/*import com.bfsu.myproject_01.entities.Book;*/
import com.bfsu.myproject_01.entities.Employee;
import com.rabbitmq.client.AMQP;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Myproject01ApplicationTests {
    @Autowired
    AmqpAdmin amqpAdmin;
	@Autowired
	StringRedisTemplate stringRedisTemplate;//操作k-v都是字符串的
	@Autowired
	RedisTemplate redisTemplate;//操作k-v都是对象的
	@Autowired
	RedisTemplate<Object, Employee> empRedisTemplate;
    @Autowired
    RabbitTemplate rabbitTemplate;
	/*@Autowired
	RabbitMessagingTemplate rabbitMessagingTemplate;*/

	@Autowired
	JestClient jestClient;
	/*@Autowired
	BookRepository bookRepository;*/
	@Autowired
	JavaMailSenderImpl javaMailSender;
	@Test
	public void contextLoads() {

	}

	@Test
	public void test01() {
		stringRedisTemplate.opsForValue().append("name","gaoyang");
		String msg = stringRedisTemplate.opsForValue().get("msg");
		System.out.println(msg);
	}


	@Test
	public void test02() {
		Employee emp=new Employee();
		emp.setLastName("zhangsan");
		emp.setGender(1);
		redisTemplate.opsForValue().set("emp",emp);
		Object emp1 = redisTemplate.opsForValue().get("emp");
		System.out.println(emp1);
	}
	@Test
	public void test03() {
		//默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
		//redisTemplate.opsForValue().set("emp-01",empById);
		//1、将数据以json的方式保存
		//(1)自己将对象转为json
		//(2)redisTemplate默认的序列化规则；改变默认的序列化规则；
		Employee myEmp=new Employee();
		myEmp.setLastName("lisi");
		myEmp.setGender(0);
		empRedisTemplate.opsForValue().set("myEmp",myEmp);
		Employee myEmp1 = empRedisTemplate.opsForValue().get("myEmp");
		System.out.println(myEmp);
	}

    @Test
    public void test04() {

        Map<String,Object> map=new HashMap<String, Object>();
        map.put("1","1111");
        map.put("2","2222");
        map.put("3","3333");
        map.put("4","4444");
        rabbitTemplate.convertAndSend("exchange.direct","atguigu",map);

    }
    @Test
    public void test05(){

        Object atguigu = rabbitTemplate.receiveAndConvert("atguigu");
        System.out.println(atguigu.getClass());
        System.out.println(atguigu);

    }

    @Test
    public void test06(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("quanbu","quanbuaaaaa");
        rabbitTemplate.convertAndSend("exchange.fanout","atguigu",map);
    }

    @Test
    public void createExchange(){

	amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
	System.out.println("创建完成");

	amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));
        //创建绑定规则

	amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqp.haha",null));

        //amqpAdmin.de
    }



    //jestClient
	@Test
	public void test07() throws IOException {
		//1、给Es中索引（保存）一个文档；
		Article article = new Article();
		article.setId(1);
		article.setTitle("good news");
		article.setAuthor("zhangsan");
		article.setContent("Hello World");
		Index index=new Index.Builder(article).index("atguigu").type("news").build();
		jestClient.execute(index);
	}


	@Test
	public void test08() throws IOException {
		//查询表达式
		String json ="{\n" +
				"    \"query\" : {\n" +
				"        \"match\" : {\n" +
				"            \"content\" : \"hello\"\n" +
				"        }\n" +
				"    }\n" +
				"}";

		//更多操作：https://github.com/searchbox-io/Jest/tree/master/jest
		//构建搜索功能
		Search search =new Search.Builder(json).addIndex("atguigu").addType("news").build();

		SearchResult execute =jestClient.execute(search);
		System.out.println(execute.getJsonString());
	}



	//bookRepository
		/*@Test
	public void test09() throws IOException {

		//spring-data-elasticsearch/3.1.8.RELEASE------------------------->对应的6.2.2的elasticsearch   我装的是2.4.6
		//所以用不了
		Book book=new Book();
		book.setId(1);
		book.setBookName("西游记");
		book.setAuthor("吴承恩");
		bookRepository.index(book);
	}*/


		//javaMailSender

@Test
	public void test10()  {
		SimpleMailMessage smessage=new SimpleMailMessage();
		smessage.setText("你好啊   大佬！");
		smessage.setSubject("今晚记得 要开会啊！");
		smessage.setTo("724765153@qq.com");
		smessage.setFrom("2073646584@qq.com");
		javaMailSender.send(smessage);
	}



	@Test
	public void test11() throws  Exception{
		//1、创建一个复杂的消息邮件
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		//邮件设置
		helper.setSubject("通知-今晚开会");
		helper.setText("<b style='color:red'>今天 7:30 开会</b>",true);

		helper.setTo("17512080612@163.com");
		helper.setFrom("534096094@qq.com");

		//上传文件
		helper.addAttachment("1.jpg",new File("C:\\Users\\lfy\\Pictures\\Saved Pictures\\1.jpg"));
		helper.addAttachment("2.jpg",new File("C:\\Users\\lfy\\Pictures\\Saved Pictures\\2.jpg"));

		javaMailSender.send(mimeMessage);

	}
}
