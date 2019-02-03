<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="view" tagdir="/WEB-INF/tags"%>
<%@page import="org.springframework.context.i18n.LocaleContextHolder"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Date" %>

	<spring:message code="quolet.ticker" var="ticker" />
	<spring:message code="quolet.publicationMoment" var="publicationMoment" />
	<spring:message code="quolet.body" var="body" />
	<spring:message code="quolet.picture" var="picture" />
	<spring:message code="quolet.finalMode" var="mode" />
	
	<jstl:set value="<%=LocaleContextHolder.getLocale() %>" var="locale"></jstl:set>
	
	<table
	class="ui celled table">
	<thead>
	</thead>
	<tbody>
		<tr>
			<td><spring:message code="quolet.ticker" />
			<td data-label="ticker">
				<view:monthsBetween srcDate="${quolet.publicationMoment}" dateVariable="diffMonth"/>
					<jstl:if test="${diffMonth < 1}">
						<div style="background: green">${quolet.ticker}</div>
					</jstl:if>
					<jstl:if test="${diffMonth >= 1 and diffMonth < 2}">
						<div style="background: orange">${quolet.ticker}</div>
					</jstl:if>
					<jstl:if test="${diffMonth >= 2}">
						<div style="background: red">${quolet.ticker}</div>
					</jstl:if>
			</td>
		</tr>
		<tr>
			<td><spring:message code="quolet.publicationMoment" />
			<td data-label="publicationMoment">
				<jstl:if test="${locale == 'en'}">
					<fmt:formatDate value = "${quolet.publicationMoment}" pattern = "yy/MM/dd hh:mm" />
				</jstl:if>
				<jstl:if test="${locale == 'es'}">
					<fmt:formatDate value = "${quolet.publicationMoment}" pattern = "dd-MM-yy hh:mm" />
				</jstl:if>
			</td>
		</tr>
		<tr>
			<td><spring:message code="quolet.body" />
			<td data-label="surname">${quolet.body}</td>
		</tr>
		<tr>
			<td><spring:message code="quolet.picture" />
			<td data-label="email">${quolet.picture}</td>
		</tr>
		<tr>
			<td><spring:message code="quolet.finalMode" />
			<td data-label="phoneNumber">${quolet.finalMode}</td>
		</tr>
	</tbody>

</table>