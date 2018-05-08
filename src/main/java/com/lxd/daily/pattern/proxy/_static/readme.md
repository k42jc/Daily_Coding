# 静态代理

代理类与目标类实现同一个接口：Subject

RealSubject：目标类，处理真实逻辑的类，实现Subject接口

ProxySubject：代理类，维持RealSubject的引用，实现Subject接口，覆盖方法时调用RealSubject的真实处理方法