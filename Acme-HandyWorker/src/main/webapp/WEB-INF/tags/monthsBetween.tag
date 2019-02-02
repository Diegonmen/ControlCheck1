<%@tag import="java.util.Date"%>
<%@tag import="java.util.GregorianCalendar"%>
<%@tag import="java.util.Calendar"%>
<%@ tag language="java" body-content="empty" %>

<%@ attribute name="srcDate" required="true" type="java.util.Date"%>
<%@ attribute name="dateVariable" required="true" type="java.lang.String"%>


<%
	Date srcDate = (Date) jspContext.getAttribute("srcDate");
	String dateVariable = (String) jspContext.getAttribute("dateVariable");

	Calendar startCalendar = new GregorianCalendar();
	startCalendar.setTime(srcDate);
	Calendar endCalendar = new GregorianCalendar();
	endCalendar.setTime(new Date());
	
	int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
	int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
	
	jspContext.setAttribute(dateVariable, diffMonth, PageContext.REQUEST_SCOPE);
%>