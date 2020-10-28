# Servlet的生命周期

Servlet没有main()方法，不能独立运行，它的运行完全由Servlet引擎来控制和调度，生命周期指的是**servlet容器何时创建servlet实例**，**何时调用其方法进行请求的处理**，**何时并销毁其实例的整个过程**。

1. 实例和初始化时机

   当请求到达容器时，容器查找该servlet对象是否存在，如果不存在，则会创建实例并进行初始化

2. 就绪/调用/服务阶段

   有请求到达容器，容器调用servlet对象的service()方法，处理请求的方法在整个生命周期可以被多次调用，HttpServlet的service()方法，会依据请求方式来调用doGet()或者doPost()方法。但是，这两个do方法默认情况下，会抛出异常，需要子类去override。

3. 销毁时机

   当容器关闭时(应用程序停止时)，会将程序中的Servlet实例进行销毁

上述生命周期可以通过Servlet中的生命周期方法来观察。在Servlet中有三个生命周期方法，不由用户手动调用，而是在特定的时机由容器自动调用，观察这三个生命周期的方法，即可观察到Servlet的生命周期1.

1. Servlet类加载，实例化（只能一次）
2. 服务（可以执行多次）
3. 销毁（只能一次）

------

init方法，在Servlet实例创建之后执行（证明该Servlet有实例创建了）

```Java
    /**
     * 初始化方法
     * 系统方法，由服务器自动调用
     * 当请求到达Servlet容器时，Servlet容器会判断该Servlet对象是否存在，如果不存在，则创建实例并初始化
     * 该方法只会执行一次
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        System.out.println("Servlet被创建");
    }
```

service方法，每次有请求到达某个Servlet方法时执行，用来处理请求（证明该Servlet进行服务了）

```Java
 	/**
     * 就绪/服务方法（处理请求数据）
     * 系统方法，服务器自动调用
     * 当有请求到达Servlet时，就会调用该方法
     * 方法可以被多次调用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Servlet被调用");
    }
```

destroy方法，Servlet实例销毁时执行（证明该Servlet的实例被销毁了）

```Java
	/**
     * 销毁方法
     * 系统方法，服务器自动调用
     * 当夫妻关闭或应用程序停止时，调用方法
     * 该方法只会执行一次
     */
    @Override
    public void destroy() {
        System.out.printf("Servlet被销毁");
    }

```

