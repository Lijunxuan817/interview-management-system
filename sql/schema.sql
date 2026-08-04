USE ta_recruitment;

CREATE TABLE IF NOT EXISTS users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(120) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  role ENUM('applicant','mo','admin') NOT NULL,
  name VARCHAR(80) NOT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS applicant_profiles (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL UNIQUE,
  major VARCHAR(80), grade VARCHAR(20),
  skills VARCHAR(500), phone VARCHAR(30),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS resumes (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  file_path VARCHAR(255) NOT NULL,
  uploaded_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS positions (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  mo_id BIGINT NOT NULL,
  title VARCHAR(120) NOT NULL,
  course_name VARCHAR(80),
  type ENUM('course_ta','exam_proctor') DEFAULT 'course_ta',
  requirements TEXT,
  quota INT DEFAULT 1,
  status ENUM('open','closed') DEFAULT 'open',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (mo_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS applications (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  position_id BIGINT NOT NULL,
  applicant_id BIGINT NOT NULL,
  status ENUM('pending','accepted','rejected') DEFAULT 'pending',
  applied_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  reviewed_at DATETIME NULL,
  UNIQUE KEY uk_position_applicant (position_id, applicant_id),
  FOREIGN KEY (position_id) REFERENCES positions(id),
  FOREIGN KEY (applicant_id) REFERENCES users(id)
);
