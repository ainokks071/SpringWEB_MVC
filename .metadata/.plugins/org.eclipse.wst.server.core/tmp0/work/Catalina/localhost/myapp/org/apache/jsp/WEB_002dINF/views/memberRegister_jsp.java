/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.80
 * Generated at: 2023-10-10 05:32:40 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class memberRegister_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("jar:file:/Users/kks/dev-study/SpringWEB_MVC/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringMVC02/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1671205607423L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("    \n");
      out.write("\n");
      out.write("    \n");
      out.write("\n");
      out.write(" \n");
      out.write("<!-- MVC03 : Model 2 시작 \n");
      out.write("\n");
      out.write(" ** Controller(Servlet)와 View(JSP) 사이의 소통, 연동이 핵심!\n");
      out.write(" \n");
      out.write("1. DAO로부터 얻어온 데이터를 set 객체 바인딩\n");
      out.write("\n");
      out.write("2. RequestDispatcher(요청 의뢰)\n");
      out.write("\n");
      out.write("3. forward기법으로 데이터를 controller(Servlet) -> view(JSP)로 보내기.\n");
      out.write("\n");
      out.write("\n");
      out.write("-->\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("  <head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("    <title>회원 가입 화면</title>\n");
      out.write("    <!--부트스트랩5  -->\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9\" crossorigin=\"anonymous\">\n");
      out.write("    <!--부트스트랩3, jquery  -->\n");
      out.write("	<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\n");
      out.write("	<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js\"></script>\n");
      out.write("	<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\n");
      out.write("	\n");
      out.write("	<script type=\"text/javascript\">\n");
      out.write("	   /* 가입 버튼 클릭 시, add()메서드 호출  */\n");
      out.write("		function add() {\n");
      out.write("			document.form1.action = \"");
      if (_jspx_meth_c_005furl_005f0(_jspx_page_context))
        return;
      out.write("\";\n");
      out.write("			document.form1.submit();\n");
      out.write("		}\n");
      out.write("	   \n");
      out.write("		/* 클라이언트가 회원정보 입력 후, 가입하기 버튼 클릭하면 add2()메서드 호출\n");
      out.write("			Ajax를 이용해서 파일업로드 구현 \n");
      out.write("			1. 파일이 첨부된 경우\n");
      out.write("			2. 파일이 첨부되지 않은 경우\n");
      out.write("		*/\n");
      out.write("		function add2() {\n");
      out.write("			/* 1. 파일이 첨부된 경우 */\n");
      out.write("			if($(\"#file\").val() != '') {\n");
      out.write("				/* 파일데이터를 서버(controller)로 전송하기 위해 'FormData객체' 사용 -> 'fomr태그'와 구분 */\n");
      out.write("				var formData = new FormData();\n");
      out.write("/* 				FormData객체의 append()메서드 : 파일데이터를 key/value로 묶어준다.\n");
      out.write("				$(\"input[name=file]\")[0] : name속성이 file인 input태그들 중 첫번째(0) input태그\n");
      out.write("				files[0] : 사용자가 업로드한 파일들 중 첫번째(0) 파일.\n");
      out.write(" */				\n");
      out.write(" 				formData.append(\"file\", $(\"input[name=file]\")[0].files[0]);\n");
      out.write("				/* 1. ajax(비동기통신)방법으로 파일자체(formData)를 서버로 전송 후, \n");
      out.write("				   2. 업로드 된 파일이름을 반환받아 input(hidden) : filename변수에 저장\n");
      out.write("				   3. 모든 텍스트데이터 서버로 전송. */\n");
      out.write("				$.ajax({\n");
      out.write("			 	/* 1. formData에 묶인 파일데이터를 서버로 전송하기(비동기) : 파일 업로드!  */\n");
      out.write("					url : \"");
      if (_jspx_meth_c_005furl_005f1(_jspx_page_context))
        return;
      out.write("\",\n");
      out.write("					type : \"post\",\n");
      out.write("					data : formData, /* 파일데이터 전송. */\n");
      out.write("					/* formData는 단순 text가 아닌, binary데이터이므로 false로 해야한다. */\n");
      out.write("					processData : false,\n");
      out.write("					contentType : false,\n");
      out.write("				/* 2. 클라이언트의 회원정보 + 파일이름 (텍스트데이터) 서버로 전송하기 */	\n");
      out.write("					success : function(data) { /* 실제 업로드 된 파일 이름 반환 후, input(hidden) : filename변수에 저장. */\n");
      out.write("						alert(\"가입 되었습니다.\");\n");
      out.write("					\n");
      out.write("  						$(\"#filename\").val(data);\n");
      out.write("						/* 사용자가 입력한 id, pass, name, age, email, phone, 첨부한 filename 7개 서버로 전송. */					\n");
      out.write("						document.form1.action = \"");
      if (_jspx_meth_c_005furl_005f2(_jspx_page_context))
        return;
      out.write("\";\n");
      out.write("						document.form1.submit();\n");
      out.write(" 	 					},\n");
      out.write("					error : function() { alert(\"error\"); }\n");
      out.write("				});\n");
      out.write("				   \n");
      out.write("			/* 파일이 첨부되지 않은 경우  */\n");
      out.write("			} else {\n");
      out.write("				/* id, pass, name, age, email, phone 6개 서버로 전송 */\n");
      out.write("				document.form1.action = \"");
      if (_jspx_meth_c_005furl_005f3(_jspx_page_context))
        return;
      out.write("\";\n");
      out.write("				document.form1.submit();\n");
      out.write("			}\n");
      out.write("		}\n");
      out.write("		\n");
      out.write("		function frmreset() {\n");
      out.write("			document.form1.reset();\n");
      out.write("		}\n");
      out.write("		\n");
      out.write("		function memlist() {\n");
      out.write("			location.href=\"");
      if (_jspx_meth_c_005furl_005f4(_jspx_page_context))
        return;
      out.write("\";\n");
      out.write("		}\n");
      out.write("		\n");
      out.write("		/* 중복확인 버큰 클릭 시 실행되는 함수 */\n");
      out.write("		function doublecheck() {\n");
      out.write("			/*JQuery 문법 */\n");
      out.write("		    if($(\"#id\").val()=='') {\n");
      out.write("		    	alert(\"아이디를 입력하세요.\")\n");
      out.write("		    	$(\"#id\").focus(); /* 커서 위치 이동 */\n");
      out.write("		    	return; /* return 적지 않으면 아래의 코드도 실행되겠지. */\n");
      out.write("		    }\n");
      out.write("			\n");
      out.write("		    /* 변수 선언, 입력값 대입  */\n");
      out.write(" 		    var id = $(\"#id\").val();\n");
      out.write("		    \n");
      out.write("		    /* ajax함수 사용 : 클라이언트와 서버 사이의 비동기통신 */\n");
      out.write("		    $.ajax({\n");
      out.write("		    	url : \"");
      if (_jspx_meth_c_005furl_005f5(_jspx_page_context))
        return;
      out.write("\", /* 요청을 보낼 컨트롤러 지정 : 서버에서 중복 체크*/\n");
      out.write("		    	type : \"POST\", /* POST방식으로 데이터 전송 */\n");
      out.write("		    	data : {\"id\" : id}, /* 컨트롤러에 요청 시 보낼 데이터. -> 컨트롤러에서 request.getParameter(\"id\")로 받는다. */\n");
      out.write("		    	success : dbCheck, /* 위 컨트롤러에 요청 성공 시, 응답에 대한 결과데이터를 callback함수에서 받아줌. */\n");
      out.write("		    	error : /* error */function() { alert(\"컨트롤러로 요청 실패 error\"); } /* 컨트롤러에 요청 실패 시 호출되는 함수. */\n");
      out.write("		    });\n");
      out.write("		}\n");
      out.write("		\n");
      out.write("		/* 서버로부터 응답에 대한 결과를 받아주는 callback함수. */\n");
      out.write("		function dbCheck(isDouble) {\n");
      out.write("			if(isDouble == \"true\") {\n");
      out.write("				alert(\"중복된 아이디 입니다.\");\n");
      out.write("				$(\"#id\").focus();\n");
      out.write("			} else {\n");
      out.write("				alert(\"사용 가능한 아이디 입니다.\");\n");
      out.write("				$(\"#id\").focus();\n");
      out.write("			}\n");
      out.write("		}\n");
      out.write("		/* function error() {\n");
      out.write("			alert(\"컨트롤러로 요청 실패 error\");\n");
      out.write("		} */\n");
      out.write("	</script>\n");
      out.write("  </head>\n");
      out.write("  \n");
      out.write("  <body>\n");
      out.write("  <!-- header -->\n");
      out.write("	<div class=\"p-5 bg-primary text-white text-center\">\n");
      out.write("		<h1>Header : 회원 관리 시스템</h1>\n");
      out.write(" 		<p>Resize this responsive page to see the effect!</p> \n");
      out.write("	</div>\n");
      out.write("	\n");
      out.write("	<br>\n");
      out.write("<div class=\"container\">\n");
      out.write(" <h2>회원 가입 화면</h2>  \n");
      out.write("<div class=\"panel panel-default\">\n");
      out.write("  <div class=\"panel-body\">\n");
      out.write("  \n");
      out.write("    <form class=\"form-horizontal\" method=\"post\" id=\"form1\" name=\"form1\">\n");
      out.write("  		<div class=\"form-group\">\n");
      out.write("    		<label class=\"control-label col-sm-2\" for=\"id\">아이디</label>\n");
      out.write("    		<div class=\"col-sm-10\">\n");
      out.write("    		 <table>\n");
      out.write("    		  <tr>\n");
      out.write("    		   <td>\n");
      out.write("    		   								     <!-- id속성은 페이지에서 고유해야한다. -->\n");
      out.write("    		    <input type=\"text\" class=\"form-control\" id=\"id\" name=\"id\" placeholder=\"Enter id\">\n");
      out.write("    		   </td>\n");
      out.write("      		   <td>\n");
      out.write("      		    <input type=\"button\" value=\"중복확인\" class=\"btn btn-warning\" onclick=\"doublecheck()\">\n");
      out.write("      		   </td>	\n");
      out.write("      		  </tr>	\n");
      out.write("      		</table>	\n");
      out.write("    		</div>\n");
      out.write("  		</div>\n");
      out.write("  		\n");
      out.write("  		<div class=\"form-group\">\n");
      out.write("    		<label class=\"control-label col-sm-2\" for=\"pass\">패스워드</label>\n");
      out.write("    		<div class=\"col-sm-10\">\n");
      out.write("      			<input type=\"password\" class=\"form-control\" id=\"pass\" name=\"pass\" placeholder=\"Enter password\" style=\"width:30%\">\n");
      out.write("    		</div>\n");
      out.write("  		</div>\n");
      out.write("  		\n");
      out.write("  		<div class=\"form-group\">\n");
      out.write("    		<label class=\"control-label col-sm-2\" for=\"name\">이름</label>\n");
      out.write("    		<div class=\"col-sm-10\">\n");
      out.write("      			<input type=\"text\" class=\"form-control\" id=\"name\" name=\"name\" placeholder=\"Enter name\" style=\"width:30%\">\n");
      out.write("    		</div>\n");
      out.write("  		</div>\n");
      out.write("  		  \n");
      out.write("   		<div class=\"form-group\">\n");
      out.write("    		<label class=\"control-label col-sm-2\" for=\"age\">나이</label>\n");
      out.write("    		<div class=\"col-sm-10\">\n");
      out.write("      			<input type=\"text\" class=\"form-control\" id=\"age\" name=\"age\" placeholder=\"Enter age\" style=\"width:30%\">\n");
      out.write("    		</div>\n");
      out.write("  		</div>\n");
      out.write("  		 				\n");
      out.write("  		<div class=\"form-group\">\n");
      out.write("    		<label class=\"control-label col-sm-2\" for=\"email\">이메일</label>\n");
      out.write("    		<div class=\"col-sm-10\">\n");
      out.write("      			<input type=\"email\" class=\"form-control\" id=\"email\" name=\"email\" placeholder=\"Enter email\" style=\"width:30%\">\n");
      out.write("    		</div>\n");
      out.write("  		</div>  \n");
      out.write("\n");
      out.write("  		<div class=\"form-group\">\n");
      out.write("    		<label class=\"control-label col-sm-2\" for=\"phone\">전화번호</label>\n");
      out.write("    		<div class=\"col-sm-10\">\n");
      out.write("      			<input type=\"text\" class=\"form-control\" id=\"phone\" name=\"phone\" placeholder=\"Enter phone\" style=\"width:30%\">\n");
      out.write("    		</div>\n");
      out.write("  		</div>\n");
      out.write("  		\n");
      out.write("  		<div class=\"form-group\">\n");
      out.write("    		<label class=\"control-label col-sm-2\" >첨부파일</label>\n");
      out.write("    		<div class=\"col-sm-10\">\n");
      out.write("      			<input type=\"file\" class=\"control-label\" id=\"file\" name=\"file\">\n");
      out.write("    		</div>\n");
      out.write("  		</div>\n");
      out.write("   				<input type=\"hidden\" id=\"filename\" name=\"filename\" >\n");
      out.write(" 	</form>\n");
      out.write("  </div>\n");
      out.write("  \n");
      out.write("  <div class=\"panel-footer\" style=\"text-align : center\">\n");
      out.write("  	  <input type=\"button\" class=\"btn btn-primary\" value=\"가입\" onclick=\"add2()\"/>\n");
      out.write("	  <input type=\"button\" class=\"btn btn-warning\" value=\"취소\" onclick=\"frmreset()\"/>\n");
      out.write("	  <input type=\"button\" class=\"btn btn-success\" value=\"리스트로\" onclick=\"memlist()\"/>\n");
      out.write("  </div>\n");
      out.write("  \n");
      out.write("</div>\n");
      out.write("</div>  \n");
      out.write("  \n");
      out.write("\n");
      out.write("<!-- JDBC는 맛보기. 어차피 요즘 사용하지 않음. MVC 공부 용도!! -->\n");
      out.write("     \n");
      out.write("<!-- 요청 : http://localhost:8080/MVC06/memberInsert.do\n");
      out.write("	 /(슬래쉬) -> 절대경로로 입력 -->\n");
      out.write("<!--  <form action=\"/MVC06/memberInsert.do\" method=\"post\"> -->\n");
      out.write("<!--$기호 : EL태그-->\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- footer -->\n");
      out.write("<div class=\"mt-5 p-4 bg-dark text-white text-center\">\n");
      out.write("  	<p>Footer</p>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("  </body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005furl_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f0 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    boolean _jspx_th_c_005furl_005f0_reused = false;
    try {
      _jspx_th_c_005furl_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005furl_005f0.setParent(null);
      // /WEB-INF/views/memberRegister.jsp(39,28) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005furl_005f0.setValue("/memberInsert.do");
      int _jspx_eval_c_005furl_005f0 = _jspx_th_c_005furl_005f0.doStartTag();
      if (_jspx_th_c_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
      _jspx_th_c_005furl_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005furl_005f0, _jsp_getInstanceManager(), _jspx_th_c_005furl_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f1 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    boolean _jspx_th_c_005furl_005f1_reused = false;
    try {
      _jspx_th_c_005furl_005f1.setPageContext(_jspx_page_context);
      _jspx_th_c_005furl_005f1.setParent(null);
      // /WEB-INF/views/memberRegister.jsp(63,12) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005furl_005f1.setValue("/fileUpload.do");
      int _jspx_eval_c_005furl_005f1 = _jspx_th_c_005furl_005f1.doStartTag();
      if (_jspx_th_c_005furl_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f1);
      _jspx_th_c_005furl_005f1_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005furl_005f1, _jsp_getInstanceManager(), _jspx_th_c_005furl_005f1_reused);
    }
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f2 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    boolean _jspx_th_c_005furl_005f2_reused = false;
    try {
      _jspx_th_c_005furl_005f2.setPageContext(_jspx_page_context);
      _jspx_th_c_005furl_005f2.setParent(null);
      // /WEB-INF/views/memberRegister.jsp(75,31) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005furl_005f2.setValue("/memberInsert.do?mode=fileAdd");
      int _jspx_eval_c_005furl_005f2 = _jspx_th_c_005furl_005f2.doStartTag();
      if (_jspx_th_c_005furl_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f2);
      _jspx_th_c_005furl_005f2_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005furl_005f2, _jsp_getInstanceManager(), _jspx_th_c_005furl_005f2_reused);
    }
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f3(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f3 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    boolean _jspx_th_c_005furl_005f3_reused = false;
    try {
      _jspx_th_c_005furl_005f3.setPageContext(_jspx_page_context);
      _jspx_th_c_005furl_005f3.setParent(null);
      // /WEB-INF/views/memberRegister.jsp(84,29) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005furl_005f3.setValue("/memberInsert.do?mode=add");
      int _jspx_eval_c_005furl_005f3 = _jspx_th_c_005furl_005f3.doStartTag();
      if (_jspx_th_c_005furl_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f3);
      _jspx_th_c_005furl_005f3_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005furl_005f3, _jsp_getInstanceManager(), _jspx_th_c_005furl_005f3_reused);
    }
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f4(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f4 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    boolean _jspx_th_c_005furl_005f4_reused = false;
    try {
      _jspx_th_c_005furl_005f4.setPageContext(_jspx_page_context);
      _jspx_th_c_005furl_005f4.setParent(null);
      // /WEB-INF/views/memberRegister.jsp(94,18) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005furl_005f4.setValue("/memberList.do");
      int _jspx_eval_c_005furl_005f4 = _jspx_th_c_005furl_005f4.doStartTag();
      if (_jspx_th_c_005furl_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f4);
      _jspx_th_c_005furl_005f4_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005furl_005f4, _jsp_getInstanceManager(), _jspx_th_c_005furl_005f4_reused);
    }
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f5(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f5 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    boolean _jspx_th_c_005furl_005f5_reused = false;
    try {
      _jspx_th_c_005furl_005f5.setPageContext(_jspx_page_context);
      _jspx_th_c_005furl_005f5.setParent(null);
      // /WEB-INF/views/memberRegister.jsp(111,14) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005furl_005f5.setValue("/memberDbcheck.do");
      int _jspx_eval_c_005furl_005f5 = _jspx_th_c_005furl_005f5.doStartTag();
      if (_jspx_th_c_005furl_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f5);
      _jspx_th_c_005furl_005f5_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005furl_005f5, _jsp_getInstanceManager(), _jspx_th_c_005furl_005f5_reused);
    }
    return false;
  }
}
