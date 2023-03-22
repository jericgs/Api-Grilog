package com.grilog.grilogapi.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.grilog.grilogapi.domain.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@Entity
public class Client {

    private static final int MAX_CONTENT_NAME = 60;
    private static final int MAX_CONTENT_MAIL = 255;
    private static final int MAX_CONTENT_PHONE = 20;

    @EqualsAndHashCode.Include
    @Id
    @NotNull(groups = ValidationGroups.ClientId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = MAX_CONTENT_NAME)
    private String name;

    @NotBlank
    @Size(max = MAX_CONTENT_MAIL)
    @Email
    private String email;

    @NotBlank
    @Size(max = MAX_CONTENT_PHONE)
    private String phone;

}
