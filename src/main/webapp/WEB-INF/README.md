前端安装与编译

安装准备：
1.	安装nodejs6+
2.	打开nodejs 命令窗口
 
3.	新版本的nodejs内置npm，不需要独立安装了
4.	安装bower，命令：npm install bower –g
 
5.	安装grunt ，命令：npm install grunt-cli –g   （可选： --save-dev ）
 


编译步骤：
1、	打开nodejs 命令窗口，cd进入webapp目录
2、	运行命令npm install
3、	运行命令 bower install
4、	打包。不压缩：grunt serve，压缩： grunt serve:dist 


