package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

import static org.assertj.core.api.Assertions.*;

public class MessageCodesResolverTest {
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodesResolverObject() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        for (String messageCode : messageCodes) {

            System.out.println("messageCodes = " + messageCode);
        }

        assertThat(messageCodes).containsExactly("required.item", "required");
    }

    @Test
    void messageCodesResolverField() {
        String[] messageCodes = codesResolver.resolveMessageCodes(
                "required", "item", "itemName", String.class);
        for (String messageCode : messageCodes) {

            System.out.println("messageCodes = " + messageCode);
        }
        //bindingResult.rejectValue("itemName", "required");
        //codesResolver 호출하여 자세한 오류코드부터 차례대로 구해, new FieldError 객체 생성시 codes 파라미터에 넘긴다.
        assertThat(messageCodes).containsExactly(
                "required.item.itemName",
                        "required.itemName",
                        "required.java.lang.String",
                        "required");
    }

}
