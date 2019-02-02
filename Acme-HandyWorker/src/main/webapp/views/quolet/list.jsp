<%@page import="org.springframework.context.i18n.LocaleContextHolder"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="view" tagdir="/WEB-INF/tags"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>

	<jstl:set value="<%=LocaleContextHolder.getLocale() %>" var="locale"></jstl:set>
	
	<spring:message code="quolet.ticker" var="ticker" />
	<spring:message code="quolet.publicationMoment" var="publicationMoment" />
	<spring:message code="quolet.body" var="body" />
	<spring:message code="quolet.picture" var="picture" />

	<display:table name="list" id="row" requestURI="quolet/list.do" pagesize="3" class="displaytag">
	
	<display:column title="${ticker}">
		<view:monthsBetween srcDate="${row.publicationMoment}" dateVariable="diffMonth"/>
		<jstl:if test="${diffMonth < 1}">
			<div style="background: green">${row.ticker}</div>
		</jstl:if>
		<jstl:if test="${diffMonth >= 1 and diffMonth < 2}">
			<div style="background: orange">${row.ticker}</div>
		</jstl:if>
		<jstl:if test="${diffMonth >= 2}">
			<div style="background: red">${row.ticker}</div>
		</jstl:if>
	</display:column>
	<display:column title="${publicationMoment}" >
		<jstl:if test="${locale == 'en'}">
			<fmt:formatDate value = "${row.publicationMoment}" pattern = "yy/MM/dd hh:mm" />
		</jstl:if>
		<jstl:if test="${locale == 'es'}">
			<fmt:formatDate value = "${row.publicationMoment}" pattern = "dd-MM-yy hh:mm" />
		</jstl:if>
	</display:column>
	<display:column property="body" title="${body}" />
	<display:column property="picture" title="${picture}" />
	
	<display:column>
	<jstl:if test="${!row.finalMode }">
		<a href="quolet/edit.do?quoletId=${row.id}"> <spring:message code="quolet.edit" /></a>
	</jstl:if>
	</display:column>
	
	</display:table>
	
	<input type="button" class="ui button" name="create"
		value="<spring:message code="quolet.create" />"
		onclick="javascript: relativeRedir('quolet/create.do?fixUpTaskId=${fixUpTaskId}');">
	
</html>