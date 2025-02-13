package com.picpaysimplificado.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaysimplificado.dtos.NotificationDTO;
import com.picpaysimplificado.entities.User;

@Service
public class NotificationService {

	@Autowired
	private RestTemplate restTemplate;

	public void sendNotification(User user, String message) throws Exception {
		String email = user.getEmail();
		NotificationDTO notificationRequest = new NotificationDTO(email, message);

		ResponseEntity<String> notifiationResponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify",
				notificationRequest, String.class);
		
		if(!(notifiationResponse.getStatusCode() == HttpStatus.OK)) {
			throw new Exception("Serviço de notificação fora do ar");
		}
	}
}
