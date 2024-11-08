# ****HttpMessageConverter****

1.HttpMessageConverter，报文信息转换器，将请求报文转换为Java对象，或将Java对象转换为响应报文

2.HttpMessageConverter提供了两个注解和两个类型：@RequestBody请求体，@ResponseBody响应体，RequestEntity，ResponseEntity

3.请求报文：请求头、请求空行、请求体。GET请求没有请求体，POST请求的Content-Type决定了请求体的格式和内容

4.@RequestBody可以获取请求体，需要在控制器方法设置一个形参，使用@RequestBody进行标识，当前请求的请求体就会为当前注解所标识的形参赋值

5.RequestEntity封装请求报文的一种类型，需要在控制器方法的形参中设置该类型的形参，当前请求的请求报文就会赋值给该形参，可以通过getHeaders()获取请求头信息，通过getBody()获取请求体信息

6.@ResponseBody用于标识一个控制器方法，可以将该方法的返回值直接作为响应报文的响应体响应到浏览器。@ResponseBody注解，所以返回的是字符串，而不是视图名
 
7.SpringMVC处理json：使用@ResponseBody处理json的步骤：a>在pom.xml文件中导入jackson的依赖
        b>在SpringMVC的核心配置文件中开启mvc的注解驱动<mvc:annotation-driven />，此时在HandlerAdaptor中会自动装配一个消息转换器：MappingJackson2HttpMessageConverter，可以将响应到浏览器的Java对象转换为Json格式的字符串
        c>在控制器方法上使用@ResponseBody注解，标识当前方法返回值将直接作为响应报文的响应体响应到浏览器
        d>将Java对象直接作为控制器方法的返回值返回，就会自动转换为Json格式的字符串，响应到浏览器
8.json:有两种格式：一种是对象格式{}通过键值获取，另一种是数组格式[]通过循环获取.
        java实体类、Map对象转换为json对象，Java List集合转换为json数组
9.AJAX（Asynchronous JavaScript and XML）是一种用于在网页上进行异步数据交换和更新的技术，允许网页在不重新加载整个页面的情况下与服务器交换数据并进行更新。AJAX 使得网页能够更快地响应用户的操作，提供更流畅的用户体验。
        主要特点：
        异步：AJAX 可以在后台与服务器交换数据，而不需要刷新页面。这意味着用户可以继续与页面进行交互，而不必等待整个页面加载。
        数据格式：尽管 AJAX 这个名字中包含了 XML，但它并不仅限于 XML 数据格式。现代应用中，通常使用 JSON 格式交换数据。AJAX 支持多种数据格式，包括 JSON、XML、HTML 或纯文本。
        轻量级：AJAX 请求通常只传输必要的数据，不需要加载整个页面，因此比传统的全页面刷新方式更节省带宽和提高性能。
        工作原理：
        AJAX 通常通过 JavaScript 来发起 HTTP 请求，获取数据，然后动态更新网页的一部分。其主要流程如下：
        触发事件：用户在页面上触发某些操作，例如点击按钮、滚动、选择项等。
        发送请求：JavaScript 使用 XMLHttpRequest 对象或 fetch API 向服务器发起 HTTP 请求，通常是 GET 或 POST 请求。
        处理响应：服务器接收到请求后，返回数据，通常是 JSON 或 XML 格式。
        更新页面：JavaScript 解析服务器返回的数据，并根据数据更新网页的某一部分（如文本、图像、表格等）。
10.@RestController注解是springMVC提供的一个复合注解，标识在控制器的类上，就相当于为类添加了@Controller注解，并且为其中的每个方法添加了@ResponseBody注解
11.ResponseEntity用于控制器方法的返回值类型，该控制器方法的返回值就是响应到浏览器的响应报文
12.ResponseEntity常用于文件的上传下载功能（底层都是文件复制,以流的形式传递）
13.文件上传：a>要求form表单的请求方式必须为post，并且添加属性enctype="multipart/form-data"，将表单的请求体类型更改为二进制格式
        b>添加依赖commons-fileupload
        c>在SpringMVC的配置文件中配置文件上传解析器CommonsMultipartResolver，上传的文件会封装到MultipartFile对象中 
        d>创建控制器方法，并且在控制器方法的形参中设置MultipartFile类型的形参，框架会自动将请求中的文件项赋值给该形参

































