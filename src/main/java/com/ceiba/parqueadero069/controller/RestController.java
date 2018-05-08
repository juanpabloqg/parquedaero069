package com.ceiba.parqueadero069.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {
	
	@GetMapping("/checkrest")
	public ResponseEntity<String> chechRest(){
		
		return new ResponseEntity<String>("OK", HttpStatus.OK);
		
	}
	
	@GetMapping("/holamundo")
	public ModelAndView holaMundo() {
		
		return new ModelAndView("holamundo");
	}

}
