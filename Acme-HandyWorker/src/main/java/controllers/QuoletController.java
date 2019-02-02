package controllers;

import java.util.Arrays;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.FixUpTask;
import domain.Quolet;
import services.FixUpTaskService;
import services.QuoletService;

@Controller
@RequestMapping("/quolet")
public class QuoletController extends AbstractController {

	@Autowired
	private QuoletService quoletService;

	@Autowired
	private FixUpTaskService fixUpTaskService;

	public QuoletController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Quolet quolet;

		quolet = this.quoletService.create();
		result = this.createEditModelAndView(quolet);
		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int fixUpTaskId) {
		ModelAndView model = new ModelAndView("quolet/list");
		Collection<Quolet> quolets = this.fixUpTaskService.findOne(fixUpTaskId).getQuolets();
		model.addObject("list", quolets);

		return model;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int quoletId) {
		ModelAndView result;
		Quolet quolet;

		quolet = this.quoletService.findOne(quoletId);
		Assert.notNull(quolet);
		result = this.createEditModelAndView(quolet);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Quolet quolet, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(quolet);
			for (ObjectError e : binding.getAllErrors())
				System.out.println(
						e.getObjectName() + " error [" + e.getDefaultMessage() + "] " + Arrays.toString(e.getCodes()));
		} else
			try {
				this.quoletService.save(quolet);
				FixUpTask fixUpTask = this.quoletService.findFixUpTaskByQuolet(quolet);
				result = new ModelAndView("redirect:/quolet/list.do?fixUpTaskId=" + fixUpTask.getId());
			} catch (Throwable oops) {
				result = this.createEditModelAndView(quolet, "quolet.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Quolet quolet, BindingResult binding) {
		ModelAndView result;
		try {
			FixUpTask fixUpTask = this.quoletService.findFixUpTaskByQuolet(quolet);
			this.quoletService.delete(quolet.getId());
			result = new ModelAndView("redirect:/quolet/list.do?fixUpTaskId=" + fixUpTask.getId());
		} catch (Throwable oops) {
			result = this.createEditModelAndView(this.quoletService.findOne(quolet.getId()), "warranty.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(Quolet quolet) {
		ModelAndView result;
		result = this.createEditModelAndView(quolet, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(Quolet quolet, String messageCode) {
		ModelAndView result;

		if (quolet.getId() > 0)
			result = new ModelAndView("quolet/edit");
		else
			result = new ModelAndView("quolet/create");

		result.addObject("quolet", quolet);
		result.addObject("message", messageCode);

		return result;
	}

}
