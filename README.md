# 助教招聘系统

高校课程助教、监考等岗位在线招聘：发岗 → 投递 → 审核 → 查进度 → 看负荷。

## 角色

| 角色 | 做什么 |
|------|--------|
| 助教申请者 | 登录、填档案、传简历、浏览岗位、投递、查进度 |
| 课程组织者 | 发岗位、看申请、通过/拒绝 |
| 管理员 | 看全员已通过岗位与负荷 |

## 目录

```
docs/          需求文档（用户故事、功能清单）
frontend/      静态页面（Chrome 打开 index.html）
backend/       Spring Boot 后端（第 2 周开始）
sql/           MySQL 建表脚本 schema.sql
```

## 本地看前端

1. 用 Chrome 打开 `frontend/index.html`
2. 测试登录：`zhang@stu.edu.cn` / `mo@edu.cn` / `admin@edu.cn`，密码 `123456`

## 技术栈

Java 17 · Spring Boot 3 · MySQL 8 · Redis · HTML/CSS/JS

## 分支

日常开发在 `dev`；`main` 为稳定快照。
