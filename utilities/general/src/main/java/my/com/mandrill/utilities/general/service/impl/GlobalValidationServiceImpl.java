package my.com.mandrill.utilities.general.service.impl;

import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.utilities.general.service.GlobalValidationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class GlobalValidationServiceImpl implements GlobalValidationService {

	@Override
	public String validateNullToLowerCase(String str) {
		return str == null ? "" : str.toLowerCase();
	}

}
