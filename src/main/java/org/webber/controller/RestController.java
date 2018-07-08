package org.webber.controller;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.webber.ApplicationProperties;
import org.webber.util.CommonUtils;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	private ApplicationProperties appConfig;
	
	@Autowired
	public void setAppConfig(ApplicationProperties appConfig) {
		this.appConfig = appConfig;
	}
	
	@GetMapping(value = "/getConfig")
    public String getConfig() {
    	JSONObject jsonRes = new JSONObject();
		try {
			jsonRes.put("form1List1", appConfig.getForm1List1());
			jsonRes.put("form1List2", appConfig.getForm1List2());
			jsonRes.put("form2List1", appConfig.getForm2List1());
			jsonRes.put("form2List2", appConfig.getForm2List2());
			
			jsonRes.put("status", "success");
			System.out.println(jsonRes);
		} catch (Exception ex) {
			try {
				jsonRes.put("status", "error");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    return jsonRes.toString();
    }
	
	@PostMapping(value = "/createUser")
    public String createUser(@RequestBody Map<String, Object> jsonString) {
    	JSONObject jsonRes = new JSONObject();
		try {
			JSONObject reqJson = new JSONObject(jsonString);
			System.out.println(reqJson);
			StringBuilder cmd = new StringBuilder();
			
			// DO NOT CHANGE THE ORDER
			cmd.append("sh ");
			cmd.append(this.appConfig.getDriver() + " ");
			
			cmd.append(this.appConfig.getShOne() + " ");
			cmd.append(this.appConfig.getProcessDirPath() + " ");
			cmd.append(CommonUtils.getCorrelationID() + " ");
			
			cmd.append(reqJson.getString("host") + " ");
			cmd.append(reqJson.getString("db") + " ");
			cmd.append(reqJson.getString("uname") + " ");
			cmd.append(reqJson.getString("passwd"));
			System.out.println(cmd.toString());
			Process p = Runtime.getRuntime().exec(cmd.toString());
			
			jsonRes.put("status", "success");
		} catch (Exception ex) {
			try {
				jsonRes.put("status", "error");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			System.out.println(ex.getMessage());
		}
	    return jsonRes.toString();
    }
	
	@PostMapping(value = "/createDB")
    public String createDB(@RequestBody Map<String, Object> jsonString) {
    	JSONObject jsonRes = new JSONObject();
		try {
			JSONObject reqJson = new JSONObject(jsonString);
			System.out.println(reqJson);
			StringBuilder cmd = new StringBuilder();
			
			// DO NOT CHANGE THE ORDER
			cmd.append("sh ");
			cmd.append(this.appConfig.getDriver() + " ");
			
			cmd.append(this.appConfig.getShTwo() + " ");
			cmd.append(this.appConfig.getProcessDirPath() + " ");
			cmd.append(CommonUtils.getCorrelationID() + " ");
			
			cmd.append(reqJson.getString("host") + " ");
			cmd.append(reqJson.getString("db") + " ");
			cmd.append(reqJson.getString("uname") + " ");
			System.out.println(cmd.toString());
			Process p = Runtime.getRuntime().exec(cmd.toString());
			
			jsonRes.put("status", "success");
		} catch (Exception ex) {
			try {
				jsonRes.put("status", "error");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			System.out.println(ex.getMessage());
		}
	    return jsonRes.toString();
    }
}
