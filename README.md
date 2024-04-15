# Enshrine 收藏你喜欢的明星 😭

## 介绍

基于 Vue + Kotlin 的完全开源项目，使用 MIT 协议

他能够帮助你收藏你喜欢的明星，视频，网站  
例如 Ikun 的唱跳 Rap 🏀

## 想自己部署？

> [!TIP]
> 后端部署

项目数据库默认使用 `Mariadb`，可以去 `pom.xml` `application.yml` 自行修改  
使用 `maven` 构建，如果有条件可以自己换成 `gradle`  
来到项目根目录

### Step.1 构建前端项目

```bash
cd ./client
pnpm install
pnpm run build
```

执行完之后把 dist 目录移动到 `src/main/resources/`

### Step.2 安装依赖，如果你有 IDE 可以跳过这一步

```bash
mvn install
```

### Step.3 开始打包并运行

```bash
mvn package
cd ./target/
java -jar enshrine-x.x.x.jar
```
