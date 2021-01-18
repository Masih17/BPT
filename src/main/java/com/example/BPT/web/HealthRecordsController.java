package com.example.BPT.web;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.BPT.domain.HealthRecords;
import com.example.BPT.domain.HealthRecordsRepository;

@Controller
public class HealthRecordsController {

	@Autowired
	private HealthRecordsRepository repository;

	@RequestMapping(value = "/login")
	public String login() {
		return "login";

	}

	// Home page
	@RequestMapping(value = { "/", "/home" })
	public String recordList(Model model) {
		model.addAttribute("bprecords", repository.findByOrderByDateDesc());
		return "home";
	}

	// Add new records
	@GetMapping("/add")
	public String addRecord(Model model) {
		model.addAttribute("bprecords", new HealthRecords("username", 0, 0, 0, LocalDate.now()));
		return "addrecord";

	}

	// Save the new records
	@PostMapping(value = "/save")
	public String save(HealthRecords newRecord) {
		repository.save(newRecord);
		return "redirect:home";
	}

	// Delete records
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Long recordId, Model model) {
		repository.deleteById(recordId);
		return "redirect:../";
	}

	// Modifying records
	@RequestMapping(value = "/modify/{id}")
	public String modifyRecord(@PathVariable("id") Long recordId, Model model) {
		Optional<HealthRecords> record = repository.findById(recordId);
		model.addAttribute("bprecords", record);
		return "edit";
	}
	
	//REST controller
	@GetMapping("/api/records")
	public @ResponseBody List<HealthRecords> recordListRest() {
		return (List<HealthRecords>) repository.findAll();
	}

}
