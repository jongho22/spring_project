package ch06_sjh_01.sjh.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch06_sjh_01.ems.member.DBConnectionInfo;
import ch06_sjh_01.ems.member.service.EMSInformationService;

@Configuration
public class MemberConfig3 {
	
	@Autowired
	DBConnectionInfo dev_DBConnectionInfoDev;
	
	@Autowired
	DBConnectionInfo real_DBConnectionInfoDev;
	
	
	@Bean
	public EMSInformationService eMSInformationService() {
		
		EMSInformationService emsInformationService = new EMSInformationService();
		emsInformationService.setInfo("Education Management System program was in 2023.");
		emsInformationService.setCopyRight("COPYRIGHT(C) 2022 EMS CO., LTD. ALL RIGHT RESERVED. CONTACT MASTER FOR MORE INFORMATION.");
		emsInformationService.setVer("The version is 1.0");
		emsInformationService.setsYear(2023);
		emsInformationService.setsMonth(12);
		emsInformationService.setsDay(6);
		emsInformationService.seteYear(2023);
		emsInformationService.seteMonth(12);
		emsInformationService.seteDay(7);
		
		List<String> developers = new ArrayList<String>();
		developers.add("Cheney.");
		developers.add("Eloy.");
		developers.add("Jasper.");
		developers.add("Dillon.");
		developers.add("Kian.");
		emsInformationService.setDevelopers(developers);
		
		Map<String, String> administrators = new HashMap<String, String>();
		administrators.put("Cheney", "cheney@springSjh.org");
		administrators.put("Jasper", "jasper@springSjh.org");
		emsInformationService.setAdministrators(administrators);
		
		Map<String, DBConnectionInfo> dbInfos = new HashMap<String, DBConnectionInfo>();
		dbInfos.put("dev", dev_DBConnectionInfoDev);
		dbInfos.put("real", real_DBConnectionInfoDev);
		emsInformationService.setDbInfos(dbInfos);
		
		return emsInformationService;
	
	}
}
