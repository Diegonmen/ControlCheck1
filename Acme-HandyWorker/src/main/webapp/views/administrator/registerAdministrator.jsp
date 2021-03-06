<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<head>
	<spring:message code="actor.confirmPhone" var="phoneConfirm"/>
	<script>
		$(document).ready(function() {
		 $("#formID").submit(function(){
			var m = document.getElementById("phone").value;
			var expreg = /^(\+\d{1,3})?\s?(\(\d{3}\)\s)?\s?\d{4,100}$/;
			
			if(!expreg.test(m)){
				
				return confirm($('#phoneConfirm').val());
			}
		});
		});
	</script>
</head>

<input id="phoneConfirm" type="hidden" value="${phoneConfirm}"/>

<form:form action="administrator/editAdministrator.do" id="formID" modelAttribute="administrator">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="suspicious" />
	<form:hidden path="userAccount" />
	<form:hidden path="socialIdentity" />
	<form:hidden path="boxes" />
	
	<form:hidden path="userAccount.authorities" />
	
	<div class="form-group"> 
		<form:label path="userAccount.username">
			<spring:message code="administrator.userAccount.username" />
		</form:label>
		<form:input class="form-control" path="userAccount.username" />
		<form:errors class="text-danger" path="userAccount.username" />
	</div>
	<div class="form-group"> 
		<form:label path="userAccount.password">
			<spring:message code="administrator.userAccount.password" />
		</form:label>
		<form:password class="form-control" path="userAccount.password" />
		<form:errors class="text-danger" path="userAccount.password" />
	</div>

	<hr />
	
	<div class="form-group"> 
		<form:label path="name">
			<spring:message code="administrator.name" />
		</form:label>
		<form:input class="form-control" path="name" />
		<form:errors class="text-danger" path="name" />
	</div>
	<div class="form-group"> 
		<form:label path="middleName">
			<spring:message code="administrator.middleName" />
		</form:label>
		<form:input class="form-control" path="middleName" />
		<form:errors class="text-danger" path="middleName" />
	</div>
	<div class="form-group"> 
		<form:label path="surname">
			<spring:message code="administrator.surname" />
		</form:label>
		<form:input class="form-control" path="surname" />
		<form:errors class="text-danger" path="surname" />
	</div>
	<div class="form-group"> 
		<form:label path="email">
			<spring:message code="administrator.email" />
		</form:label>
		<form:input class="form-control" path="email" />
		<form:errors class="text-danger" path="email" />
	</div>
	<div class="form-group"> 
		<form:label path="photo">
			<spring:message code="administrator.photo" />
		</form:label>
		<form:input class="form-control" path="photo" />
		<form:errors class="text-danger" path="photo" />
	</div>
	<div class="form-group"> 
		<form:label path="phoneNumber">
			<spring:message code="administrator.phoneNumber" />
		</form:label>
		<form:input class="form-control" id="phone" path="phoneNumber" />
		<form:errors class="text-danger" path="phoneNumber" />
	</div>
	<div class="form-group"> 
		<form:label path="address">
			<spring:message code="administrator.address" />
		</form:label>
		<form:input class="form-control" path="address" />
		<form:errors class="text-danger" path="address" />
	</div>
	
	<br/>
	
	<input type="submit" class="ui primary button" name="save"
			value="<spring:message code="administrator.save" />">
	
	<input type="button" class="ui button" name="cancel"
		value="<spring:message code="administrator.cancel" />"
		onclick="javascript: relativeRedir('welcome/index.do');">
	
</form:form>