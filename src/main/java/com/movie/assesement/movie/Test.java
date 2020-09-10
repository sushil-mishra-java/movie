package com.movie.assesement.movie;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
	@GetMapping("/hi")
	public String show() {
		return "welcome";
	}

}
