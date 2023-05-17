# asm_time_cost

使用kotlin的插件形式  
使用AsmClassVisitorFactory和asm插桩  
计算方法的执行时间，可以设置打印的tag  
### 使用方法
在build.gradle中加入
```
classpath "com.upuphone.asm:TimeCostPlugin:1.0.20-SNAPSHOT"
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
@Duration(tag = "logTag")
```
注解有继承效果，可以在接口上添加，所有的实现类都可以自动添加
