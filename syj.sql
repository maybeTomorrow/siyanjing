CREATE DATABASE  IF NOT EXISTS `siyanjing` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `siyanjing`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: siyanjing
-- ------------------------------------------------------
-- Server version	5.5.25a

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `content` varchar(45) DEFAULT NULL,
  `author` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `auth_idx` (`author`),
  CONSTRAINT `auth` FOREIGN KEY (`author`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_photo`
--

DROP TABLE IF EXISTS `activity_photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_photo` (
  `id` bigint(20) NOT NULL,
  `activity_id` bigint(20) NOT NULL,
  `path` varchar(200) DEFAULT NULL,
  `describ` varchar(500) DEFAULT NULL,
  `activity_photocol` varchar(45) DEFAULT NULL,
  `activity_photocol1` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_idx` (`activity_id`),
  CONSTRAINT `photo` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_photo`
--

LOCK TABLES `activity_photo` WRITE;
/*!40000 ALTER TABLE `activity_photo` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_photo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_record`
--

DROP TABLE IF EXISTS `activity_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_record` (
  `activity_id` bigint(20) NOT NULL,
  `views` int(11) DEFAULT '0',
  `comments` int(11) DEFAULT '0',
  PRIMARY KEY (`activity_id`),
  UNIQUE KEY `activity_id_UNIQUE` (`activity_id`),
  CONSTRAINT `avtivity_id` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_record`
--

LOCK TABLES `activity_record` WRITE;
/*!40000 ALTER TABLE `activity_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_summary`
--

DROP TABLE IF EXISTS `activity_summary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_summary` (
  `id` bigint(20) NOT NULL,
  `activity_id` bigint(20) NOT NULL,
  `content` longtext,
  PRIMARY KEY (`id`),
  KEY `fk_idx` (`activity_id`),
  CONSTRAINT `fk` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_summary`
--

LOCK TABLES `activity_summary` WRITE;
/*!40000 ALTER TABLE `activity_summary` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_summary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_tags`
--

DROP TABLE IF EXISTS `activity_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_tags` (
  `id` bigint(20) NOT NULL,
  `activity_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `activ_idx` (`activity_id`),
  KEY `tag_idx` (`tag_id`),
  CONSTRAINT `activ` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tag` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_tags`
--

LOCK TABLES `activity_tags` WRITE;
/*!40000 ALTER TABLE `activity_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `content` varchar(255) NOT NULL,
  `author` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `author_idx` (`author`),
  CONSTRAINT `author` FOREIGN KEY (`author`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_record`
--

DROP TABLE IF EXISTS `blog_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog_record` (
  `blog_id` bigint(20) NOT NULL,
  `views` int(11) DEFAULT '0',
  `comments` int(11) DEFAULT '0',
  PRIMARY KEY (`blog_id`),
  UNIQUE KEY `blog_id_UNIQUE` (`blog_id`),
  CONSTRAINT `blog_id` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_record`
--

LOCK TABLES `blog_record` WRITE;
/*!40000 ALTER TABLE `blog_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_tags`
--

DROP TABLE IF EXISTS `blog_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog_tags` (
  `blog_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  PRIMARY KEY (`blog_id`,`tag_id`),
  KEY `blog_id_idx` (`blog_id`),
  KEY `tag_id_idx` (`tag_id`),
  CONSTRAINT `blog_fid` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tag_fid` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_tags`
--

LOCK TABLES `blog_tags` WRITE;
/*!40000 ALTER TABLE `blog_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `target` varchar(45) NOT NULL,
  `comment_uname` varchar(45) DEFAULT NULL,
  `comment_unickname` varchar(45) DEFAULT NULL,
  `at_uname` varchar(45) DEFAULT NULL,
  `at_unickname` varchar(45) DEFAULT NULL,
  `comment` varchar(255) NOT NULL,
  `comment_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `target` (`target`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'job_4','test','test',NULL,NULL,'zgeshfjdhfdsfhdsdsfdsfds ','2013-04-20 07:17:29'),(2,'job_4','test','测试中文',NULL,NULL,'zgeshfjdhfdsfhdsdsfdsfds ','2013-04-20 12:18:09'),(3,'job_4','test','test',NULL,NULL,'zgeshfjdhfdsfhdsdsfdsfds ','2013-04-20 07:29:14');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `content` varchar(255) NOT NULL,
  `author` bigint(20) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `salary` varchar(45) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  `city` varchar(45) DEFAULT NULL,
  `company` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `recruiting_numbers` int(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `author_wglnfkp_idx` (`author`),
  CONSTRAINT `author_wglnfkp` FOREIGN KEY (`author`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (4,'有钱','1、有钱\r\n2、有钱',1,4,'2500199999','2013-04-12 18:01:19','有钱','有钱','有钱',1,'有钱');
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_record`
--

DROP TABLE IF EXISTS `job_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_record` (
  `id` bigint(20) NOT NULL,
  `views` int(11) DEFAULT '0',
  `comments` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `job_id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_record`
--

LOCK TABLES `job_record` WRITE;
/*!40000 ALTER TABLE `job_record` DISABLE KEYS */;
INSERT INTO `job_record` VALUES (1,0,0);
/*!40000 ALTER TABLE `job_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_tags`
--

DROP TABLE IF EXISTS `job_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_tags` (
  `job_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  PRIMARY KEY (`job_id`,`tag_id`),
  KEY `job_id_idx` (`job_id`),
  KEY `tag-id_idx` (`tag_id`),
  CONSTRAINT `job_id` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tag-id` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_tags`
--

LOCK TABLES `job_tags` WRITE;
/*!40000 ALTER TABLE `job_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `job_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `content` varchar(255) NOT NULL,
  `author` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `author_idx` (`author`),
  CONSTRAINT `author_id` FOREIGN KEY (`author`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news_record`
--

DROP TABLE IF EXISTS `news_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news_record` (
  `news_id` bigint(20) NOT NULL,
  `views` int(11) DEFAULT '0',
  `comments` int(11) DEFAULT '0',
  PRIMARY KEY (`news_id`),
  UNIQUE KEY `news_id_UNIQUE` (`news_id`),
  CONSTRAINT `news_id` FOREIGN KEY (`news_id`) REFERENCES `news` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_record`
--

LOCK TABLES `news_record` WRITE;
/*!40000 ALTER TABLE `news_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `news_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news_tags`
--

DROP TABLE IF EXISTS `news_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news_tags` (
  `news_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  PRIMARY KEY (`news_id`,`tag_id`),
  KEY `activity_idx` (`news_id`),
  KEY `tags_id` (`tag_id`),
  CONSTRAINT `tags_id` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `news` FOREIGN KEY (`news_id`) REFERENCES `news` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_tags`
--

LOCK TABLES `news_tags` WRITE;
/*!40000 ALTER TABLE `news_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `news_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slider`
--

DROP TABLE IF EXISTS `slider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `slider` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `content` varchar(45) NOT NULL,
  `img` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `priority` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slider`
--

LOCK TABLES `slider` WRITE;
/*!40000 ALTER TABLE `slider` DISABLE KEYS */;
INSERT INTO `slider` VALUES (1,'test1test1test1test1test1test1test1test1test1','test_content','http://myjpg.qire123.com:84/myupload/newpic/files/201302/19/512335911037f.jpg','http://siyanjing.com/news/1',1),(2,'test12','test_content2','http://myjpg.qire123.com:84/myupload/newpic/files/201302/19/512335911037f.jpg','http://siyanjing.com/news/2',2);
/*!40000 ALTER TABLE `slider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sysconfig`
--

DROP TABLE IF EXISTS `sysconfig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sysconfig` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `config_key` varchar(45) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_UNIQUE` (`config_key`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sysconfig`
--

LOCK TABLES `sysconfig` WRITE;
/*!40000 ALTER TABLE `sysconfig` DISABLE KEYS */;
INSERT INTO `sysconfig` VALUES (1,'ALLOW_REGISTE','true'),(2,'INVITE_REGISTE_MSG','you need been invite!');
/*!40000 ALTER TABLE `sysconfig` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NOT NULL,
  `gender` varchar(20) NOT NULL,
  `birth` varchar(45) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `blog` varchar(100) DEFAULT NULL,
  `wunit` varchar(255) DEFAULT NULL,
  `wpost` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `USER_ID` (`user_id`),
  KEY `USERID_idx` (`user_id`),
  CONSTRAINT `USERID` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,2,'ROLE_USER'),(2,6,'ROLE_ADMIN');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `username` varchar(15) NOT NULL,
  `password` varchar(60) NOT NULL,
  `name` varchar(45) NOT NULL,
  `email` varchar(25) NOT NULL,
  `avatar` varchar(45) DEFAULT NULL,
  `verify` varchar(255) DEFAULT NULL,
  `verified` bit(1) NOT NULL,
  `lastlogin` datetime DEFAULT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_uname` (`username`),
  UNIQUE KEY `index_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2013-04-12 01:42:26','123','123','234','234',NULL,NULL,'',NULL,''),(2,'2013-04-17 14:13:17','test','43bac31e54aeb1990bde3aefece94a1dcae7ceaf','test','dev@thinkjet.me',NULL,NULL,'','2013-04-28 19:08:25',''),(3,'2013-04-20 04:48:05','zyc','91cfddcd3362c3f22bd95b9f33f826051d8e488f','','tide.zyc@gmail.com',NULL,'oFF32p2aUVbnjoV6evYfuWMucxqCO1Ddvp9','\0',NULL,''),(5,'2013-04-20 04:58:40','test123','6800120beaf782406be23d977e9f2928d172de37','','zyc1016@126.com',NULL,'YtwxGH2FIN39GnFuMXct6nP3hV5VJXV5bbI','\0',NULL,''),(6,'2013-04-21 05:21:49','admin','a40546cc4fd6a12572828bb803380888ad1bfdab','admin','zyc@thinkjet.me',NULL,'Z3dJMeahWwTz9xdL6Mvk64byopb5294UJMS','','2013-05-02 23:11:33','');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-05-03  9:32:31
