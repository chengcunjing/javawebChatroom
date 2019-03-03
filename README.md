一，项目工具：
IDE：Eclipe 
服务器：Tomcat
数据库：mysql+Navicat for MySQL

二，项目完成的功能：

用户登陆，注册，找回密码

记录登录人数，在线人数，页面访问次数，

显示用户信息，查询用户信息，更改用户信息，信息分页及聊天功能

三，技术栈：
1.dao
2.listener
3.fitler
四，具体实现：
（1）编写了一个登录页面，登录信息中有用户名和密码，分别用三个按钮来登录和注册和忘记密码登录信息。

（2）编写了一个Servlet程序chatroomServlet.java通过请求指派来处理用户提交的登录信息，如果用户名和密码正确时，跳转到showMessage.jsp显示用户信息跳转，利用RequestDispatcher.forward方法来完成页面跳转功能；否则跳转到err页面，提示用户重新登录(注：当提交密码错误时页面又会重定向到homepage.jsp页面)。

（3）编写了两个Servlet程序，分别用来显示“信息输入”窗口和“聊天记录显示”窗口的内容；用户在“信息输入”窗口中键入聊天内容，点击“发送”按钮后，在“聊天记录显示”窗口中显示发送消息的用户名称和聊天内容。提示：利用HTML中的textarea标签来实现。

（4）在登录界面上实现记住用户名和密码的功能，使得当用户选择了此功能并成功登录后，在其下次登录时可以不用再输入用户名和密码即可登录，此功能通过两个Cookie来实现。

（5）编写了一个Listener程序来监听会话的创建和销毁事件，以此统计当前在线（登录）人数，并将其显示在聊天界面上。

（6）添加了一个Filter对本系统所有的Servlet程序进行过滤，该Filter实现对请求和响应对象的编码格式的设置
