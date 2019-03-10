package com.egakat.core.mail.service.api;

import com.egakat.core.mail.dto.MailMessageDto;

public interface MailService {

	void sendMail(MailMessageDto mailMessage);
}