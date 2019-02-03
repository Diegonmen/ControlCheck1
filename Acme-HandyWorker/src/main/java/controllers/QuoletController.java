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
import services.CustomerService;
import services.FixUpTaskService;
import services.QuoletService;

@Controller
@RequestMapping("/quolet")
public class QuoletController extends AbstractController {

	@Autowired
	private QuoletService quoletService;

	@Autowired
	private FixUpTaskService fixUpTaskService;

	@Autowired
	private CustomerService customerService;

	public QuoletController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int fixUpTaskId) {
		ModelAndView result;
		Quolet quolet;

		quolet = this.quoletService.create();
		result = this.createEditModelAndView(quolet);
		result.addObject("fixUpTaskId", fixUpTaskId);
		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int fixUpTaskId) {
		ModelAndView model = new ModelAndView("quolet/list");
		Collection<Quolet> quolets = this.fixUpTaskService.findOne(fixUpTaskId).getQuolets();
		model.addObject("list", quolets);
		model.addObject("fixUpTaskId", fixUpTaskId);

		return model;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int quoletId) {
		ModelAndView model = new ModelAndView("quolet/display");
		Quolet quolet = this.quoletService.findOne(quoletId);
		model.addObject("quolet", quolet);
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
	public ModelAndView save(@RequestParam(required = false) Integer fixUpTaskId, @Valid Quolet quolet,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(quolet);
			for (ObjectError e : binding.getAllErrors())
				System.out.println(
						e.getObjectName() + " error [" + e.getDefaultMessage() + "] " + Arrays.toString(e.getCodes()));
		} else
			try {
				if (!this.quoletService.exists(quolet.getId())) {
					FixUpTask fixUpTask = this.fixUpTaskService.findOne(fixUpTaskId);
					fixUpTask.getQuolets().add(quolet);
					this.customerService.saveCustomerFixUpTask(fixUpTask);
					result = new ModelAndView("redirect:/quolet/list.do?fixUpTaskId=" + fixUpTaskId);

				} else {
					FixUpTask fixUpTask = this.quoletService.findFixUpTaskByQuolet(quolet);
					this.quoletService.save(quolet, fixUpTask);
					result = new ModelAndView("redirect:/quolet/list.do?fixUpTaskId=" + fixUpTask.getId());
				}
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
