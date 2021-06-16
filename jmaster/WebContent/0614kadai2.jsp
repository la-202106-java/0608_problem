<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<%!
public class Omikugi {
	private int month;
	private String luck;

	public Omikugi() {
	}

	public Omikugi(int month, String luck) {
		this.month = month;
		this.luck = luck;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getOmikugi() {
		return luck;
	}

	public void setOmikugi(String omikugi) {
		this.luck = luck;
	}
}
%>

<%
ArrayList<Omikugi> list = new ArrayList<Omikugi>();
Omikugi kugi = new Omikugi(1, "吉");
list.add(kugi);
kugi = new Omikugi(2, "大吉");
list.add(kugi);
kugi = new Omikugi(3, "吉");
list.add(kugi);
kugi = new Omikugi(4, "凶");
list.add(kugi);
kugi = new Omikugi(5, "吉");
list.add(kugi);
kugi = new Omikugi(6, "大吉");
list.add(kugi);
kugi = new Omikugi(7, "吉");
list.add(kugi);
kugi = new Omikugi(8, "吉");
list.add(kugi);
kugi = new Omikugi(9, "吉");
list.add(kugi);
kugi = new Omikugi(10, "小吉");
list.add(kugi);
kugi = new Omikugi(11, "小吉");
list.add(kugi);
kugi = new Omikugi(12, "吉");
list.add(kugi);

request.setAttribute("lucks",list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Sample3</title>
</head>
<body>

<c:forEach items="${lucks}" var="result">
<tr><td>${result.month}月の運勢は、、、 ${result.luck}</td></tr>
</c:forEach>


</body>
</html>