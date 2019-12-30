# Servlet

Servlet을 깊게 이해해보기 

참고 서적  : 처음해보는 Servlet & JSP 웹 프로그래밍



## Servlet개요





### 서블릿의 역사

- 애플릿 
  - 정적인 페이지를 극복하기 위한 기술
  - 서버에서 클라이언트 쪽으로 실행파일을 다운받아 실행
- 서블릿
  - 애플릿의 단점을 극복하기 위한 기술(다운받아 실행 받는 문제)
  - 서버에서 실행하여 결과값만 클라이언트에게 전송
  - HTTP 프로토콜로 통신하는 웹의 특징과 속성을 자유롭게 활용
- JSP 
  - JSP는 서블릿과 똑같은 기능을 가지고 있지만 다음과 같은 차이가 있음
    1) JSP는 페이지안에서 스크립트 형태로 구현
    2) JSP는 뷰페이지 생성을 위해 사용됨

### 컨테이너

- 서블릿 컨테이너

  - 웹 컴포넌트를 저장하는 저장소, 메모리 로딩, 객체 생성 초기화, 생명 주기 관리 하는 것이 컨테이너 역할
  - 자바 수행환경, 웹서버, 서블릿 컨테이너가 필요
  - 서블릿 컨테이너를 서블릿 엔진이라 부르기도 함

- JSP 컨테이너 

  - 자바 수행환경, 웹서버, 서블릿 컨테이너, JSP 컨테이너
- web.xml : 환경설정 파일
  - web.xml은 서비스 처리에 관한 내용을 정의한 파일

### 서블릿 구현

- 클라이언트 요청에 따라 서버가 실행할 수 있는 자바프로그램은 서블릿 
- [공식문서](https://docs.oracle.com/javaee/7/api/index.html?javax/servlet/package-summary.html)
- 서블릿 구현할 때 HttpServlet 클래스를 상속 받아야함
- Servlet -> GenericServlet -> HttpServlet 순으로 계층 구성
- Servlet 인터페이스
  - init, service, destory, getServletConfig, getServletInfo 메소드를 선언

### service

- 클라이언트 요청에 따라 실행

- HttpServlet.class (tomcat/servlet-api.jar) 

  참고사이트 : [java servlet 스펙](https://github.com/javaee/servlet-spec/blob/master/src/main/java/javax/servlet/http/HttpServlet.java)

  ```	java
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  		String method = req.getMethod();
  		long lastModified;
  		if (method.equals("GET")) {
  			lastModified = this.getLastModified(req);
  			if (lastModified == -1L) {
  				this.doGet(req, resp);
  			} else {
  				long ifModifiedSince;
  				try {
  					ifModifiedSince = req.getDateHeader("If-Modified-Since");
  				} catch (IllegalArgumentException var9) {
  					ifModifiedSince = -1L;
  				}
  
  				if (ifModifiedSince < lastModified / 1000L * 1000L) {
  					this.maybeSetLastModified(resp, lastModified);
  					this.doGet(req, resp);
  				} else {
  					resp.setStatus(304);
  				}
  			}
  		} else if (method.equals("HEAD")) {
  			lastModified = this.getLastModified(req);
  			this.maybeSetLastModified(resp, lastModified);
  			this.doHead(req, resp);
  		} else if (method.equals("POST")) {
  			this.doPost(req, resp);
  		} else if (method.equals("PUT")) {
  			this.doPut(req, resp);
  		} else if (method.equals("DELETE")) {
  			this.doDelete(req, resp);
  		} else if (method.equals("OPTIONS")) {
  			this.doOptions(req, resp);
  		} else if (method.equals("TRACE")) {
  			this.doTrace(req, resp);
  		} else {
  			String errMsg = lStrings.getString("http.method_not_implemented");
  			Object[] errArgs = new Object[]{method};
  			errMsg = MessageFormat.format(errMsg, errArgs);
  			resp.sendError(501, errMsg);
  		}
  
  	}
  ```

  - request method를 추출해서 각 메소드를 호출

  - getLastModified는 기본 값으로 -1L를 리턴한다.  [If-Modified-Since](https://developer.mozilla.org/ko/docs/Web/HTTP/Headers/If-Modified-Since)는 다음과 같이 공식문서에 작성되어있음

    > ​	a `long` value representing the date specified in the header expressed as the number of milliseconds since January 1, 1970 GMT, or -1 if the named header was not included with the request

  - if-Modified-Since 헤더는 캐시 갱신 등 많이 사용된다. [참고사이트](https://developer.mozilla.org/ko/docs/Web/HTTP/Conditional_requests)

    > 조건부 요청의 가장 일반적인 유스 케이스는 캐시를 갱신하는 것입니다. 비어 있는 캐시를 가지고 있거나 혹은 캐시를 가지고 있지 않은 경우, 요청된 리소스는 [`200`](https://developer.mozilla.org/ko/docs/Web/HTTP/Status/200) `OK`의 상태로 회신됩니다.
    >
    > 
    >
    > 리소스가 변경되지 않았다면, 서버는 `304` `Not Modified` 응답을 회신하게 되는데, 이는 캐시를 다시 신선한 것으로 만들어주며 클라이언트는 그 캐시된 리소스를 사용하게 됩니다. 비록 어떤 리소스를 소비하는 응답/요청 라운드 트립이 있다고 하더라도, 연결을 통해 다시 전체 리소스를 전송하는 것보다는 더 효율적입니다.



### doGet, doPost

- doGet이나 doPost를 오버라이딩 하면 오버라이딩 된 메소드가 호출 됨

  ```java
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  		String protocol = req.getProtocol();
  		String msg = lStrings.getString("http.method_get_not_supported");
  		if (protocol.endsWith("1.1")) {
  			resp.sendError(405, msg);
  		} else {
  			resp.sendError(400, msg);
  		}
  
  }
  ```

  - HttpServlet에 정의된 메서드에서는  프로토콜이 HTTP 1.1일 경우 405(Method Not Allowed) 
    그렇지 않으면 400에러(Bad Request)를 보낸다



### doHead

> ​	**HTTP `HEAD` 메소드**는 특정 리소스를 HTTP [`GET`](https://developer.mozilla.org/ko/docs/Web/HTTP/Methods/GET) 메소드로 요청하는 경우에 어떤 헤더들이 반환되는지를 요청합니다. 예를 들어, 큰 용량의 리소스를 다운로드 받을지 말지 결정하기 위해서 사전 요청하는 용도로 사용할 수 있습니다.



```java
protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (DispatcherType.INCLUDE.equals(req.getDispatcherType())) {
			this.doGet(req, resp);
		} else {
			NoBodyResponse response = new NoBodyResponse(resp);
			this.doGet(req, response);
			response.setContentLength();
		}

	}
```

- DispatcherType은 Enum으로 정의되어있는데 다음과 같이 5가지로 정의되어있다.

  ```java
  public enum DispatcherType {
  	FORWARD, INCLUDE, REQUEST, ASYNC, ERROR;
  }
  ```

  - include인 경우는 바로 doGet을 호출 그 외의 경우에는 NoBodyResponse를 생성해서 response를 매개변수로 전달

- NoBodyResponse는 변수 그대로 Body가 없는 Response로  ContentLength를 전달하는 것이 목적이기에 코드 대부분이 ContentLength를 계산,체크하도록 구현되어있다.



### doOption

> ​	요청을 보내면, 응답에 [`Allow`](https://developer.mozilla.org/ko/docs/Web/HTTP/Headers/Allow)헤더가 포함되어서 오는데, 이를 통해 허용되는 메소드를 확인할 수 있다.

- 소스코드 상에서도 모든 메서드를 확인하는 것을 알 수 있다.

  ```java
  protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  		Method[] methods = getAllDeclaredMethods(this.getClass());
  		boolean ALLOW_GET = false;
  		boolean ALLOW_HEAD = false;
  		boolean ALLOW_POST = false;
  		boolean ALLOW_PUT = false;
  		boolean ALLOW_DELETE = false;
  		boolean ALLOW_TRACE = true;
  		boolean ALLOW_OPTIONS = true;
  		Class clazz = null;
  
  		try {
  			clazz = Class.forName("org.apache.catalina.connector.RequestFacade");
  			Method getAllowTrace = clazz.getMethod("getAllowTrace", (Class[]) null);
  			ALLOW_TRACE = (Boolean) getAllowTrace.invoke(req, (Object[]) null);
  		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
  				| InvocationTargetException | ClassNotFoundException var14) {
  			;
  		}
  
  		for (int i = 0; i < methods.length; ++i) {
  			Method m = methods[i];
  			if (m.getName().equals("doGet")) {
  				ALLOW_GET = true;
  				ALLOW_HEAD = true;
  			}
  
  			if (m.getName().equals("doPost")) {
  				ALLOW_POST = true;
  			}
  
  			if (m.getName().equals("doPut")) {
  				ALLOW_PUT = true;
  			}
  
  			if (m.getName().equals("doDelete")) {
  				ALLOW_DELETE = true;
  			}
  		}
  
  		String allow = null;
  		if (ALLOW_GET) {
  			allow = "GET";
  		}
  
  		if (ALLOW_HEAD) {
  			if (allow == null) {
  				allow = "HEAD";
  			} else {
  				allow = allow + ", HEAD";
  			}
  		}
  
  		if (ALLOW_POST) {
  			if (allow == null) {
  				allow = "POST";
  			} else {
  				allow = allow + ", POST";
  			}
  		}
  
  		if (ALLOW_PUT) {
  			if (allow == null) {
  				allow = "PUT";
  			} else {
  				allow = allow + ", PUT";
  			}
  		}
  
  		if (ALLOW_DELETE) {
  			if (allow == null) {
  				allow = "DELETE";
  			} else {
  				allow = allow + ", DELETE";
  			}
  		}
  
  		if (ALLOW_TRACE) {
  			if (allow == null) {
  				allow = "TRACE";
  			} else {
  				allow = allow + ", TRACE";
  			}
  		}
  
  		if (ALLOW_OPTIONS) {
  			if (allow == null) {
  				allow = "OPTIONS";
  			} else {
  				allow = allow + ", OPTIONS";
  			}
  		}
  
  		resp.setHeader("Allow", allow);
  	}
  ```

  - getAllDeclaredMethods는 재귀호출을 통해서 부모 객체의 사용가능한 모든 메소드를 배열로 만든다.
  - "org.apache.catalina.connector.RequestFacade"를 리플렉션 API로 allowTrace 확인 후 boolean 값 지정 (기본값 false)
  - 문자열로 저장하여 HEADER에 지정

  
