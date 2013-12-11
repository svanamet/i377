package controllers;


import javax.annotation.Resource;

import models.Unit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import view.UnitForm;
import dao.Dao;


@Controller
public class WebController {
	
	@Resource
	private Dao dao;
	
	@RequestMapping("/")
	public String Index() {
		
		return "redirect:/search";
	}
	
	@RequestMapping("/addForm")
	 public String showForm(@ModelAttribute("unitForm") UnitForm form,
	   ModelMap model) {
		
		model.addAttribute("units", dao.findAllUnits());
		
	  return "addForm";
	 }
	
	@RequestMapping("/search")
	public String Search(@RequestParam(required=false, value="searchString") String search, ModelMap model) {
		
		if (search != null){
			model.addAttribute("units", dao.search(search));
		} else
			model.addAttribute("units", dao.findAllUnits());
		

		
		return "search";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	 public String saveForm(@ModelAttribute("unitForm") UnitForm form,
	    ModelMap model) {
		
			Unit unit = new Unit();
			unit = form.getUnit();
			
			if(!form.getSuperUnitCode().equals("")){				
			unit.setSuper_unit_id(dao.findByCode(form.getSuperUnitCode()).getId());
			}
	  		dao.addUnit(unit);
	  		
		return "redirect:/";
	}
	
	@RequestMapping("/view/{code}")
	public String viewForm(@PathVariable("code") String code, @ModelAttribute("unitForm") UnitForm form, ModelMap model) {

		
		
		 Unit unit = dao.findByCode(code);
		 model.addAttribute("name", unit.getName());
		   form.setUnit(unit);
		   Unit superUnit;
		   if (unit.getSuper_unit_id() == null) {
		    superUnit = new Unit();
		   } else {
		    superUnit = dao.findById(unit.getSuper_unit_id());
		   }
		   System.out.println(superUnit);
		   model.addAttribute("super_name", superUnit);
		   model.addAttribute("codes",
		     dao.findSubUnitsById(unit.getId()));

		return "view";
	}
	
	@RequestMapping("/delete/{code}")
	public String Delete(@PathVariable("code") String code, ModelMap model) {
		
		Unit unit = dao.findByCode(code);
		dao.deleteUnit(unit.getId());
 
		return "redirect:/";
	}
	
	@RequestMapping("/admin/clearData")
	public String ClearData() {
		
		dao.deleteAllUnits();
		
		return "redirect:/search";
	}
	
}
