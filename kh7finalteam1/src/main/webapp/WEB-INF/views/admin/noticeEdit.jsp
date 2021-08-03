<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/WEB-INF/views/template/adminSidebar.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/template/adminTopbar.jsp"></jsp:include>   

<style>
	.form-textarea{
           min-height: 150px;
           max-height: 400px;
       }
</style>
<script>
	$(function(){
		$("textarea[name='noticeWrite']").text('${noticeVo.noticeWrite}');
	});
</script>
		<section class="ftco-section">
 			<div class="row justify-content-center">
                <div class="col-md-6 text-center">
                    <h2 class="heading-section text-gray-800">공지 신규 작성</h2>
                </div>
            </div>	
        </section>
<div class="container-fluid" style="color:#141414">
        <div class="row mt-3">
            <div class="col-md-6 offset-md-3">
            <form action="noticeEdit" method="post" class="form" id="form-regist">	
				<div>
					<div class="form-group">
						<input type="hidden" name="noticeNo" value="${noticeVo.noticeNo}"> 
	                    <label>제목</label>
	                    <input type="text" name="noticeName" class="form-control" value="${noticeVo.noticeName}" required>
                    </div>
                    <div class="form-group">
	                    <label>내용</label>
	                    <textarea rows="10" cols="50" name="noticeWrite" class="form-control" required></textarea>
                    </div>
                </div>
                <button type="submit" class="btn btn-danger btn-block">등록</button>
                <button type="reset" class="btn btn-secondary btn-block">초기화</button>
            </form>
			<div class="py-4 text-right">			            
            	<a href="${pageContext.request.contextPath}/admin/notice/noticeDetail?noticeNo=${noticeVo.noticeNo}" class="btn btn-outline-dark">취소</a>
            </div>
        </div>
	</div>
</div>
	
    <jsp:include page="/WEB-INF/views/template/adminFooter.jsp"></jsp:include>