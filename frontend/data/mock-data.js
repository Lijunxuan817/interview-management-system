window.MOCK = {
  positions: [
    { id: 1, title: "数据结构助教", course_name: "CS101", quota: 2, requirements: "熟悉 Java", status: "open" },
    { id: 2, title: "监考助教", course_name: "MATH201", quota: 1, requirements: "时间灵活", status: "open" },
  ],
  applications: [
    { applicant_name: "张三", position_title: "数据结构助教", status: "pending" },
    { applicant_name: "张三", position_title: "监考助教", status: "rejected" },
    { applicant_name: "王五", position_title: "数据结构助教", status: "accepted" },
    { applicant_name: "李四", position_title: "监考助教", status: "pending" },
  ],
  workloads: [
    { name: "王五", accepted_count: 2, load_level: "偏高" },
    { name: "张三", accepted_count: 0, load_level: "正常" },
  ],
  users: [
    { email: "zhang@stu.edu.cn", password: "123456", role: "applicant", name: "张三" },
    { email: "mo@edu.cn", password: "123456", role: "mo", name: "李老师" },
    { email: "admin@edu.cn", password: "123456", role: "admin", name: "管理员" },
  ],
};
