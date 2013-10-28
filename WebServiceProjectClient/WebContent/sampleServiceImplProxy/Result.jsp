<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleServiceImplProxyid" scope="session" class="model.ServiceImplProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleServiceImplProxyid.setEndpoint(request.getParameter("endpoint"));
%>

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        java.lang.String getEndpoint2mtemp = sampleServiceImplProxyid.getEndpoint();
if(getEndpoint2mtemp == null){
%>
<%=getEndpoint2mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEndpoint2mtemp));
        %>
        <%= tempResultreturnp3 %>
        <%
}
break;
case 5:
        gotMethod = true;
        String endpoint_0id=  request.getParameter("endpoint8");
            java.lang.String endpoint_0idTemp = null;
        if(!endpoint_0id.equals("")){
         endpoint_0idTemp  = endpoint_0id;
        }
        sampleServiceImplProxyid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        model.ServiceImpl getServiceImpl10mtemp = sampleServiceImplProxyid.getServiceImpl();
if(getServiceImpl10mtemp == null){
%>
<%=getServiceImpl10mtemp %>
<%
}else{
        if(getServiceImpl10mtemp!= null){
        String tempreturnp11 = getServiceImpl10mtemp.toString();
        %>
        <%=tempreturnp11%>
        <%
        }}
break;
case 13:
        gotMethod = true;
        String nom_2id=  request.getParameter("nom18");
            java.lang.String nom_2idTemp = null;
        if(!nom_2id.equals("")){
         nom_2idTemp  = nom_2id;
        }
        String prenom_3id=  request.getParameter("prenom20");
            java.lang.String prenom_3idTemp = null;
        if(!prenom_3id.equals("")){
         prenom_3idTemp  = prenom_3id;
        }
        %>
        <jsp:useBean id="model1Personne_1id" scope="session" class="model.Personne" />
        <%
        model1Personne_1id.setNom(nom_2idTemp);
        model1Personne_1id.setPrenom(prenom_3idTemp);
        java.lang.String op13mtemp = sampleServiceImplProxyid.op(model1Personne_1id);
if(op13mtemp == null){
%>
<%=op13mtemp %>
<%
}else{
        String tempResultreturnp14 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(op13mtemp));
        %>
        <%= tempResultreturnp14 %>
        <%
}
break;
}
} catch (Exception e) { 
%>
Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.toString()) %>
Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.getMessage()) %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>