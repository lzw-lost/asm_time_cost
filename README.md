# asm_time_cost

使用kotlin的插件形式
使用AsmClassVisitorFactory和asm插桩
计算方法的执行时间，可以设置打印的tag
使用方法
在build.gradle中加入

```classpath "com.upuphone.asm:TimeCostPlugin:1.0.20-SNAPSHOT"```
在app的项目中引入插件
```id 'com.upuphone.asm'```

引入依赖包
```implementation 'com.upuphone.durationanotation:durationAnotation:1.0.20-SNAPSHOT'```

在需要测量的class或者method上添加注解
```@Duration(tag = "logTag")```
