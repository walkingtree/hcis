<%@ page session="true" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://www.tonbeller.com/jpivot" prefix="jp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<%-- uses a dataSource --%>
<%-- jp:mondrianQuery id="query01" dataSource="jdbc/orcl" catalogUri="/WEB-INF/demo/FoodMart.xml" --%>

<%-- uses mysql --%>
<%-- jp:mondrianQuery id="query01" jdbcDriver="oracle.jdbc.driver.OracleDriver" jdbcUrl="jdbc:oracle:thin:scott/tiger@localhost:1521:orcl;" catalogUri="/WEB-INF/queries/FoodMart.xml"--%>

<%-- uses a role defined in FoodMart.xml --%>
<%-- jp:mondrianQuery role="California manager" id="query01" jdbcDriver="sun.jdbc.odbc.JdbcOdbcDriver" jdbcUrl="jdbc:odbc:MondrianFoodMart" catalogUri="/WEB-INF/queries/FoodMart.xml" --%>

<jp:mondrianQuery id="query01" jdbcDriver="oracle.jdbc.driver.OracleDriver" jdbcUrl="jdbc:oracle:thin:scott/tiger@localhost:1521:orcl"  catalogUri="/WEB-INF/queries/FoodMart.xml">

select {[Measures].[CREDITAMOUNT], [Measures].[CURRENTCHARGE], [Measures].[RENDEREDUNITS], [Measures].[CANCELLEDUNITS]} ON COLUMNS,
  NON EMPTY {([Date].[All Dates], [Service].[All Services], [Doctor].[All Doctors])} ON ROWS
from [ASSIGNEDSERVICES]

</jp:mondrianQuery>

<c:set var="title01" scope="session">Date-Service-Doctor wise Analysis</c:set>
