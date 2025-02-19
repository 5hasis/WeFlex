package com.kh.finalteam1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.finalteam1.entity.GenreDto;
import com.kh.finalteam1.entity.ProgramFeatureDto;
import com.kh.finalteam1.repository.ClientDao;
import com.kh.finalteam1.repository.ContentDao;
import com.kh.finalteam1.repository.GenreDao;
import com.kh.finalteam1.repository.HomeSliderDao;
import com.kh.finalteam1.repository.ProgramFeatureDao;
import com.kh.finalteam1.service.HomeService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private HomeService homeService;
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private ContentDao contentDao;
	
	@Autowired
	private HomeSliderDao homeSliderDao;

	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("clientMonth", clientDao.monthMemberCount());
		model.addAttribute("clientYear", clientDao.yearMemberCount());
		model.addAttribute("contentMonth", contentDao.monthContentCount());
		model.addAttribute("contentYear", contentDao.yearContentCount());
		return "admin/index";
	}
	
	@Autowired
	private GenreDao genreDao;
	
	@GetMapping("/genre")
	public String genre(Model model) {
		List<GenreDto> genreList = genreDao.list();
		model.addAttribute("genreList", genreList);
		return "admin/genre";
	}
	
	@GetMapping("/insertGenre")
	public String insertGenre(@RequestParam String genreName) {
		genreDao.insert(genreName);
		return "redirect:/admin/genre";
	}
	
	@GetMapping("/genreEdit")
	public String genreEdit(@RequestParam String genreName, Model model) {
		List<GenreDto> genreList = genreDao.list();
		model.addAttribute("genreList", genreList);
		model.addAttribute("editGenreName", genreName);
		return "admin/genreEdit";
	}
	
	@PostMapping("/genreEditComplete")
	public String genreEditComplete(@ModelAttribute GenreDto genreDto) {
		boolean isChange = genreDao.edit(genreDto);
		if(isChange) {
			return "redirect:/admin/genre";
		}
		else {
			return "admin/genreEdit?genreName="+genreDto.getGenreName();
		}
	}
	
	@GetMapping("/genreDelete")
	public String genreDelete(@RequestParam String genreName, Model model) {
		boolean isDelete = genreDao.delete(genreName);
		if(isDelete) {
			return "redirect:/admin/genre";
		}
		else {
			model.addAttribute("genreName", genreName);
			return "genreEdit";
		}
	}
	
	@Autowired
	private ProgramFeatureDao programFeatureDao;
	
	@GetMapping("/feature")
	public String feature(Model model) {
		List<ProgramFeatureDto> featureList = programFeatureDao.list();
		model.addAttribute("featureList", featureList);
		return "admin/programFeature";
	}
	
	@GetMapping("/insertFeature")
	public String insertfeature(@RequestParam String featureName) {
		programFeatureDao.insert(featureName);
		return "redirect:/admin/feature";
	}
	
	@GetMapping("/featureEdit")
	public String featureEdit(@RequestParam String featureName, Model model) {
		List<ProgramFeatureDto> featureList = programFeatureDao.list();
		model.addAttribute("featureList", featureList);
		model.addAttribute("editFeatureName", featureName);
		return "admin/programFeatureEdit";
	}
	
	@PostMapping("/featureEditComplete")
	public String featureEditComplete(@ModelAttribute ProgramFeatureDto programFeatureDto) {
		boolean isChange = programFeatureDao.edit(programFeatureDto);
		if(isChange) {
			return "redirect:/admin/feature";
		}
		else {
			return "admin/featureEdit?featureName="+programFeatureDto.getFeatureName();
		}
	}
	
	@GetMapping("/featureDelete")
	public String featureDelete(@RequestParam String featureName, Model model) {
		boolean isDelete = programFeatureDao.delete(featureName);
		if(isDelete) {
			return "redirect:/admin/feature";
		}
		else {
			model.addAttribute("featureName", featureName);
			return "admin/programFeatureEdit";
		}
	}
	
	
	//회원 관리
	@GetMapping("/clientAdmin")
	public String clientAdmin() {
		return "admin/clientAdmin";
	}
		
	@GetMapping("/homeSetting")
	public String homeSetting(Model model) {
		model.addAttribute("sliderList", homeService.getSliderList());
		return "admin/homeSetting";
	}
	
	@PostMapping("/deleteHomeSlider")
	public String deleteHomeSlider(@RequestParam int homeSliderNo) {
		homeSliderDao.deleteHomeSlider(homeSliderNo);
		return "redirect: homeSetting";
	}
	
	@PostMapping("/homeSetting")
	public String homeSetting(@RequestParam int homeSliderNo,
			@RequestParam String sliderTitle,
			@RequestParam String contentType,
			@RequestParam String type,
			@RequestParam String keyword) {
		
		if(homeSliderNo == 0) {
			homeSliderDao.insertHomeSlider(sliderTitle, contentType, type, keyword);
		}
		else {
			homeSliderDao.updateHomeSlider(homeSliderNo, sliderTitle, contentType, type, keyword);
		}
		
		return "redirect: homeSetting";
	}

}
