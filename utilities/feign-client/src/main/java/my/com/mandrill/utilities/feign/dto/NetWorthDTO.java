package my.com.mandrill.utilities.feign.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NetWorthDTO {

	private BigDecimal assets;

	private BigDecimal liabilities;

}
