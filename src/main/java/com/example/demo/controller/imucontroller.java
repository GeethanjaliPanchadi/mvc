package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.models.garp;
import com.example.demo.repository.dragon;

import jakarta.servlet.http.HttpSession;

@Controller

public class imucontroller {
	@Autowired
	dragon repo;
	@GetMapping("/")
	public String home(Model m) {
		List<garp> e= (List<garp>) repo.findAll();
		m.addAttribute("add-products",e);
		return "home";
	}
	@GetMapping("/getbyid/{id}")
	public String getbyid(@PathVariable(value="id") int id,Model m) {
		Optional<garp> g = repo.findById(id);
		garp s = g.get();
		m.addAttribute("products",s);
		return "update";	
	}
	@PostMapping("/insert")
	public String insert(@ModelAttribute garp g,HttpSession session) {
		repo.save(g);
		session.setAttribute("message", "Success");
		return "redirect:/load";
	}
	@PutMapping("/update")
	public String edit(@ModelAttribute garp g,HttpSession session) {
		repo.save(g);
		session.setAttribute("message", "succesfully updated");
		return "redirect:/";
	}
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable(value="id") int id, HttpSession session) {
		repo.deleteById(id);
		session.setAttribute("message", "deleted");
		return "redirect:/";
	}
}
