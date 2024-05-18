-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.27-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for school
CREATE DATABASE IF NOT EXISTS `school` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `school`;

-- Dumping structure for table school.course
CREATE TABLE IF NOT EXISTS `course` (
  `CourseID` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(100) NOT NULL,
  `Credits` int(11) NOT NULL,
  `DepartmentID` int(11) NOT NULL,
  PRIMARY KEY (`CourseID`),
  KEY `fk_course_department` (`DepartmentID`),
  CONSTRAINT `fk_course_department` FOREIGN KEY (`DepartmentID`) REFERENCES `department` (`DepartmentID`)
) ENGINE=InnoDB AUTO_INCREMENT=5002 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table school.course: ~12 rows (approximately)
INSERT INTO `course` (`CourseID`, `Title`, `Credits`, `DepartmentID`) VALUES
	(1045, 'Calculus', 4, 7),
	(1050, 'Chemistry', 4, 1),
	(1061, 'Physics', 4, 1),
	(2021, 'Composition', 3, 2),
	(2030, 'Poetry', 2, 2),
	(2042, 'Literature', 4, 2),
	(3141, 'Trigonometry', 4, 7),
	(4022, 'Microeconomics', 3, 4),
	(4041, 'Macroeconomics', 3, 4),
	(4061, 'Quantitative', 2, 4),
	(5000, 'Software Engineering', 4, 1),
	(5001, 'ádfsdf', 2, 2);

-- Dumping structure for table school.courseinstructor
CREATE TABLE IF NOT EXISTS `courseinstructor` (
  `CourseID` int(11) NOT NULL,
  `PersonID` int(11) NOT NULL,
  PRIMARY KEY (`CourseID`,`PersonID`),
  KEY `fk_courseinstructor_person` (`PersonID`),
  CONSTRAINT `fk_courseinstructor_course` FOREIGN KEY (`CourseID`) REFERENCES `course` (`CourseID`),
  CONSTRAINT `fk_courseinstructor_person` FOREIGN KEY (`PersonID`) REFERENCES `person` (`PersonID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table school.courseinstructor: ~11 rows (approximately)
INSERT INTO `courseinstructor` (`CourseID`, `PersonID`) VALUES
	(1045, 1),
	(1045, 5),
	(1050, 1),
	(1061, 31),
	(1061, 34),
	(2021, 1),
	(2021, 27),
	(2030, 4),
	(2042, 25),
	(4022, 18),
	(5000, 1);

-- Dumping structure for table school.department
CREATE TABLE IF NOT EXISTS `department` (
  `DepartmentID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Budget` double NOT NULL,
  `StartDate` datetime NOT NULL,
  `Administrator` int(11) DEFAULT NULL,
  PRIMARY KEY (`DepartmentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table school.department: ~4 rows (approximately)
INSERT INTO `department` (`DepartmentID`, `Name`, `Budget`, `StartDate`, `Administrator`) VALUES
	(1, 'Engineering', 350000, '2007-09-01 00:00:00', 2),
	(2, 'English', 120000, '2007-09-01 00:00:00', 6),
	(4, 'Economics', 200000, '2007-09-01 00:00:00', 4),
	(7, 'Mathematics', 250000, '2007-09-01 00:00:00', 3);

-- Dumping structure for table school.officeassignment
CREATE TABLE IF NOT EXISTS `officeassignment` (
  `InstructorID` int(11) NOT NULL,
  `Location` varchar(50) NOT NULL DEFAULT 'TP.HCM',
  `Timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`InstructorID`),
  CONSTRAINT `fk_officeassignment_person` FOREIGN KEY (`InstructorID`) REFERENCES `person` (`PersonID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table school.officeassignment: ~9 rows (approximately)
INSERT INTO `officeassignment` (`InstructorID`, `Location`, `Timestamp`) VALUES
	(1, '17 Smith', '2022-02-23 17:19:21'),
	(4, '29 Adams', '2022-02-23 17:19:21'),
	(5, '37 Williams', '2022-02-23 17:19:22'),
	(18, '143 Smith', '2022-02-23 17:19:22'),
	(25, '57 Adams', '2022-02-23 17:19:22'),
	(27, '271 Williams', '2022-02-23 17:19:22'),
	(31, '131 Smith', '2022-02-23 17:19:22'),
	(32, '203 Williams', '2022-02-23 17:19:22'),
	(34, '213 Smith', '2022-02-23 17:19:22');

-- Dumping structure for table school.onlinecourse
CREATE TABLE IF NOT EXISTS `onlinecourse` (
  `CourseID` int(11) NOT NULL,
  `url` varchar(100) NOT NULL,
  PRIMARY KEY (`CourseID`),
  CONSTRAINT `onlinecourse_ibfk_1` FOREIGN KEY (`CourseID`) REFERENCES `course` (`CourseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table school.onlinecourse: ~5 rows (approximately)
INSERT INTO `onlinecourse` (`CourseID`, `url`) VALUES
	(2021, 'http://www.fineartschool.net/Composition'),
	(2030, 'http://www.fineartschool.net/Poetry'),
	(3141, 'http://www.fineartschool.net/Trigonometry'),
	(4041, 'http://www.fineartschool.net/Macroeconomics'),
	(5000, 'abcd');

-- Dumping structure for table school.onsitecourse
CREATE TABLE IF NOT EXISTS `onsitecourse` (
  `CourseID` int(11) NOT NULL,
  `Location` varchar(50) NOT NULL,
  `Days` varchar(50) NOT NULL,
  `Time` time NOT NULL,
  PRIMARY KEY (`CourseID`),
  CONSTRAINT `fk_onsitecourse_course` FOREIGN KEY (`CourseID`) REFERENCES `course` (`CourseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table school.onsitecourse: ~7 rows (approximately)
INSERT INTO `onsitecourse` (`CourseID`, `Location`, `Days`, `Time`) VALUES
	(1045, '121 Smith', 'MWHF', '15:30:00'),
	(1050, '123 Smith', 'MTWH', '11:30:00'),
	(1061, '234 Smith', 'TWHF', '13:15:00'),
	(2042, '225 Adams', 'MTWH', '11:00:00'),
	(4022, '23 Williams', 'MWF', '09:00:00'),
	(4061, '22 Williams', 'TH', '11:15:00'),
	(5001, 'dfsdfdf', 'MWH', '12:00:00');

-- Dumping structure for table school.person
CREATE TABLE IF NOT EXISTS `person` (
  `PersonID` int(11) NOT NULL AUTO_INCREMENT,
  `Lastname` varchar(50) NOT NULL,
  `Firstname` varchar(50) NOT NULL,
  `HireDate` datetime DEFAULT NULL,
  `EnrollmentDate` datetime DEFAULT NULL,
  PRIMARY KEY (`PersonID`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table school.person: ~34 rows (approximately)
INSERT INTO `person` (`PersonID`, `Lastname`, `Firstname`, `HireDate`, `EnrollmentDate`) VALUES
	(1, 'Abercrombie', 'Kim', '1995-03-11 00:00:00', NULL),
	(2, 'Barzdukas', 'Gytis', NULL, '2005-09-01 00:00:00'),
	(3, 'Justice', 'Peggy', NULL, '2001-09-01 00:00:00'),
	(4, 'Fakhouri', 'Fadi', '2002-08-06 00:00:00', NULL),
	(5, 'Harui', 'Roger', '1998-07-01 00:00:00', NULL),
	(6, 'Li', 'Yan', NULL, '2002-09-01 00:00:00'),
	(7, 'Norman', 'Laura', NULL, '2003-09-01 00:00:00'),
	(8, 'Olivotto', 'Nino', NULL, '2005-09-01 00:00:00'),
	(9, 'Tang', 'Wayne', NULL, '2005-09-01 00:00:00'),
	(10, 'Alonso', 'Meredith', NULL, '2002-09-01 00:00:00'),
	(11, 'Lopez', 'Sophia', NULL, '2004-09-01 00:00:00'),
	(12, 'Browning', 'Meredith', NULL, '2000-09-01 00:00:00'),
	(13, 'Anand', 'Arturo', NULL, '2003-09-01 00:00:00'),
	(14, 'Walker', 'Alexandra', NULL, '2000-09-01 00:00:00'),
	(15, 'Powell', 'Carson', NULL, '2004-09-01 00:00:00'),
	(16, 'Jai', 'Damien', NULL, '2001-09-01 00:00:00'),
	(17, 'Carlson', 'Robyn', NULL, '2005-09-01 00:00:00'),
	(18, 'Zheng', 'Roger', '2004-02-12 00:00:00', NULL),
	(19, 'Bryant', 'Carson', NULL, '2001-09-01 00:00:00'),
	(20, 'Suarez', 'Robyn', NULL, '2004-09-01 00:00:00'),
	(21, 'Holt', 'Roger', NULL, '2004-09-01 00:00:00'),
	(22, 'Alexander', 'Carson', NULL, '2005-09-01 00:00:00'),
	(23, 'Morgan', 'Isaiah', NULL, '2001-09-01 00:00:00'),
	(24, 'Martin', 'Randall', NULL, '2005-09-01 00:00:00'),
	(25, 'Kapoor', 'Candace', '2001-01-15 00:00:00', NULL),
	(26, 'Rogers', 'Cody', NULL, '2002-09-01 00:00:00'),
	(27, 'Serrano', 'Stacy', '1999-06-01 00:00:00', NULL),
	(28, 'White', 'Anthony', NULL, '2001-09-01 00:00:00'),
	(29, 'Griffin', 'Rachel', NULL, '2004-09-01 00:00:00'),
	(30, 'Shan', 'Alicia', NULL, '2003-09-01 00:00:00'),
	(31, 'Stewart', 'Jasmine', '1997-10-12 00:00:00', NULL),
	(32, 'Xu', 'Kristen', '2001-07-23 00:00:00', NULL),
	(33, 'Gao', 'Erica', NULL, '2003-01-30 00:00:00'),
	(34, 'Van Houten', 'Roger', '2000-12-07 00:00:00', NULL);

-- Dumping structure for table school.studentgrade
CREATE TABLE IF NOT EXISTS `studentgrade` (
  `EnrollmentID` int(11) NOT NULL AUTO_INCREMENT,
  `CourseID` int(11) NOT NULL,
  `StudentID` int(11) NOT NULL,
  `Grade` decimal(3,2) DEFAULT NULL,
  PRIMARY KEY (`EnrollmentID`),
  KEY `fk_studentgrade_person` (`StudentID`),
  KEY `fk_studentgrade_course` (`CourseID`),
  CONSTRAINT `fk_studentgrade_course` FOREIGN KEY (`CourseID`) REFERENCES `course` (`CourseID`),
  CONSTRAINT `fk_studentgrade_person` FOREIGN KEY (`StudentID`) REFERENCES `person` (`PersonID`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table school.studentgrade: ~30 rows (approximately)
INSERT INTO `studentgrade` (`EnrollmentID`, `CourseID`, `StudentID`, `Grade`) VALUES
	(1, 2021, 2, NULL),
	(2, 2030, 2, 3.00),
	(3, 2021, 3, 2.00),
	(4, 2030, 3, 0.00),
	(5, 2021, 6, 0.00),
	(6, 2042, 6, 3.00),
	(7, 2021, 7, 1.00),
	(8, 2042, 7, 4.00),
	(9, 2021, 8, 3.00),
	(10, 2042, 8, 3.00),
	(11, 4041, 9, 3.50),
	(12, 4041, 10, 2.00),
	(13, 4041, 11, 2.50),
	(14, 4041, 12, 3.00),
	(15, 4061, 12, 4.00),
	(16, 4022, 14, 3.00),
	(17, 4022, 13, 4.00),
	(18, 4061, 13, NULL),
	(19, 4041, 14, 3.00),
	(20, 4022, 15, 2.50),
	(21, 4022, 16, 2.00),
	(22, 4022, 17, 1.00),
	(23, 4022, 19, 3.50),
	(24, 4061, 20, NULL),
	(25, 4061, 21, 2.00),
	(26, 4022, 22, 3.00),
	(27, 4041, 22, 3.50),
	(29, 4022, 23, 3.00),
	(38, 1061, 29, 1.00),
	(40, 1061, 30, 2.00);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
