package com.example.kodomoproject.domain.qr.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QrRequest {

    @NotBlank
    @URL(message = "올바른 URL 형식이 아닙니다.")
    private String url;

}
