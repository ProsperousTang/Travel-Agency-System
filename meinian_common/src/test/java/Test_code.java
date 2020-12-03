import com.dtss.utils.SMSUtils;
import com.dtss.utils.ValidateCodeUtils;
import org.junit.Test;

public class Test_code {
    @Test
    public void test()  throws Exception{
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        System.out.println("code: "+code);
        SMSUtils.sendShortMessage("132******96",String.valueOf(code));
    }
}
