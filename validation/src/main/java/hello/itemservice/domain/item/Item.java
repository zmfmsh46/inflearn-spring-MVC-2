package hello.itemservice.domain.item;

//jakarta.validation 으로 시작하면 특정 구현에 관계없이 제공되는 표준 인터페이스
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
//org.hibernate.validator 로 시작하면 하이버네이트 validator 구현체를 사용할 때만 제공되는 검증 기능
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

@Data
public class Item {
/*
    @NotNull(groups = UpdateCheck.class)
    private Long id;

    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class}, message = "공백X")
    private String itemName;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Range(groups = {SaveCheck.class, UpdateCheck.class}, min = 1000, max = 1000000)
    private Integer price; // A, typeMismatch로 FieldError 추가

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Max(groups = SaveCheck.class, value = 9999)
    private Integer quantity;
 */


    private Long id;

    private String itemName;

    private Integer price;

    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
