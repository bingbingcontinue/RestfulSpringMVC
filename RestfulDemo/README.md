**REST：Representational State Transfer，表现层资源状态转移。**

a.资源
资源是一种看待服务器的方式，即，将服务器看作是由很多离散的资源组成。每个资源是服务器上一个可命名的抽象概念。因为资源是一个抽象的概念，所以它不仅仅能代表服务器文件系统中的一个文件、数据库中的一张表等等具体的东西，可以将资源设计的要多抽象有多抽象，只要想象力允许而且客户端应用开发者能够理解。与面向对象设计类似，资源是以名词为核心来组织的，首先关注的是名词。一个资源可以由一个或多个URI来标识。URI既是资源的名称，也是资源在Web上的地址。对某个资源感兴趣的客户端应用，可以通过资源的URI与其进行交互。

b.资源的表述
资源的表述是一段对于资源在某个特定时刻的状态的描述。可以在客户端-服务器端之间转移（交换）。资源的表述可以有多种格式，例如HTML/XML/JSON/纯文本/图片/视频/音频等等。资源的表述格式可以通过协商机制来确定。请求-响应方向的表述通常使用不同的格式。

c.状态转移
状态转移说的是：在客户端和服务器端之间转移（transfer）代表资源状态的表述。通过转移和操作资源的表述，来间接实现操作资源的目的。

2、RESTful的实现
具体说，就是 HTTP 协议里面，四个表示操作方式的动词：GET、POST、PUT、DELETE。

它们分别对应四种基本操作：GET 用来获取资源，POST 用来新建资源，PUT 用来更新资源，DELETE 用来删除资源。

REST 风格提倡 URL 地址使用统一的风格设计，从前到后各个单词使用斜杠分开，不使用问号键值对方式携带请求参数，而是将要发送给服务器的数据作为 URL 地址的一部分，以保证整体风格的一致性。

3、HiddenHttpMethodFilter
由于浏览器只支持发送get和post方式的请求，那么该如何发送put和delete请求呢？

SpringMVC 提供了 HiddenHttpMethodFilter 帮助我们将 POST 请求转换为 DELETE 或 PUT 请求

HiddenHttpMethodFilter 处理put和delete请求的条件：

a>当前请求的请求方式必须为post

b>当前请求必须传输请求参数_method

满足以上条件，HiddenHttpMethodFilter 过滤器就会将当前请求的请求方式转换为请求参数_method的值，因此请求参数_method的值才是最终的请求方式

在web.xml中注册HiddenHttpMethodFilter

在web.xml中注册时，必须先注册CharacterEncodingFilter，再注册HiddenHttpMethodFilter

原因：

在 CharacterEncodingFilter 中通过 request.setCharacterEncoding(encoding) 方法设置字符集的

request.setCharacterEncoding(encoding) 方法要求前面不能有任何获取请求参数的操作

而 HiddenHttpMethodFilter 恰恰有一个获取请求方式的操作：

String paramValue = request.getParameter(this.methodParam);


功能	URL 地址	请求方式
访问首页√	/	GET
查询全部数据√	/employee	GET
删除√	/employee/2	DELETE
跳转到添加数据页面√	/toAdd	GET
执行保存√	/employee	POST
跳转到更新数据页面√	/employee/2	GET
执行更新√	/employee	PUT

1.没有操作，直接跳转页面的，使用了springMVC.xml中的mvc view-controller注解
2.使用了thymeleaf解析器
3.增删改查的功能，浏览器可以提交get和post请求，即查和增。对于删和改对应的delete和put请求,在Restful风格中，html中注意要用到name="_method" value="put"
4.对于难点删除功能，使用超链接进行删除，用到了vue.js。而且要注意！！静态资源的引用！！
5.在springMVC.xml用到了<mvc:default-servlet-handler />这是用于开放对静态资源的访问。
      对静态资源的访问首先被前端控制器处理，如果没找到就交给默认的servlet处理，如果还是没找到就报404（控制台不报，因为没用到springMVC）
6.方法一：由于我引用静态资源/webapp/static/js/vue.js总是被告知405  Request method 'GET' not supported。  配置application.properties文件，并写入内容  spring.mvc.static-path-pattern=/**     可以成功
  方法二：我引用了网络资源<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>  无需配置application.properties可以成功
