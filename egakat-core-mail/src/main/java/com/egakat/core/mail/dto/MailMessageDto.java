package com.egakat.core.mail.dto;

import java.io.File;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MailMessageDto {

	@NotNull
	@Size(max = 50)
	private String codigo;

	@NotNull
	private String asunto;

	@NotNull
	private String contenido;

	private File[] attachments;
}