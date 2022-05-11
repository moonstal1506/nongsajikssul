<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container " style="width: 800px">
    <div>
		<h2>농약 계산기<h2>
		<hr/>
	</div>

    <div class="card">
      <div class="card-header">농약계산</div>
      <div class="card-body">

          <label for="multiple" class="mr-sm-2"><b>희석배수</b></label>
          <input class="form-control mb-2 mr-sm-2" placeholder="희석배수" id="multiple">

          <label for="water" class="mr-sm-2"><b>물의 양(L)</b></label>
          <input class="form-control mb-2 mr-sm-2" placeholder="물의 양(L)" id="water">

          <button onclick="calculate()" class="btn btn-success mb-2">계산</button>
      </div>
      <div class="card-footer" style="color:red;"><b id="result">0</b>g(mL)</div>

    </div>

</div>
<script src="/js/calculator.js"></script>
<%@ include file="../layout/footer.jsp"%>