package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import domain.Quolet;
import repositories.QuoletRepository;

public class StringToQuoletConverter implements Converter<String, Quolet> {

	@Autowired
	QuoletRepository quoletRepository;

	@Override
	public Quolet convert(String text) {
		Quolet result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.quoletRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
