package com.example.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @PROJECT_NAME: supermarket
 * @DESCRIPTION:
 * @USER: 韩冰
 * @DATE: 2022/9/14 0014 19:33
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEncoder {
    @Autowired
    private PasswordEncoder passwordEncoder;

    //$2a$10$ogvUqZZAxrBwrmVI/e7.SuFYyx8my8d.9zJ6bs9lPKWvbD9eefyCe
    //$2a$10$XUDAAUSIUK6NzoHpHS83q.9UV5I3.u.TUqvthsLqGg/G1N7sNuAiC
    //$2a$10$Zb96zMe533AOklS1o0Za.urWtc3NcpiBb0EcA1wO9nRUowUmAYlae
    @Test
    public void test01(){
        System.out.println(passwordEncoder.encode("123"));
        boolean matches1 = passwordEncoder.matches("$2a$10$ogvUqZZAxrBwrmVI/e7.SuFYyx8my8d.9zJ6bs9lPKWvbD9eefyCe", "123");
        System.out.println(matches1);
//        System.out.println(matches2);

        //System.out.println(passwordEncoder.matches(passwordEncoder.encode("123"), "123"));
    }

}
