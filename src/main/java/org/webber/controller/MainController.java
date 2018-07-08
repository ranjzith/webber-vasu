package org.webber.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @RequestMapping("/")
    public String greeting(@RequestParam(value="p", defaultValue="main") String page) {
    	try {
			System.out.println(page);
			return page;
		} catch (Exception ex) {
			return "errorpage";
		}
    }
}
