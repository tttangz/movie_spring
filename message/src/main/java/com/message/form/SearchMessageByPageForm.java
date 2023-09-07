package com.message.form;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

@ApiModel
@Data
public class SearchMessageByPageForm {
    @NotNull
    @Min(1)
    private Integer page;

    @NotNull
    @Range(min = 1,max = 40)
    private Integer length;
}
