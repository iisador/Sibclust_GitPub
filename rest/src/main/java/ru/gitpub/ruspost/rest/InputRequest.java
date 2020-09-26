package ru.gitpub.ruspost.rest;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "InputRequest", description = "Запрос на построение цепочки поставки")
public class InputRequest {

    @ApiModelProperty(value = "Откуда", example = "24cae1ed-f158-4c9d-a982-09d19b2410eb6")
    private String fromGeozone;

    @ApiModelProperty(value = "Куда", example = "83ecf082-d29d-45f3-9f5f-1dedaa3f49b0")
    private String toGeozone;

    @ApiModelProperty(value = "Дата", example = "26.09.2020")
    private LocalDate fromDtm;
}
