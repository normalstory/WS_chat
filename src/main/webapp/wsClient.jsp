<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WebSocket</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

//서버 전역변수로 
var socket;

$(document).ready(function(){
	//서버 환경설정
	socket = new WebSocket("ws://localhost:8081/wsServer");
	//서버와 연동이 성공한 경우, 시스템이 실행하도록하는 함수 
	socket.onopen = function(){
		socket.send("hello world");
	}
	
	//서버가 보낸 메시지를 수신하기 위한 함수
	socket.onmessage = function(event){
		$("#chat").append(event.data + "\n");
		
		//console.log("onMessage : " + event.data);
	}
	
	//보내기 버튼 클릭시 실행될 함수 
	$("#send").on("click", function(){
		socket.send($("#text").val());
	});
});

	
</script>
</head>
<body>

	<textarea id="chat" cols="200" rows="30"></textarea><br/>
	<input id="text" type="text"/><br/>
	<button id="send">보내기</button>
	
</body>
</html>