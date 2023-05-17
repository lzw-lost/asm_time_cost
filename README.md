# asm_time_cost


使用kotlin的插件形式  
使用AsmClassVisitorFactory和asm插桩  
计算方法的执行时间，可以设置打印的tag  
### 使用方法
使用feature-dev分支
在build.gradle中加入
```
classpath "com.upuphone.asm:TimeCostPlugin:2.0.2"
```
在app的项目中引入插件
```
apply plugin: 'com.upuphone.asm'
```

引入依赖包
```
implementation 'com.upuphone.durationanotation:durationAnotation:2.0.2'
```

在需要测量的class或者method上添加注解
```
@Duration(tag = "logTag",endClassName = "com.example.myapplication.TestActivity",endMethodName = "onResume" )
```
endClassName是结束打印时间的类名，endMethodName是结束打印方方法的方法名

本工具不直接支持跨进程，多线程的情况下，测量会不准确
原理就是在添加注解的地方将时间记录进入map，在结束的方法结尾取出时间，进行相减。 默认会打印添加注解的本方法的执行时长，过滤条件为"time-cost======== ",跨方法执行时长的过滤条件为"time-duration== "
当然跨进程调用的调用时间也可以测算，距离A进程的方法m,调用B进程的方法n，那就在m方法上添加注解，在n方法上添加注解（这个注解需要加上结束方法endClassName和endMethodName），此时m方法会打印时间戳，同时endClassName也会打印结束的时间戳，相减即可得出整个的执行时长

注解有继承效果，可以在接口上添加，所有的实现类都可以自动添加
