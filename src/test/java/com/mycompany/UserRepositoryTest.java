package com.mycompany;

import com.mycompany.users.User;
import com.mycompany.users.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace =AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew(){
        User user =new User();
        user.setEmail("123@gmail.com");
        user.setPassword("12345");
        user.setFirstname("Dinithi");
        user.setLastname("Tharushini");

        User saveduser=repo.save(user);
        Assertions.assertThat(saveduser).isNotNull();
        Assertions.assertThat(saveduser.getId()).isGreaterThan(0);

    }
    @Test
    public void testListAll(){
        Iterable<User> users=repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user:users){
            System.out.println(user);
        }
    }

    @Test
    public void testupdate(){
        int userId=1;
        Optional<User> optionalUser=repo.findById(userId);
        User user=optionalUser.get();
        user.setPassword("2000");
        repo.save(user);

        User updatedUser=repo.findById(userId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("2000");

    }
}
