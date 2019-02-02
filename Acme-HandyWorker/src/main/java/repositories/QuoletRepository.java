package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.FixUpTask;
import domain.Quolet;

public interface QuoletRepository extends JpaRepository<Quolet, Integer> {

	@Query("select avg(f.quolets.size)*1.0, sqrt(sum(f.quolets.size * f.quolets.size)/count(f.quolets.size)*1.0 - (avg(f.quolets.size)*avg(f.quolets.size)*1.0)) from Customer c join c.fixUpTasks f")
	Double[] findAvgMinMaxStrDvtQuoletsPerUser();

	@Query("select avg(c.quolets.size)*1.0, sqrt(sum(c.quolets.size * c.quolets.size)/count(c.quolets.size)*1.0 - (avg(c.quolets.size)*avg(c.quolets.size)*1.0)) from FixUpTask c")
	Double[] findAvgMinMaxStrDvtQuoletsPerFixUpTask();

	@Query("select (count(f) * 1.0 / (select count(fi) from Quolet fi)) from Quolet f where f.finalMode=true")
	Double ratioPublishedQuolets();

	@Query("select (count(f) * 1.0 / (select count(fi) from Quolet fi)) from Quolet f where f.finalMode=false")
	Double ratioUnpublishedQuolets();

	@Query("select f from Quolet f where f.finalMode=true")
	Collection<Quolet> findPublishedQuolets();

	@Query("select f from Quolet f where f.finalMode=false")
	Collection<Quolet> findUnpublishedQuolets();

	@Query("select f from FixUpTask f join f.quolets q where q.id=?1")
	FixUpTask findFixUpTaskByQuolet(int quoletId);
}
