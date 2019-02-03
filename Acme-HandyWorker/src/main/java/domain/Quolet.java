
package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Quolet extends DomainEntity {

	private String ticker;
	private Date publicationMoment;
	private String body;
	private String picture;
	private Boolean finalMode;

	@Pattern(regexp = "^[0-9]{6}#\\d{1,3}$")
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	@DateTimeFormat(pattern = "yyyy/MM/dd hh:mm")
	public Date getPublicationMoment() {
		return this.publicationMoment;
	}

	public void setPublicationMoment(Date publicationMoment) {
		this.publicationMoment = publicationMoment;
	}

	@NotBlank
	@Length(min = 1, max = 13)
	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@URL
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Boolean getFinalMode() {
		return this.finalMode;
	}

	public void setFinalMode(Boolean finalMode) {
		this.finalMode = finalMode;
	}

	// Relationships
}
