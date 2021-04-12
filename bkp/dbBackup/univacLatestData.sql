-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 12, 2021 at 02:56 AM
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
  `assistanceCreatedAt` timestamp(6) NOT NULL DEFAULT current_timestamp(6),
  `assistanceUpdatedAt` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  `assistanceStatus` tinyint(1) NOT NULL DEFAULT 1,
  `billboardId` int(11) NOT NULL,
  `userId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `assistance`
--

INSERT INTO `assistance` (`assistanceId`, `assistanceCreatedAt`, `assistanceUpdatedAt`, `assistanceStatus`, `billboardId`, `userId`) VALUES
(1, '0000-00-00 00:00:00.000000', '0000-00-00 00:00:00.000000', 1, 1, 1),
(2, '0000-00-00 00:00:00.000000', '0000-00-00 00:00:00.000000', 1, 1, 2);

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
  `movieGenre` varchar(45) NOT NULL,
  `movieDuration` int(11) NOT NULL,
  `movieStatus` tinyint(1) NOT NULL DEFAULT 1,
  `movieCreatedAt` timestamp(6) NOT NULL DEFAULT current_timestamp(6),
  `movieUpdatedAt` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`movieId`, `movieName`, `movieDirector`, `movieProducer`, `movieClassification`, `movieGenre`, `movieDuration`, `movieStatus`, `movieCreatedAt`, `movieUpdatedAt`) VALUES
(1, 'Lilo & Stitch', 'Leo Di Caprio', 'Leonardo Di Caprio', 'B15', 'Adventure', 125, 1, '2021-03-24 07:50:59.926375', '2021-04-12 00:55:47.618060'),
(2, 'Juan ', 'Pecas', 'Joel', 'B15', 'Terror', 23, 1, '2021-04-10 22:47:19.895037', '2021-04-12 00:55:51.637169'),
(3, 'Pepe pecas', 'yaison2', 'prueba', 'B15', 'Action', 23, 1, '2021-04-10 22:46:29.883720', '2021-04-12 00:55:55.763768'),
(4, 'Juan Pepe', 'Pai', 'Pao', 'D', 'Comedy', 123, 0, '2021-04-10 22:46:11.234432', '2021-04-12 00:55:59.092267');

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
(1, 'Guadalajara', 1, '2021-03-25 12:46:06', '2021-03-25 12:46:06', 1),
(2, 'Zapopan', 1, '2021-03-25 12:50:36', '2021-03-25 12:50:36', 1),
(3, 'Tlaquepaque', 1, '2021-03-25 12:46:06', '2021-03-25 12:46:06', 1),
(4, 'Tonala', 1, '2021-03-25 12:46:06', '2021-03-25 12:46:06', 1),
(5, 'Zapotlanejo', 1, '2021-03-25 12:46:06', '2021-03-25 12:46:06', 1),
(6, 'Tlajomulco', 1, '2021-03-25 12:46:06', '2021-03-25 12:46:06', 1),
(7, 'Ayotlan', 1, '2021-03-25 12:46:06', '2021-03-25 12:46:06', 1),
(8, 'Tequila', 1, '2021-03-25 12:46:06', '2021-03-25 12:46:06', 1),
(9, 'Ocotlan', 1, '2021-03-25 12:46:06', '2021-03-25 12:46:06', 1),
(10, 'Puerto Vallarta', 1, '2021-03-25 12:46:06', '2021-03-25 12:46:06', 1),
(11, 'Guadalupe', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 2),
(12, 'Abasolo', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 2),
(13, 'Apodaca', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 2),
(14, 'Cienega de Flores', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 2),
(15, 'General Zaragoza', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 2),
(16, 'Iturbide', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 2),
(17, 'Juarez', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 2),
(18, 'Monterrey', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 2),
(19, 'Salinas Victoria', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 2),
(20, 'General Zuazua', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 2),
(21, 'Cuautitlan Izcalli', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 3),
(22, 'Chalco', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 3),
(23, 'Aculco', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 3),
(24, 'Atizapan', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 3),
(25, 'Chapultepec', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 3),
(26, 'Ecatepec de Morelos', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 3),
(27, 'Naucalpan de Juarez', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 3),
(28, 'Morelos', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 3),
(29, 'Texcoco', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 3),
(30, 'Toluca', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 3),
(31, 'Ignacio Allende', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 4),
(32, 'Allende', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 4),
(33, 'Valle de Zaragoza', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 4),
(34, 'Rosario', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 4),
(35, 'Nonoava', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 4),
(36, 'Matamoros', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 4),
(37, 'Guadalupe y Calvo', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 4),
(38, 'Coronado', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 4),
(39, 'Delicias', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 4),
(40, 'Galeana', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 4),
(41, 'Guasave', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 5),
(42, 'Navolato', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 5),
(43, 'Cosala', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 5),
(44, 'Angostura', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 5),
(45, 'Mocorito', 1, '2021-03-25 12:46:52', '2021-03-25 12:46:52', 5);

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
(1, 1, 1, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(2, 2, 1, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(3, 3, 1, '2021-03-25 12:40:00', '2021-03-25 12:40:00'),
(4, 4, 1, '2021-03-25 12:40:00', '2021-03-25 12:40:00'),
(5, 5, 1, '2021-03-25 12:40:00', '2021-03-25 12:40:00'),
(6, 6, 1, '2021-03-25 12:40:00', '2021-03-25 12:40:00'),
(7, 7, 1, '2021-03-25 12:40:00', '2021-03-25 12:40:00'),
(8, 8, 1, '2021-03-25 12:40:00', '2021-03-25 12:40:00'),
(9, 9, 1, '2021-03-25 12:40:00', '2021-03-25 12:40:00'),
(10, 10, 1, '2021-03-25 12:40:00', '2021-03-25 12:40:00');

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

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`scheduleId`, `scheduleStart`, `scheduleEnd`, `scheduleStatus`, `scheduleCreatedAt`, `scheduleUpdatedAt`, `roomId`) VALUES
(1, '2021-04-08 01:55:54', '2021-04-09 01:55:54', 1, '2021-04-08 06:56:15', '2021-04-08 06:56:15', 1);

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
(1, 'Paola', '3', 0, 0, '2021-03-24 13:30:00.314843', '2021-03-24 13:30:00.314843'),
(2, 'armando', '2', 1, 0, '2021-03-24 13:30:38.854061', '2021-03-24 13:30:38.854061'),
(3, 'joel flores', 'maria', 1, 0, '2021-03-24 13:30:53.446548', '2021-03-24 13:30:53.446548'),
(4, 'julio', 'pass', 1, 0, '2021-03-24 13:31:05.761260', '2021-03-24 13:31:05.761260'),
(5, 'Paoluqui', '123', 0, 1, '2021-04-10 22:44:47.486598', '2021-04-10 22:44:47.486598');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assistance`
--
ALTER TABLE `assistance`
  ADD PRIMARY KEY (`assistanceId`),
  ADD KEY `billboardId` (`billboardId`),
  ADD KEY `userId` (`userId`);

--
-- Indexes for table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`movieId`);

--
-- Indexes for table `municipality`
--
ALTER TABLE `municipality`
  ADD PRIMARY KEY (`municipalityId`),
  ADD KEY `stateId` (`stateId`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`roomId`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`scheduleId`),
  ADD KEY `roomId` (`roomId`);

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
  MODIFY `assistanceId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `movie`
--
ALTER TABLE `movie`
  MODIFY `movieId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `municipality`
--
ALTER TABLE `municipality`
  MODIFY `municipalityId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `roomId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `schedule`
--
ALTER TABLE `schedule`
  MODIFY `scheduleId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `state`
--
ALTER TABLE `state`
  MODIFY `stateId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assistance`
--
ALTER TABLE `assistance`
  ADD CONSTRAINT `assistance_ibfk_1` FOREIGN KEY (`billboardId`) REFERENCES `billboard` (`billboardId`),
  ADD CONSTRAINT `assistance_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`);

--
-- Constraints for table `municipality`
--
ALTER TABLE `municipality`
  ADD CONSTRAINT `municipality_ibfk_1` FOREIGN KEY (`stateId`) REFERENCES `state` (`stateId`);

--
-- Constraints for table `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`roomId`) REFERENCES `room` (`roomId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
