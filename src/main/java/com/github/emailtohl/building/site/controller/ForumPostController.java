package com.github.emailtohl.building.site.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.emailtohl.building.common.jpa.Pager;
import com.github.emailtohl.building.site.dao.SearchResult;
import com.github.emailtohl.building.site.dto.ForumPostDto;
import com.github.emailtohl.building.site.service.ForumPostService;

/**
 * 论坛控制器
 * 
 * @author HeLei
 */
@RestController
@RequestMapping("forum")
public class ForumPostController {
	private static final Logger logger = LogManager.getLogger();
	
	ForumPostService forumPostService;
	
	@Inject
	public ForumPostController(ForumPostService forumPostService) {
		super();
		this.forumPostService = forumPostService;
	}
	
	/*	@RequestMapping(value = "", method = RequestMethod.GET)
	public String form(Map<String, Object> model) {
		model.put("added", null);
		model.put("addForm", new PostForm());
		return "add";
	}
*/
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void add(@Valid ForumPostDto form, Errors e) {
		if (e.hasErrors()) {
			for (ObjectError oe : e.getAllErrors()) {
				logger.info(oe);
			}
			return;
		}
		this.forumPostService.save(form.getEmail(), form);
	}
/*
	@RequestMapping(value = "search")
	public String search(Map<String, Object> model) {
		model.put("searchPerformed", false);
		model.put("searchForm", new SearchForm());

		return "search";
	}
*/
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public Pager<SearchResult<ForumPostDto>> search(@RequestParam String query,
			@PageableDefault(page = 0, size = 20, sort = "id=desc") Pageable pageable) {
		return this.forumPostService.search(query, pageable);
	}

	@RequestMapping(value = "pager", method = RequestMethod.GET)
	Pager<ForumPostDto> getPager(@PageableDefault(page = 0, size = 20, sort = "id=desc") Pageable pageable) {
		return forumPostService.getPager(pageable);
	}
	
}
