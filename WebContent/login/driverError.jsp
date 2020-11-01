<%@page import="customer.model.vo.Customer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보가 존재하지 않습니다.</title>
</head>
<body>
	<div>${sessionScope.driver.driverId }님의 회원 정보가 존재하지 않습니다.</div>
	
</body>
</html>