package services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.FixUpTask;
import domain.Quolet;
import repositories.QuoletRepository;

@Service
@Transactional
public class QuoletService {

	@Autowired
	private QuoletRepository quoletRepository;

	@Autowired
	private CustomerService customerService;

	public Quolet save(Quolet quolet) {
		FixUpTask fixuptask = this.findFixUpTaskByQuolet(quolet);
		Quolet saved = this.quoletRepository.save(quolet);
		fixuptask.getQuolets().remove(quolet);
		fixuptask.getQuolets().add(saved);
		this.customerService.saveCustomerFixUpTask(fixuptask);
		return saved;
	}

	public List<Quolet> findAll() {
		return this.quoletRepository.findAll();
	}

	public Quolet findOne(Integer id) {
		return this.quoletRepository.findOne(id);
	}

	public boolean exists(Integer id) {
		return this.quoletRepository.exists(id);
	}

	public void delete(Integer id) {
		Quolet quolet = this.findOne(id);
		FixUpTask fixUpTask = this.findFixUpTaskByQuolet(quolet);
		fixUpTask.getQuolets().remove(quolet);
		this.customerService.saveCustomerFixUpTask(fixUpTask);
		this.quoletRepository.delete(id);
	}

	public Double[] findAvgMinMaxStrDvtQuoletsPerUser() {
		Double[] res = this.quoletRepository.findAvgMinMaxStrDvtQuoletsPerUser();
		return res;
	}

	public Double[] findAvgMinMaxStrDvtQuoletsPerFixUpTask() {
		Double[] res = this.quoletRepository.findAvgMinMaxStrDvtQuoletsPerFixUpTask();
		return res;
	}

	public Double findRatioPublishedQuolets() {
		Double res = this.quoletRepository.ratioPublishedQuolets();
		return res;
	}

	public Double findRatioUnpublishedQuolets() {
		Double res = this.quoletRepository.ratioUnpublishedQuolets();
		return res;
	}

	public Collection<Quolet> findPublishedQuolets() {
		Collection<Quolet> res = this.quoletRepository.findPublishedQuolets();
		return res;
	}

	public Collection<Quolet> findUnpublishedQuolets() {
		Collection<Quolet> res = this.quoletRepository.findUnpublishedQuolets();
		return res;
	}

	public FixUpTask findFixUpTaskByQuolet(Quolet quolet) {
		FixUpTask res = this.quoletRepository.findFixUpTaskByQuolet(quolet.getId());
		return res;
	}

	public Quolet create() {
		Quolet res = new Quolet();
		return res;
	}
}
