import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerateCode {

    @Test
    public void t1(){
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        String encode = b.encode("test");
        System.out.println(encode);
    }
}
