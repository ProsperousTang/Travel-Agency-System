import com.dtss.utils.SMSUtils;
import com.dtss.utils.ValidateCodeUtils;
import org.junit.Test;

public class Test_code {
    @Test
    public void test()  throws Exception{
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        System.out.println("code: "+code);
        SMSUtils.sendShortMessage("13261509696",String.valueOf(code));
    }
}
