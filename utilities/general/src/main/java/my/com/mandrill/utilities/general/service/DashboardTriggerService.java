package my.com.mandrill.utilities.general.service;

import my.com.mandrill.utilities.general.dto.model.DashboardActivityMessage;

public interface DashboardTriggerService {

	void send(DashboardActivityMessage dashboardActivityMessage);

}
