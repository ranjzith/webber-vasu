package org.webber.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.webber.ApplicationProperties;
import org.webber.util.CommonUtils;

@org.springframework.web.bind.annotation.RestController
public class DashboardController {

private ApplicationProperties appConfig;
	
	@Autowired
	public void setAppConfig(ApplicationProperties appConfig) {
		this.appConfig = appConfig;
	}
	
	@GetMapping(value = "/getDashboardDetails")
    public String getDashboardDetails() {
    	JSONObject jsonRes = new JSONObject();
		try {
			
			jsonRes.put("inprocess", this.getListofFiles(this.appConfig.getProcessDirPath() + "/inprocess"));
			jsonRes.put("completed", this.getListofFiles(this.appConfig.getProcessDirPath() + "/completed"));
			jsonRes.put("failed", this.getListofFiles(this.appConfig.getProcessDirPath() + "/failed"));
			
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
	
	private JSONArray getListofFiles(String path) throws Exception {
		JSONArray array = new JSONArray();
		StringBuilder cmd = new StringBuilder();
		
		// DO NOT CHANGE THE ORDER
		cmd.append("ls -lt ");
		cmd.append(path);
		
		System.out.println(cmd.toString());
		Process p = Runtime.getRuntime().exec(cmd.toString());
		p.waitFor();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
	    BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));

	    String stdout = null;
	    
	    while ((stdout = reader.readLine()) != null) {
	        String[] tokens = StringUtils.tokenizeToStringArray(stdout, " ");
	        if(tokens != null && tokens.length >= 9) {
	        	JSONObject newObj = new JSONObject();
	        	newObj.put("exectime", tokens[5] + " " + tokens[6] + " " + tokens[7]);
	        	newObj.put("filename", tokens[8]);
	        	array.put(newObj);
	        }
	    }
	    
	    String stderr = "";
	    while ((stderr = errorReader.readLine()) != null) {
	        System.out.println(stderr);
	    }
	    return array;
	}
	
	@GetMapping(value = "/getLog")
    public String getLog(@RequestParam(value="filename", defaultValue="") String fileName, @RequestParam(value="pstate", defaultValue="") String pstate) {
    	JSONObject jsonRes = new JSONObject();
		try {
			JSONObject reqJson = new JSONObject();
			System.out.println(reqJson);
			StringBuilder cmd = new StringBuilder();
			
			// DO NOT CHANGE THE ORDER
			cmd.append("cat ");
			cmd.append(this.appConfig.getProcessDirPath() + "/" + pstate + "/" + fileName);
			
			System.out.println(cmd.toString());
			Process p = Runtime.getRuntime().exec(cmd.toString());
			
			p.waitFor();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));

		    String stdout = null;
		    StringBuilder log = new StringBuilder();
		    
		    while ((stdout = reader.readLine()) != null) {
		        log.append(stdout);
		        log.append("\n");
		    }
		    
		    String stderr = "";
		    while ((stderr = errorReader.readLine()) != null) {
		        System.out.println(stderr);
		    }
		    
		    jsonRes.put("log", log);
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
