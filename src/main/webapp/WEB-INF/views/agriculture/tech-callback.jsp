<%@page import="java.io.*,java.net.*" contentType="text/xml; charset=UTF-8" %><%
String queryString = request.getQueryString();
String openapi_url = "http://api.nongsaro.go.kr/service/"+queryString;

StringBuffer sbf = new StringBuffer();
try{
System.out.println(openapi_url);
URL url = new URL(openapi_url);
BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
String inputLine;
while( (inputLine = in.readLine() ) != null ) sbf.append(inputLine);
} catch( Exception e ) {
e.printStackTrace();
}
%><%= sbf.toString() %>