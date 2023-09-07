package com.message.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel
@Data
public class UpdateUnreadMessageForm {
    @NotBlank
    private String id;
}
