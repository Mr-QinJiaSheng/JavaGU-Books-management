
如何运行项目？
     1. 下载代码后解压缩

     2. 找到项目中的sql文件夹，将sql导入到mysql中，配置好数据源

     3.导入

	idea导入：直接新窗口打开项目文件夹，修改数据库的用户名和密码（com.jsaon->frame->DbOp.java），运行Login.java即可
	eclipse导入：选择从本地导入到工程中，修改数据库的用户名和密码（com.jsaon->frame->DbOp.java），运行Login.java即可
技术架构
     Java GUI: awt

需要注意的点
    1.确保环境安装好
    2.文件编码设置为GBK，不然会乱码
    3.不要随意改变文件结构，不然图片可能会找不到