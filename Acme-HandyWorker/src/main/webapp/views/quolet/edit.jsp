<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="quolet/edit.do" modelAttribute="quolet">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ticker"/>
	<form:hidden path="publicationMoment"/>
	
	<div> 
		<form:label path="body">
			<spring:message code="quolet.body" />
		</form:label>
		<form:input path="body" />
		<form:errors path="body"/>
	</div>
	
	<div> 
		<form:label path="picture">
			<spring:message code="quolet.picture" />
		</form:label>
		<form:input path="picture" />
		<form:errors path="picture"/>
	</div>
	
	<div> 
		<form:label path="finalMode">
			<spring:message code="quolet.finalMode" />
		</form:label>
		<form:radiobutton path="finalMode" value="true"/><spring:message code="quolet.finalModeTrue" />
		<form:radiobutton path="finalMode" value="false"/><spring:message code="quolet.finalModeFalse" />
	</div>
	
	<input type="submit" name="save" value="<spring:message code="quolet.save" />">
	
	<jstl:if test="${quolet.finalMode==false }">
			<input type="submit" class="btn btn-warning" name="delete" value="<spring:message code="quolet.delete" />">
	</jstl:if>
 	
	<input type="button" name="cancel" value="<spring:message code="quolet.cancel" />" onclick="javascript: relativeRedir('welcome/index.do')">
	
</form:form>