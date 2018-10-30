package com.anexa.core.mail.service.api;

import com.anexa.core.mail.dto.MailMessageDto;

public interface MailService {

	void sendMail(MailMessageDto mailMessage);
}