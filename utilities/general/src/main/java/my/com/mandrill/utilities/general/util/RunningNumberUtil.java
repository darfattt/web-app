package my.com.mandrill.utilities.general.util;

import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.utilities.general.constant.RegexConstants;
import my.com.mandrill.utilities.general.constant.RunningNumberModule;
import my.com.mandrill.utilities.general.constant.SubModuleRunningNumberCode;
import my.com.mandrill.utilities.general.domain.RunningNumber;
import my.com.mandrill.utilities.general.repository.RunningNumberRepository;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class RunningNumberUtil {

	private static final String NUMBER_YEAR_FORMAT = "yyyy";

	private static final String NUMBER_MONTH_FORMAT = "MM";

	private static final String NUMBER_DAY_FORMAT = "dd";

	private static final String TYPE_ADMIN = "0";

	private static final String TYPE_USER = "1";

	private static final String SHORT_YEAR_FORMAT = "yy";

	private final RunningNumberRepository runningNumberRepository;

	public RunningNumberUtil(@Autowired(required = false) RunningNumberRepository runningNumberRepository) {
		this.runningNumberRepository = runningNumberRepository;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String getLatestRunningNumber(String module, boolean isAdmin) {
		Optional<RunningNumber> runningNumberOptional = runningNumberRepository.findFirstByModule(module);
		if (runningNumberOptional.isPresent()) {
			StringBuilder stringBuilder = new StringBuilder();

			RunningNumber runningNumber = runningNumberOptional.get();

			if (StringUtils.isNotBlank(runningNumber.getPrefix())) {
				stringBuilder.append(runningNumber.getPrefix());
			}

			String date = parseDate(runningNumber.getIncludeDay());
			String type = isAdmin ? TYPE_ADMIN : TYPE_USER;
			String seq = generateSeq(runningNumber);

			runningNumberRepository.save(runningNumber);

			return stringBuilder.append(date).append(type).append(seq).toString();
		}
		else
			return null;
	}

	private String parseDate(boolean includeDay) {
		DateTime datetime = DateTime.now(DateTimeZone.UTC);
		StringBuilder str = new StringBuilder();

		str.append(datetime.toString(NUMBER_YEAR_FORMAT)).append(datetime.toString(NUMBER_MONTH_FORMAT));
		if (includeDay) {
			str.append(datetime.toString(NUMBER_DAY_FORMAT));
		}

		return str.toString();
	}

	private String generateSeq(RunningNumber runningNumber) {

		String seq = String.format(runningNumber.getFormat(), runningNumber.getNext());
		if (seq.matches(RegexConstants.ONLY_NINE)) {
			runningNumber.setNext(1L);
		}
		else {
			runningNumber.setNext(runningNumber.getNext() + 1L);
		}

		return seq;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String generateInstitutionRefNo() {
		Optional<RunningNumber> runningNumberOptional = runningNumberRepository
				.findFirstByModule(RunningNumberModule.INSTITUTION_REF_NO.name());
		log.info("runningNumberOptional > " + runningNumberOptional);
		if (runningNumberOptional.isPresent()) {
			StringBuilder stringBuilder = new StringBuilder();

			DateTime datetime = DateTime.now(DateTimeZone.UTC);

			RunningNumber runningNumber = runningNumberOptional.get();
			String seq = generateSeq(runningNumber);

			runningNumberRepository.save(runningNumber);

			return stringBuilder.append(datetime.toString(SHORT_YEAR_FORMAT)).append(seq).toString();
		}
		else
			return null;
	}

	@Transactional
	public void createInstitutionIncidentReportRunningNumber(String institutionId) {
		RunningNumber runningNumber = new RunningNumber();
		runningNumber.setModule(RunningNumberModule.INSTITUTION_INCIDENT_REPORT_RUNNING_NUMBER.name());
		runningNumber.setNext(1l);
		runningNumber.setPrefix(SubModuleRunningNumberCode.IR.name());
		runningNumber.setFormat("%03d");
		runningNumber.setInstitutionId(institutionId);
		runningNumberRepository.save(runningNumber);
	}

	@Transactional
	public void createInstitutionCommunityInvestmentRunningNumber(String institutionId) {
		RunningNumber runningNumber = new RunningNumber();
		runningNumber.setModule(RunningNumberModule.INSTITUTION_COMMUNITY_INVESTMENT_RUNNING_NUMBER.name());
		runningNumber.setNext(1l);
		runningNumber.setPrefix(SubModuleRunningNumberCode.CI.name());
		runningNumber.setFormat("%03d");
		runningNumber.setInstitutionId(institutionId);
		runningNumberRepository.save(runningNumber);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String generateInstitutionIncidentReportRunningNo(String institutionId, String institutionRefNo) {
		Optional<RunningNumber> runningNumberOptional = runningNumberRepository.findByModuleAndInstitutionId(
				RunningNumberModule.INSTITUTION_INCIDENT_REPORT_RUNNING_NUMBER.name(), institutionId);
		if (runningNumberOptional.isPresent()) {
			StringBuilder stringBuilder = new StringBuilder();

			DateTime datetime = DateUtil.now();

			RunningNumber runningNumber = runningNumberOptional.get();
			String seq = generateSeq(runningNumber);

			String separator = "-";

			runningNumberRepository.save(runningNumber);

			return stringBuilder.append(runningNumber.getPrefix()).append(separator).append(institutionRefNo)
					.append(separator).append(datetime.toString(SHORT_YEAR_FORMAT))
					.append(datetime.toString(NUMBER_MONTH_FORMAT)).append(seq).toString();
		}
		else
			return null;

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String generateInstitutionCommunityInvestmentRunningNo(String institutionId, String institutionRefNo) {
		Optional<RunningNumber> runningNumberOptional = runningNumberRepository.findByModuleAndInstitutionId(
				RunningNumberModule.INSTITUTION_COMMUNITY_INVESTMENT_RUNNING_NUMBER.name(), institutionId);
		if (runningNumberOptional.isPresent()) {
			StringBuilder stringBuilder = new StringBuilder();

			DateTime datetime = DateUtil.now();

			RunningNumber runningNumber = runningNumberOptional.get();
			String seq = generateSeq(runningNumber);

			String separator = "-";

			runningNumberRepository.save(runningNumber);

			return stringBuilder.append(runningNumber.getPrefix()).append(separator).append(institutionRefNo)
					.append(separator).append(datetime.toString(SHORT_YEAR_FORMAT))
					.append(datetime.toString(NUMBER_MONTH_FORMAT)).append(seq).toString();
		}
		else
			return null;

	}

	// for unit test only
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public String getTestRunningNumber() {
		String runningNumber = getLatestRunningNumber(RunningNumberModule.USER_REFERENCE.name(), true);
		return runningNumber;
	}

}
