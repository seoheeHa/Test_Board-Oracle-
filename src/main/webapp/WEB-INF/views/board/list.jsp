<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
  
  <style>
  	a{
  		text-decoration : none;
  		
  	}
  	
  	table{
  		border-collapse: collapse;
  		width: 1000px;
  		margin-top : 20px;
  		text-align : center;
  	}
  
  	td, th{
  		border : 1px solid black;
  		height : 50px;
  	}
  	
  	th {
  		font-size : 17px
  	}
  	
  	thead {
  		font-weight: 700;
  	}
  	
  	.table_wrap{
  		margin : 50px 0 0 50px;
  	}
  	.bno_width{
  		width: 12%;
  	}
  	.writer_width{
  		width: 20%;
 	 }
  	.regdate_width{
  		width: 15%;
  	}
  	.updatedate_width{
  		width: 15%;
  	}
  	.top_btn{
  		font-size: 20px;
    	padding: 6px 12px;
    	background-color: #fff;
    	border: 1px solid #ddd;
    	font-weight: 600;
 	 }
 	 
 	 .pageInfo{
 	 	list-style : none;
 	 	display : inline-block;
 	 	margin : 50px 0 0 100px;
 	 }
 	 
 	 .pageInfo li{
 	 	float : left;
 	 	font-size: 20px;
 	 	margin-left: 18px;
 	 	padding: 7px;
 	 	font-weight: 500;
 	 }

	a:link {color:black; text-decoration: none;}
	a:visited{color:black; text-decoration:none;}
	a:hover{color:black; text-decortaion:underline;}  
	.active{
		background-color: #cdd5ec;
	}

	.search_area{
		display: inline-block;
		margin-top: 30px;
		margin-left: 260px;
		
	}
	
	.search_area input{
		height: 30px;
		width: 250px;
	}

	.search_area button{
		width: 100px;
		height: 36px;
	}
	
	.search_area select{
		height: 35px;
	}
	
    </style>
  
  
</head>
<body>

<h1>목록페이지 입니다</h1>
<div class ="table_wrap">
<a href="/board/enroll">게시판 등록</a>

<table>
	<thead>
		<tr>
			<th class="bno_width">번호</th>
			<th class="title_width">제목</th>
			<th class="writer_width">작성자</th>
			<th class="regdate_width">작성일</th>
			<th class="updatedate_width">수정일</th>
			
		</tr>
	</thead>
	<c:forEach items="${list }" var="list">
		<tr>
			<td><c:out value="${list.bno }"/></td>
			<td>
			<a class="move" href='<c:out value="${list.bno}"/>'>
        		<c:out value="${list.title}"/>
    		</a>
			</td>
			<td><c:out value="${list.writer }"/></td>
			<td><fmt:formatDate pattern="yyyy/MM/dd" value="${list.regdate}"/></td>
            <td><fmt:formatDate pattern="yyyy/MM/dd" value="${list.updateDate}"/></td>
		</tr>
	 </c:forEach>
</table>

	<div class="search_wrap">
		<div class="search_area">
			<select name="type">
				<option value="" <c:out value="${pageMaker.cri.type == null?'selected':'' }"/>> --<option>
				<option value="T" <c:out value="${pageMaker.cri.type eq 'T'?'selected':'' }"/>> 제목<option>
				<option value="C" <c:out value="${pageMaker.cri.type eq 'C'?'selected':'' }"/>> 내용<option>
				<option value="W" <c:out value="${pageMaker.cri.type eq 'W'?'selected':'' }"/>> 작성자<option>
				<option value="TC" <c:out value="${pageMaker.cri.type eq 'TC'?'selected':'' }"/>> 제목 + 내용<option>
				<option value="TW" <c:out value="${pageMaker.cri.type eq 'TW'?'selected':'' }"/>> 제목 + 작성자<option>
				<option value="TCW" <c:out value="${pageMaker.cri.type eq 'TCW'?'selected':'' }"/>> 제목 + 내용 + 작성자<option>
			
			</select>
		
			<input type="text" name="keyword" value="${pageMaker.cri.keyword }">
			<button>검색</button>
		</div>
	</div>


		<!-- 번호페이지 구현 -->
	<div class="pageInfo_wrap">
		<div class="pageInfo_area">
			<ul id="pageInfo" class="pageInfo">

			<!-- 이전페이지 버튼 -->
			<c:if test="${pageMaker.prev }">
				<li class="pageInfo_btn previous"><a href="${pageMaker.startPage-1 }">Previous</a></li>
			</c:if>
				
				<!-- 각 번호 페이지 구현 -->
				<!-- 
					pageMaker속성에 저장된 statPage와 endPage값을 가지고
					foreach태그 활용하여 페이지 번호를 화면에 출력시킨다.
					각 번호는 a태그로 감쌌고,
				 -->
				<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                    <li class="pageInfo_btn ${pageMaker.cri.pageNum == num ? "active": "" }"><a href="${num}">${num}</a></li>
                </c:forEach>	
			
			<!-- 다음페이지 버튼 -->
			<c:if test="${pageMaker.next }">
				<li class="pageInfo_btn next"><a href="${pageMaker.endPage + 1 }">Next</a></li>			
			</c:if>
			
			</ul>
		</div>
	</div>

	<!-- 페이지 이동시 필요정보인 pageNum과 amount정보를 전송하기 위해 form태그안에 넣는다.
		input태그안의 value값은 현재 페이지의 정보가 저장되도록 하였다.
		이는 현 페이지안에서 조회,수정페이지로 이동하였다가 다시 현페이지로 이동하기위해 작성된것이다.
	 -->
	<form id="moveForm" method="get">    
    	 <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
        <input type="hidden" name="amount" value="${pageMaker.cri.amount }">
        <input type="hidden" name="keyword" value="${pageMaker.cri.keyword }">
        <input type="hidden" name="type" value="${pageMaker.cri.type }">
    
    </form>

</div>
<script>

$(document).ready(function(){

	let result = "<c:out value="${result}"/>";

	checkAlert(result);
    console.log(result);
    function checkAlert(result){
        
        if(result === ''){
            reutrn;
        }
        
        if(result === "enrol success"){
            alert("등록이 완료되었습니다.");
        }

        if(result === "modify success"){
			alert("수정이 완료되었습니다");
            }
      	if(result === "delete success"){
			alert("삭제가 완료되었습니다");
          	}  
    }    
    
	});

	let moveForm = $("#moveForm");
	
	$(".move").on("click", function(e){
	    e.preventDefault();
	    
	    moveForm.append("<input type='hidden' name='bno' value='"+ $(this).attr("href")+ "'>");
	    moveForm.attr("action", "/board/get");
	    moveForm.submit();
	});

	$(".pageInfo a").on("click", function(e){
		 
        e.preventDefault();
        moveForm.find("input[name='pageNum']").val($(this).attr("href"));
        moveForm.attr("action", "/board/list");
        moveForm.submit();
        
    });


	$(".search_area button").on("click", function(e){
		 
        e.preventDefault();
        let type = $(".search_area select").val();
        let keyword = $(".search_area input[name='keyword']").val();

		if(!type){
			alert("검색 종류를 선택하세요");
			return false;
			}

		if(!keyword){
			alert("키워드를 입력하세요");
			return false;
			}
        
        moveForm.find("input[name='type']").val(type);
        moveForm.find("input[name='keyword']").val(keyword);
        moveForm.find("input[name='pageNum']").val(1);
        moveForm.submit();
        
    });

	
</script>


</body>
</html>