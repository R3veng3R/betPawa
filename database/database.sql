--
-- Devart dbForge Studio 2019 for MySQL 8.1.45.0
-- http://www.devart.com/ru/dbforge/mysql/studio
-- DATE CREATED: 18/05/2019 14:06:23
-- Database version: 5.7.26-log
--

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


USE betpawa;

--
-- DROP TABLE `comments`
--
DROP TABLE IF EXISTS comments;

--
-- DROP TABLE `tasks`
--
DROP TABLE IF EXISTS tasks;

--
-- DROP TABLE `users`
--
DROP TABLE IF EXISTS users;

--
-- DB NAME
--
USE betpawa;

--
-- CREATE TABLE `users`
--
CREATE TABLE users (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  last_name varchar(100) NOT NULL,
  is_active tinyint(1) NOT NULL DEFAULT 1,
  email varchar(100) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 4,
AVG_ROW_LENGTH = 16384,
CHARACTER SET utf8,
COLLATE utf8_general_ci;

--
-- CREATE TABLE `tasks`
--
CREATE TABLE tasks (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  title varchar(150) NOT NULL,
  description text DEFAULT NULL,
  priority enum ('LOW', 'MEDIUM', 'HIGH') NOT NULL DEFAULT 'LOW',
  create_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  due_date date NOT NULL,
  user_id bigint(20) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 56,
AVG_ROW_LENGTH = 16384,
CHARACTER SET utf8,
COLLATE utf8_general_ci;

--
-- `IDX_tasks_user_id` `tasks`
--
ALTER TABLE tasks
ADD INDEX IDX_tasks_user_id (user_id);

--
--
ALTER TABLE tasks
ADD CONSTRAINT FK_tasks_user_id FOREIGN KEY (user_id)
REFERENCES users (id) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- CREATE TABLE `comments`
--
CREATE TABLE comments (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  create_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  comment varchar(255) NOT NULL,
  task_id bigint(20) NOT NULL,
  user_id bigint(20) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 57,
AVG_ROW_LENGTH = 8192,
CHARACTER SET utf8,
COLLATE utf8_general_ci;

--
-- `IDX_comments_user_id` `comments`
--
ALTER TABLE comments
ADD INDEX IDX_comments_user_id (user_id);

--
--
ALTER TABLE comments
ADD CONSTRAINT FK_comments_task_id FOREIGN KEY (task_id)
REFERENCES tasks (id) ON DELETE CASCADE ON UPDATE NO ACTION;

--
--
ALTER TABLE comments
ADD CONSTRAINT FK_comments_user_id FOREIGN KEY (user_id)
REFERENCES users (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

-- 
--  users
--
INSERT INTO users VALUES
(1, 'Aleksei', 'Jermatsenkov', 1, 'aj@gmail.com'),
(3, 'TestUser', 'TestLastname', 1, 'test@test.com');

-- 
-- tasks
--
INSERT INTO tasks VALUES
(50, 'What is Lorem Ipsum? <no delete>', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', 'MEDIUM', '2019-05-15 22:16:48', '2019-05-15', 1);

-- 
--  comments
--
INSERT INTO comments VALUES
(48, '2019-05-15 22:16:48', 'Meeting room avaialable? lorum ipsum lorum ipsum', 50, 1),
(50, '2019-05-17 20:31:28', 'Hello, yes it is available!', 50, 3);

-- 
-- SQL (SQL mode)
-- 
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;