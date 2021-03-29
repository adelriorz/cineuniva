-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 26, 2021 at 05:20 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `univac`
--

-- --------------------------------------------------------

--
-- Table structure for table `assistance`
--

CREATE TABLE `assistance` (
  `assistanceId` int(11) NOT NULL,
  `assistanceStatus` tinyint(1) NOT NULL DEFAULT 1,
  `assistanceCreatedAt` timestamp(6) NOT NULL DEFAULT current_timestamp(6),
  `assistanceUpdatedAt` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  `movie_movieId` int(11) NOT NULL,
  `schedule_scheduleId` int(11) NOT NULL,
  `schedule_roomId` int(11) NOT NULL,
  `municipality_municipalityId` int(11) NOT NULL,
  `municipality_stateId` int(11) NOT NULL,
  `user_userId` int(11) NOT NULL,
  `room_roomId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE `movie` (
  `movieId` int(11) NOT NULL,
  `movieName` varchar(45) NOT NULL,
  `movieDirector` varchar(45) NOT NULL,
  `movieProducer` varchar(45) NOT NULL,
  `movieClassification` varchar(45) NOT NULL,
  `movieDuration` int(11) NOT NULL,
  `movieStatus` tinyint(1) NOT NULL DEFAULT 1,
  `movieCreatedAt` timestamp(6) NOT NULL DEFAULT current_timestamp(6),
  `movieUpdatedAt` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`movieId`, `movieName`, `movieDirector`, `movieProducer`, `movieClassification`, `movieDuration`, `movieStatus`, `movieCreatedAt`, `movieUpdatedAt`) VALUES
(2, 'Lilo & Stitch', 'Leo Di Caprio', 'Leonardo Di Caprio', 'B15', 125, 1, '2021-03-24 07:50:59.926375', '2021-03-25 05:53:02.823745'),
(8, 'Juan ', 'Pecas', 'Joel', 'B15', 23, 0, '2021-03-26 01:20:21.438668', '2021-03-26 01:20:21.438668'),
(14, 'Pepe pecas', 'yeison', 'prueba', 'B15', 23, 0, '2021-03-26 03:59:47.213610', '2021-03-26 04:00:32.595154');

-- --------------------------------------------------------

--
-- Table structure for table `municipality`
--

CREATE TABLE `municipality` (
  `municipalityId` int(11) NOT NULL,
  `municipalityName` varchar(45) NOT NULL,
  `municipalityStatus` tinyint(1) NOT NULL DEFAULT 1,
  `municipalityCreatedAt` timestamp NOT NULL DEFAULT current_timestamp(),
  `municipalityUpdatedAt` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `stateId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `municipality`
--

INSERT INTO `municipality` (`municipalityId`, `municipalityName`, `municipalityStatus`, `municipalityCreatedAt`, `municipalityUpdatedAt`, `stateId`) VALUES
(2, 'Guadalajara', 1, '2021-03-25 06:46:06', '2021-03-25 06:46:06', 1),
(3, 'Monterrey', 1, '2021-03-25 06:46:52', '2021-03-25 06:46:52', 2),
(4, 'Zapopan', 1, '2021-03-25 06:50:36', '2021-03-25 06:50:36', 1);

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `roomId` int(11) NOT NULL,
  `roomNumber` int(11) NOT NULL,
  `roomStatus` tinyint(1) NOT NULL DEFAULT 1,
  `roomCreatedAt` timestamp NOT NULL DEFAULT current_timestamp(),
  `roomUpdatedAt` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`roomId`, `roomNumber`, `roomStatus`, `roomCreatedAt`, `roomUpdatedAt`) VALUES
(2, 1, 1, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(4, 2, 1, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(5, 4, 1, '2021-03-25 06:40:00', '2021-03-25 06:40:00');

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `scheduleId` int(11) NOT NULL,
  `scheduleStart` datetime NOT NULL,
  `scheduleEnd` datetime NOT NULL,
  `scheduleStatus` tinyint(1) NOT NULL DEFAULT 1,
  `scheduleCreatedAt` timestamp NOT NULL DEFAULT current_timestamp(),
  `scheduleUpdatedAt` timestamp NOT NULL DEFAULT current_timestamp(),
  `roomId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `state`
--

CREATE TABLE `state` (
  `stateId` int(11) NOT NULL,
  `stateName` varchar(45) NOT NULL,
  `stateStatus` tinyint(1) NOT NULL DEFAULT 1,
  `stateCreatedAt` timestamp NOT NULL DEFAULT current_timestamp(),
  `stateUpdatedAt` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `state`
--

INSERT INTO `state` (`stateId`, `stateName`, `stateStatus`, `stateCreatedAt`, `stateUpdatedAt`) VALUES
(1, 'Jalisco', 1, '2021-03-25 06:44:01', '2021-03-25 06:44:01'),
(2, 'Nuevo Leon', 1, '2021-03-25 06:44:01', '2021-03-25 06:47:13'),
(3, 'Estado de Mexico', 1, '2021-03-25 06:44:24', '2021-03-25 06:49:48'),
(4, 'Chihuahua', 1, '2021-03-25 06:48:55', '2021-03-25 06:48:55'),
(5, 'Sinaloa', 1, '2021-03-25 06:49:17', '2021-03-25 06:49:17');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userId` int(11) NOT NULL,
  `userName` varchar(45) NOT NULL,
  `userPassword` varchar(45) NOT NULL,
  `userType` tinyint(1) NOT NULL DEFAULT 0,
  `userStatus` tinyint(1) NOT NULL DEFAULT 1,
  `userCreatedAt` timestamp(6) NOT NULL DEFAULT current_timestamp(6),
  `userUpdatedAt` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `userName`, `userPassword`, `userType`, `userStatus`, `userCreatedAt`, `userUpdatedAt`) VALUES
(1, 'Paola', '3', 0, 0, '2021-03-24 07:30:00.314843', '2021-03-24 07:30:00.314843'),
(2, 'armando', '2', 1, 0, '2021-03-24 07:30:38.854061', '2021-03-24 07:30:38.854061'),
(3, 'joel flores', 'maria', 1, 0, '2021-03-24 07:30:53.446548', '2021-03-24 07:30:53.446548'),
(4, 'julio', 'pass', 1, 0, '2021-03-24 07:31:05.761260', '2021-03-24 07:31:05.761260');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assistance`
--
ALTER TABLE `assistance`
  ADD PRIMARY KEY (`assistanceId`,`movie_movieId`,`schedule_scheduleId`,`schedule_roomId`,`municipality_municipalityId`,`municipality_stateId`,`user_userId`,`room_roomId`),
  ADD KEY `fk_assistance_movie1_idx` (`movie_movieId`),
  ADD KEY `fk_assistance_schedule1_idx` (`schedule_scheduleId`,`schedule_roomId`),
  ADD KEY `fk_assistance_municipality1_idx` (`municipality_municipalityId`,`municipality_stateId`),
  ADD KEY `fk_assistance_user1_idx` (`user_userId`),
  ADD KEY `fk_assistance_room1_idx` (`room_roomId`);

--
-- Indexes for table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`movieId`);

--
-- Indexes for table `municipality`
--
ALTER TABLE `municipality`
  ADD PRIMARY KEY (`municipalityId`,`stateId`),
  ADD KEY `fk_municipality_state_idx` (`stateId`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`roomId`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`scheduleId`,`roomId`);

--
-- Indexes for table `state`
--
ALTER TABLE `state`
  ADD PRIMARY KEY (`stateId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `assistance`
--
ALTER TABLE `assistance`
  MODIFY `assistanceId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `movie`
--
ALTER TABLE `movie`
  MODIFY `movieId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `municipality`
--
ALTER TABLE `municipality`
  MODIFY `municipalityId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `roomId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `schedule`
--
ALTER TABLE `schedule`
  MODIFY `scheduleId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `state`
--
ALTER TABLE `state`
  MODIFY `stateId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assistance`
--
ALTER TABLE `assistance`
  ADD CONSTRAINT `fk_assistance_movie1` FOREIGN KEY (`movie_movieId`) REFERENCES `movie` (`movieId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_assistance_municipality1` FOREIGN KEY (`municipality_municipalityId`,`municipality_stateId`) REFERENCES `municipality` (`municipalityId`, `stateId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_assistance_room1` FOREIGN KEY (`room_roomId`) REFERENCES `room` (`roomId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_assistance_schedule1` FOREIGN KEY (`schedule_scheduleId`,`schedule_roomId`) REFERENCES `schedule` (`scheduleId`, `roomId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_assistance_user1` FOREIGN KEY (`user_userId`) REFERENCES `user` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `municipality`
--
ALTER TABLE `municipality`
  ADD CONSTRAINT `fk_municipality_state` FOREIGN KEY (`stateId`) REFERENCES `state` (`stateId`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
