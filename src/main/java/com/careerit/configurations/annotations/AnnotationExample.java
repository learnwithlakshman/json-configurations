package com.careerit.configurations.annotations;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jasypt.util.password.BasicPasswordEncryptor;

import java.lang.annotation.*;
import java.lang.reflect.Field;

@Getter
@Setter
@Builder
class Patron{
    private String name;
    private String userId;
    private String password;

    @Override
    public String toString(){
        return "Name :"+name+" UserId :"+userId;
    }
}

class PasswordEncodeUtil{
    private static final BasicPasswordEncryptor obj = new BasicPasswordEncryptor();
    public static String encode(String plainPassword){
        return obj.encryptPassword(plainPassword);
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Author{
        String name() default "User";
        String email() default "user@xyz.com";


}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface  AutoWired{

}

class One{
    public void show(){
        System.out.println("Show method of one");
    }
}

class Two{
    @AutoWired
    private One one;
    public void setOne(One one){
        this.one = one;
        System.out.println("Setter method is called");
    }
}

// @AutoWired
// @Service
// @Controller
// @RestController

@Author(name="Krish",email = "krish.t@gmail.com")
public class AnnotationExample {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Patron patron = Patron.builder().name("Krish").userId("krish.t@gmail.com").password(PasswordEncodeUtil.encode("Krish")).build();
        System.out.println(patron);

        Class cls = AnnotationExample.class;
        Author author = (Author) cls.getAnnotation(Author.class);
        System.out.println(author.name());
        System.out.println(author.email());




    }
}
