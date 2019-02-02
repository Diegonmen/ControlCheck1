<%@page import="org.springframework.context.i18n.LocaleContextHolder"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="view" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>

	<jstl:set value="<%=LocaleContextHolder.getLocale() %>" var="locale"></jstl:set>

	<spring:message code="quolet.ticker" var="ticker" />
	<spring:message code="quolet.publicationMoment" var="publicationMoment" />
	<spring:message code="quolet.body" var="body" />
	<spring:message code="quolet.picture" var="picture" />

	<display:table name="list" id="row" requestURI="quolet/list.do" pagesize="3" class="displaytag">
	
	<display:column property="ticker" title="${ticker}" />
	<display:column title="${publicationMoment}" >
		<jstl:if test="${locale == 'en'}">${row.publicationMoment}</jstl:if>
		<jstl:if test="${locale == 'es'}">${row.spanishPublicationMoment}</jstl:if>
	</display:column>
	<display:column property="body" title="${body}" />
	<display:column property="picture" title="${picture}" />
	
	<display:column>
		<a href="quolet/edit.do?quoletId=${row.id}"> <spring:message code="quolet.edit" />
		</a>
	</display:column>
	</display:table>
	
	<input type="button" class="ui button" name="create"
		value="<spring:message code="quolet.create" />"
		onclick="javascript: relativeRedir('quolet/create.do');">
	
</html>