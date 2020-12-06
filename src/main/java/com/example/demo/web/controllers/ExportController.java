package com.example.demo.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.RacerService;
import com.example.demo.service.TownService;

@Controller
@RequestMapping("/export")
public class ExportController extends BaseController {

	private final TownService townService;
	private final RacerService racerService;

	public ExportController(TownService townService, RacerService racerService) {
		this.townService = townService;
		this.racerService = racerService;
	}

	@GetMapping("/racing-towns")
	public ModelAndView exportRacingTowns() {
		return super.view("export/export-racing-towns", "racingTowns", this.townService.exportRacingTowns());
	}

	@GetMapping("/racing-cars")
	public ModelAndView exportRacingCars() {
		return super.view("export/export-racing-cars", "racingCars", this.racerService.exportRacingCars());
	}
}
