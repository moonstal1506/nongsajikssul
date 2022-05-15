<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko">
  <head>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
    <meta content="no-cache" http-equiv="pragma" />
    <title>이달의 농업기술 Open API Ajax 샘플 페이지</title>

    <!-- 공통 CSS, JS 선언 -->
    <link
      href="http://api.nongsaro.go.kr/css/api.css"
      rel="stylesheet"
      type="text/css"
    />
    <script
      type="text/javascript"
      src="http://api.nongsaro.go.kr/js/framework.js"
    ></script>
    <script
      type="text/javascript"
      src="http://api.nongsaro.go.kr/js/openapi_nongsaro.js"
    ></script>
    <script type="text/javascript">
      nongsaroOpenApiRequest.apiKey = "20220512FNEAFAIGTULAVOL9Z2NQ"; //Api Key  - 발급받은 인증키로변경
      nongsaroOpenApiRequest.serviceName = "monthFarmTech"; //서비스명
      nongsaroOpenApiRequest.operationName = "monthFarmTechLst"; //오퍼레이션명 - nongsaroApiLoadingArea 영역에 로딩할 오퍼레이션
      nongsaroOpenApiRequest.htmlArea = "nongsaroApiLoadingArea"; //첫번째로 HTML을 로딩할영역

      //크로스 도메인 처리를 위한 콜백페이지 - 샘플소스에 있는 콜백페이지를 서버에 올리고 올린 경로로 수정
      //서버 환경에 맞는 언어로 주석 해지 후 적용 사용
      nongsaroOpenApiRequest.callback =
        "http://localhost:9000/agriculture/tech/callback";
    </script>
  </head>

  <body>
    <div id="nongsaroApiLoadingArea"></div>
    <div id="nongsaroApiLoadingAreaDtlGuideLst"></div>
    <div id="nongsaroApiLoadingAreaDtlDefaultInfo"></div>
    <div id="nongsaroApiLoadingAreaDtl"></div>
    <div id="nongsaroApiLoadingAreaDtlMovieInfo"></div>
    <div id="nongsaroApiLoadingAreaDtlKidoInfo"></div>
    <div id="nongsaroApiLoadingAreaDtlAnswerLst"></div>
  </body>
</html>
