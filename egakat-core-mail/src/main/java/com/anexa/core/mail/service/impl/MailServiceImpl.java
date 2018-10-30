package com.anexa.core.mail.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.anexa.core.mail.configuration.MailProperties;
import com.anexa.core.mail.dto.MailMessageDto;
import com.anexa.core.mail.service.api.MailService;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

	@Autowired
	private MailProperties properties;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendMail(MailMessageDto mailMessage) {
		try {
			MimeMessage message = this.mailSender.createMimeMessage();

			// @formatter:off
			message = crearMimeMessage(
					message, 
					properties.getFrom(), 
					properties.getFromPersonal(), 
					properties.getTo(), 
					properties.getCc(), 
					mailMessage.getAsunto(), 
					mailMessage.getContenido(),
					true, 
					mailMessage.getAttachments());
			// @formatter:on

			this.mailSender.send(message);
		} catch (RuntimeException e) {
			log.error("catch (" + e.getClass().getSimpleName() + " e), exception:" + e.getMessage());
			throw e;
		}
	}

	static private MimeMessage crearMimeMessage(MimeMessage message, String from, String fromPersonal, String to[],
			String cc[], String subject, String content, boolean html, File attachments[]) {

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(from, fromPersonal);
			helper.setTo(Arrays.stream(to).filter(s -> !s.isEmpty()).toArray(String[]::new));
			helper.setCc(Arrays.stream(cc).filter(s -> !s.isEmpty()).toArray(String[]::new));
			helper.setSubject(subject);
			helper.setText(content, html);

			for (File attachment : attachments) {
				// FileSystemResource file = new FileSystemResource(attachment);
				val name = attachment.getName();
				helper.addAttachment(name, attachment);
			}

			return message;
		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new RuntimeException("Ocurrió una excepción al intentar crear el mensaje de correo electrónico", e);
		}
	}
}
