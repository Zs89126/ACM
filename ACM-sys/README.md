ACM-sys
ACM报名系统后端

请注意拉取代码后在github上创建自己的分支，命名规则：另说

Spring-boot后端接口编写流程

Step1.根据数据库表格创建实体类(entity文件夹下)
请注意java后端数据类型与SQLServer数据库字段类型的对应关系：
java----------->sqlServer
String--------->CHAR/VARCHAR/text
Long---------->bigint/int
Date---------->datetime

Step2.创建Mapper接口文件(mapper文件夹下)

Step3.创建Service接口文件和实现类(Service文件夹下)

Step4.创建Controller接口文件(Controller文件夹下)
相关注释
@ApiOperation(value = "接口名字", notes = "接口描述信息")
@ApiOperationSupport(order = 1)（接口文档排列顺序）
@GetMapping("在网址url上显示的接口名称")

Step5.访问 http://localhost:1234/ACM-sys/doc.html#/home 查看接口文档