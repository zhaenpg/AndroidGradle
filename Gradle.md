# Gradle

## Groovy语法	

​	参考Groovy官方文档即可。



## 基础

### Gradle构建生命周期

​	gradle构建分为3个阶段：

1. Initialization：Gradle支持单个和多项目构建。在 Initialization 阶段，Gradle 将会确定哪些项目将参与构建，并为每个项目创建一个 Project 对象实例
2. Configuration：在这一阶段项目配置对象，所有项目的构建脚本将会被「执行」，这样才能够知道各个 task 之间的依赖关系
3. Execution：task 的执行阶段。首先执行 doFirst {} 闭包中的内容，最后执行 doLast {} 闭包中的内容

### Gradle中常用的命令

- gradlew app:dependencies

  查看项目的依赖树，不过输出信息会比较多，可以使用 **gradlew build --scan**，该命令利用scan工具生成html的分析文件

- 查看主要任务

  ```
  ./gradlew tasks
  ```

- 查看所有任务（包括缓存的）

  ```
  ./gradlew tasks --all
  ```

- 运行某个module下的task

  ```
  ./gradlew :moduleName:taskName
  ```

- 查看构建版本

  ```
  ./gradlew -v
  ```

- 清除build文件夹

  ```
  ./gradlew clean
  ```

- 检查依赖并编译打包

  ```
  ./gradlew build
  ```

- 编译并安装debug包

  ```
  ./gradlew installDebug
  ```

- 编译并打印日志

  ```
  ./gradlew build --info
  ```

- 编译并输出性能报告，性能报告一般在构建工程根目录 build/reports/profile 下

  ```
  ./gradlew build --profile
  ```

- 调试模式构建并打印堆栈日志

  ```
  ./gradlew build --info --debug --stacktrace
  ```

- 强制更新最新依赖，清除后再构建

  ```
  ./gradlew clean build --refresh--dependencies
  ```

- 编译并打debug包 / 编译并打release包

  ```
   ./gradlew assembleDebug(可简化为aD)    /   .gradlew assembleRelease(可简化为aR)
  ```

- Release模式打包并安装

  ```
  ./gradlew installRelease
  ```

- 卸载Release包

  ```
  ./gradlew uninstallRelease
  ```

- 全部渠道打包

  ```
  ./gradlew assemble
  ```

- 查看根目录下的依赖

  ```
  ./gradlew dependencies
  ```

- 查看app模块下的依赖

  ```
  ./gradlew app:dependencies  //同理，查看其它模块的依赖，修改app为其它module即可
  ```

- 查看app模块下包含implementation关键字得依赖项目

  ```
  ./gradlew app:dependencies --configuration implementation
  ```

- 利用Build Scan分析耗时的分析出导致应用构建速度慢的一些问题

  ```
  /gradlew build --scan
  ```

- 检测构建速度优化效果

  ```
  ./gradlew --profile --recompile-scripts --offline --rerun-tasks assembleDebug
  --profile开启性能检测
  --recompile-scripts不使用缓存，直接重新编译脚本
  --offline启用离线编译模式
  --rerun-tasks运行所有 gradle task 并忽略所有优化
  ```

## Task执行

```
 gradle myTask   # 执行某个Task
 gradle :my-subproject:taskName  # 执行子项目中的Task
 gradle my-subproject:taskName   # 同上，不指定子项目，会执行所有子项目的此Task，如gradle clean；
 gradle task1 task2  # 运行多个Task
 gradle dist --exclude-task test # 将某个task排除在执行外
 gradle dist -x test # 同上
 gradle test --rerun-tasks   # 强制执行UP-TO-DATE的Task，即不走增量编译，执行全量编译；
 gradle test --continue  # 默认情况下，一旦Task失败就会构建失败，通过此参数可继续执行；
```

### 常见的Task

```
 gradle build
 gradle run
 gradle check
 gradle clean    # 删除构建目录
```

## 构建细节

```
 gradle projects # 列出所有子项目
 gradle tasks    # 列出所有Task(分配给任务组的Task)
 gradle tasks --group="build setup"  # 列出特定任务组的Task
 gradle tasks --all  # 列出所有Task
 gradle -q help --task libs  # 查看某个Task的详细信息
 gradle myTask --scan    # 生成可视化的编译报告
 gradle dependencies # 列出项目依赖
 gradle -q project:properties    # 列出项目属性列表
```

## Gradle命令相关选项

### 调试选项

```
 -?，-h，--help  # 帮助信息
 -v，--version   # 版本信息
 -s, --stacktrace    # 打印出异常堆栈跟踪信息；
 -S, --full-stacktrace   # 比上面更完整的信息；
```

### 性能相关

```
 --build-cache   # 复用缓存
 --no-build-cache    # 不复用缓存，默认
 --max-workers   # 最大处理器数量
 --parallel  # 并行生成项目
 --no-parallel   # 不并行生成项目
 --priority  # Gradle启动的进程优先级
 --profile   # 生成性能报告
```

### 守护进程

```
 --daemon # 使用deamon进程构建
 --no-daemon # 不使用deamon进程构建
 --foreground    # 前台进程启动deamon进程
 --status    # 查看运行中和最近停止的deamon进程；
 --stop  # 停止所有同一版本的deamon进程；
```

### 日志选项

```
 -q, --quiet # 只记录错误
 -w, --warn
 -i, --info
 -d, --debug
 --console=(auto,plain,rich,verbose) # 指定输出类型
 --warning-mode=(all,fail,none,summary)  # 指定警告级别
```

### 执行选项

```
 --include-build # 复合构建
 --offline   # 离线构建
 --refresh-dependencies  # 强制清除依赖缓存
 --dry-run # 在不实际执行Task的情况下看Task执行顺序
 --no-rebuild # 不重复构建项目依赖
```

### 环境选项

```
 -b, --build-file # 指定构建文件
 -c, --settings-file # 指定设置文件
 -g, --gradle-user-home  # 指定默认.Gradle目录
 -p, --project-dir   # 指定Gradle的开始目录
 --project-cache-dir # 指定缓存目录，默认.gradle
 -D, --system-prop   # 设置JVM系统属性
 -I, --init-script   # 指定初始化脚本
 -P, --project-prop  # 指定根项目的项目属性；
```



## 参考资料

[Gradle官方文档]: https://docs.gradle.org/current/userguide/userguide.html
[Groovy官方文档]: http://www.groovy-lang.org/api.html
[补齐Android技能树 - 玩转Gradle(一) | 小册免费学]: https://juejin.cn/post/6950643579643494431
[Gradle 与 Android 构建入门]: https://juejin.cn/post/6844904121217056782
[7个你应该知道的Gradle实用技巧]: https://juejin.cn/post/6947675376835362846
[Android中的Gradle之配置及构建优化]: https://juejin.cn/post/6844903697823039501
[Android 进阶之路：ASM 修改字节码，这样学就对了！]: https://juejin.cn/post/6999646242125529096
[加快apk的构建速度，如何把编译时间从130秒降到17秒]: (https://www.jianshu.com/p/53923d8f241c)
[深度探索 Gradle 自动化构建技术（一、Gradle 核心配置篇）]: https://juejin.cn/post/6844904122492125198
[为什么说 Gradle 是 Android 进阶绕不去的坎]: https://mp.weixin.qq.com/s/bysl7n3MaHgxLvge-esSBQ
[寄Android开发Gradle你需要知道的知识]: https://juejin.cn/post/6844903603086295054#heading-28
[补齐Android技能树——从AGP构建过程到APK打包过程]: https://juejin.cn/post/6963527524609425415
[补齐Android技能树 - 从害怕到玩转Android代码混淆]: https://juejin.cn/post/6966526844552085512
[一文学会Android Gradle Transform基础使用]: https://juejin.cn/post/6914485867029463054
[Android gradle 7.0+完整配置记录]: https://www.cnblogs.com/bg-tab/p/16303565.html
[Gradle 系列（2）手把手带你自定义 Gradle 插件]: https://juejin.cn/post/7098383560746696718#heading-21
[深入探索编译插桩技术（四、ASM 探秘）]: https://juejin.cn/post/6844904118700474375#heading-4Hunter
[深入探索编译插桩技术（三、解密 JVM 字节码）]: https://juejin.cn/post/6844904116603486222