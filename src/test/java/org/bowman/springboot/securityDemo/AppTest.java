package org.bowman.springboot.securityDemo;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=App.class)

/***************************************************************
 * 只识别.properties，如果换成application-test.yml，不会报错，
 * 但是-test中的属性不会覆盖application.yml中的属性
 ***************************************************************/
@TestPropertySource("classpath:application-test.properties")
public class AppTest{
  
}
