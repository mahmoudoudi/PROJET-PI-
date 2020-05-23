-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 06, 2020 at 12:03 AM
-- Server version: 5.7.24
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hooks`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('admin', 'admin'),
('a', 'a');

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
CREATE TABLE IF NOT EXISTS `cart` (
  `IdCart` int(11) NOT NULL AUTO_INCREMENT,
  `IdClient` int(11) DEFAULT NULL,
  `IdProduct` int(11) DEFAULT NULL,
  `Quantity` int(11) NOT NULL,
  `EmailClient` varchar(100) NOT NULL,
  `price` float NOT NULL,
  `Total` float NOT NULL,
  PRIMARY KEY (`IdCart`),
  KEY `ccc` (`IdClient`),
  KEY `eeee` (`IdProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `IdCategory` int(11) NOT NULL AUTO_INCREMENT,
  `NameCategory` varchar(9999) NOT NULL,
  `DescriptionCategory` varchar(9999) NOT NULL,
  PRIMARY KEY (`IdCategory`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`IdCategory`, `NameCategory`, `DescriptionCategory`) VALUES
(6, 'Category11', 'des11'),
(7, 'Category2', 'des');

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `IdClient` int(11) NOT NULL AUTO_INCREMENT,
  `EmailClient` varchar(100) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `LastName` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Adress` varchar(100) NOT NULL,
  PRIMARY KEY (`IdClient`),
  UNIQUE KEY `EmailClient` (`EmailClient`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`IdClient`, `EmailClient`, `Name`, `LastName`, `Password`, `Adress`) VALUES
(34, 'dfgh', 'dfghn', 'khadhraoui', 'dfvgbh', 'dfgh'),
(37, 't', 'u', 'e', 'i', 'gr'),
(39, 'rzrz', 'rg', 'rf ', 'f', 'fe ');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
  `IdComment` int(11) NOT NULL AUTO_INCREMENT,
  `IdClient` int(11) DEFAULT NULL,
  `IdNews` int(11) DEFAULT NULL,
  `TextC` varchar(9999) NOT NULL,
  `DateC` date NOT NULL,
  PRIMARY KEY (`IdComment`),
  KEY `qq` (`IdClient`),
  KEY `dd` (`IdNews`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
CREATE TABLE IF NOT EXISTS `contact` (
  `IdContact` int(11) NOT NULL AUTO_INCREMENT,
  `IdClient` int(11) DEFAULT NULL,
  `TextContact` varchar(9999) NOT NULL,
  PRIMARY KEY (`IdContact`),
  KEY `zq` (`IdClient`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `contact`
--

INSERT INTO `contact` (`IdContact`, `IdClient`, `TextContact`) VALUES
(1, 34, 'fg'),
(2, 37, 'hello'),
(3, 34, 'fjgk'),
(4, 37, 'hello');

-- --------------------------------------------------------

--
-- Table structure for table `delivery`
--

DROP TABLE IF EXISTS `delivery`;
CREATE TABLE IF NOT EXISTS `delivery` (
  `IdDelivery` int(11) NOT NULL AUTO_INCREMENT,
  `IdOrder` int(11) DEFAULT NULL,
  `Destination` varchar(100) NOT NULL,
  `CityD` varchar(9999) NOT NULL,
  `CostDelivery` float NOT NULL,
  `EtatD` int(11) NOT NULL,
  PRIMARY KEY (`IdDelivery`),
  KEY `zz` (`IdOrder`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `delivery`
--

INSERT INTO `delivery` (`IdDelivery`, `IdOrder`, `Destination`, `CityD`, `CostDelivery`, `EtatD`) VALUES
(1, 4, 'jdaida', 'Tunis', 10000, 1),
(26, 6, 'jj', 'Manouba', 117.5, 1),
(27, 6, 'hjkkk', 'Medenine', 187, 1);

-- --------------------------------------------------------

--
-- Table structure for table `deliveryman`
--

DROP TABLE IF EXISTS `deliveryman`;
CREATE TABLE IF NOT EXISTS `deliveryman` (
  `IdDeliveryman` int(11) NOT NULL AUTO_INCREMENT,
  `NameD` varchar(100) NOT NULL,
  `LastNameD` varchar(100) NOT NULL,
  `AddressD` varchar(100) NOT NULL,
  `EmailDeliveryman` varchar(100) NOT NULL,
  `PasswordD` varchar(6666) NOT NULL,
  `PhoneNumberD` int(11) NOT NULL,
  `Available` varchar(100) NOT NULL,
  `Score` float NOT NULL,
  `NumberScore` float NOT NULL,
  PRIMARY KEY (`IdDeliveryman`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `deliveryman`
--

INSERT INTO `deliveryman` (`IdDeliveryman`, `NameD`, `LastNameD`, `AddressD`, `EmailDeliveryman`, `PasswordD`, `PhoneNumberD`, `Available`, `Score`, `NumberScore`) VALUES
(25, 'defrgthy', 'erfgthyzazaz', 'rth', 'rt', '852', 852, 'null', 0, 0),
(27, 'ghh', 'hhhh', 'hhhh', 'hhhh@gmail.com', '55555', 55555, 'Yes', 0, 0),
(28, 'ghh', 'hhhh', 'hhhh', 'hhhh@gmail.com', '55555', 55555, 'Yes', 0, 0),
(29, 'jhgf', 'fgd', 'fdfd', 'rhgtf@gmail.com', '555', 555, 'Yes', 0, 0),
(30, 'jhgf', 'fgd', 'fdfd', 'rhgtf@gmail.com', '555', 555, 'Yes', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `eventclient`
--

DROP TABLE IF EXISTS `eventclient`;
CREATE TABLE IF NOT EXISTS `eventclient` (
  `idclient` int(11) NOT NULL,
  `idEvent` int(11) NOT NULL,
  PRIMARY KEY (`idclient`,`idEvent`),
  KEY `fkevent` (`idEvent`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `eventclient`
--

INSERT INTO `eventclient` (`idclient`, `idEvent`) VALUES
(34, 1),
(34, 7);

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
CREATE TABLE IF NOT EXISTS `events` (
  `IdEvent` int(11) NOT NULL AUTO_INCREMENT,
  `NameEvent` varchar(100) NOT NULL,
  `AddressEvent` varchar(100) NOT NULL,
  `Type` varchar(100) NOT NULL,
  `PriceEvent` float NOT NULL,
  `NbrPlace` int(11) NOT NULL,
  `DescriptionEvent` varchar(100) NOT NULL,
  `Image` varchar(100) NOT NULL,
  `dateD` date NOT NULL,
  `dateF` date NOT NULL,
  PRIMARY KEY (`IdEvent`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`IdEvent`, `NameEvent`, `AddressEvent`, `Type`, `PriceEvent`, `NbrPlace`, `DescriptionEvent`, `Image`, `dateD`, `dateF`) VALUES
(1, 'azaz', 'ariana', 'peche', 5, 2, 'azazaz', 'night', '2020-02-13', '2020-02-20'),
(7, 'Oussema', 'jdaida', 'chasse', 125, 221, 'sdfsqdf', 'night', '2020-02-08', '2020-02-29'),
(9, 'sanglier', 'ARIANA', 'chasse', 522, 111, 'zzzzz', 'Nature', '2020-02-14', '2020-03-06');

-- --------------------------------------------------------

--
-- Table structure for table `fidelitycard`
--

DROP TABLE IF EXISTS `fidelitycard`;
CREATE TABLE IF NOT EXISTS `fidelitycard` (
  `IdFidelityCard` int(11) NOT NULL AUTO_INCREMENT,
  `Points` int(11) NOT NULL,
  `IdClient` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdFidelityCard`),
  KEY `oo` (`IdClient`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fidelitycard`
--

INSERT INTO `fidelitycard` (`IdFidelityCard`, `Points`, `IdClient`) VALUES
(12, 0, 37),
(13, 0, 37),
(14, 0, 37);

-- --------------------------------------------------------

--
-- Table structure for table `fos_user`
--

DROP TABLE IF EXISTS `fos_user`;
CREATE TABLE IF NOT EXISTS `fos_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_957A647992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_957A6479A0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_957A6479C05FB297` (`confirmation_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `guide`
--

DROP TABLE IF EXISTS `guide`;
CREATE TABLE IF NOT EXISTS `guide` (
  `IdGuide` int(11) NOT NULL AUTO_INCREMENT,
  `NameG` varchar(100) NOT NULL,
  `LastNameG` varchar(100) NOT NULL,
  `EmailG` varchar(100) NOT NULL,
  `PhoneG` int(30) NOT NULL,
  `EventAccept` int(11) NOT NULL,
  PRIMARY KEY (`IdGuide`),
  UNIQUE KEY `EmailG` (`EmailG`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
CREATE TABLE IF NOT EXISTS `news` (
  `IdNews` int(11) NOT NULL AUTO_INCREMENT,
  `Subject` varchar(200) NOT NULL,
  `Text` varchar(9999) NOT NULL,
  `Date` date NOT NULL,
  `ImageNews` varchar(100) NOT NULL,
  PRIMARY KEY (`IdNews`),
  UNIQUE KEY `Subject` (`Subject`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `news`
--

INSERT INTO `news` (`IdNews`, `Subject`, `Text`, `Date`, `ImageNews`) VALUES
(16, 'rzg', 'uyo', '2020-02-06', 'night'),
(20, 'dry', 'gfjk', '2020-02-06', 'Nature');

-- --------------------------------------------------------

--
-- Table structure for table `orderp`
--

DROP TABLE IF EXISTS `orderp`;
CREATE TABLE IF NOT EXISTS `orderp` (
  `IdOrder` int(11) NOT NULL AUTO_INCREMENT,
  `IdClient` int(11) DEFAULT NULL,
  `EmailClient` varchar(100) NOT NULL,
  `PhoneNumber` int(30) NOT NULL,
  `PaymentMethod` varchar(100) NOT NULL,
  `TotalO` float NOT NULL,
  PRIMARY KEY (`IdOrder`),
  KEY `aaa` (`IdClient`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orderp`
--

INSERT INTO `orderp` (`IdOrder`, `IdClient`, `EmailClient`, `PhoneNumber`, `PaymentMethod`, `TotalO`) VALUES
(4, 37, 'azaz', 8, 'carte', 18),
(5, 37, 'med', 555, 'carte', 36),
(6, 37, 'fghjk', 45676, 'carte', 117),
(7, 37, 'fghjk', 5475, 'carte', 9),
(8, 37, 'gf', 456, 'cheque', 9),
(9, 37, 'dgs', 54, 'cheque', 9),
(10, 37, 'sd', 55, 'carte', 72);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `IdProduct` int(11) NOT NULL AUTO_INCREMENT,
  `NameCategory` varchar(9999) NOT NULL,
  `NameProduct` varchar(9999) NOT NULL,
  `DescriptionProduct` varchar(9999) NOT NULL,
  `QuantityProduct` int(11) NOT NULL,
  `PriceProduct` float NOT NULL,
  `Promotion` float NOT NULL,
  `Imageproduct` varchar(9999) NOT NULL,
  PRIMARY KEY (`IdProduct`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`IdProduct`, `NameCategory`, `NameProduct`, `DescriptionProduct`, `QuantityProduct`, `PriceProduct`, `Promotion`, `Imageproduct`) VALUES
(3, 'az', 'az', 'az', 938, 9, 50, 'flech.jpg'),
(11, 'Category1', 'Product1', 'desccc', 50, 690.5, 0, 'fus1.jpg'),
(12, 'Category1', 'a', 'a', 90, 5, 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `ratecity`
--

DROP TABLE IF EXISTS `ratecity`;
CREATE TABLE IF NOT EXISTS `ratecity` (
  `City` varchar(30) NOT NULL,
  `Rate` float NOT NULL,
  PRIMARY KEY (`City`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ratecity`
--

INSERT INTO `ratecity` (`City`, `Rate`) VALUES
('Zaghouan', 50),
('Tunis', 5),
('Manouba', 0.5),
('Ariana', 90),
('Bizerte', 60),
('Beja', 30),
('Jendouba', 45),
('Siliana', 40),
('Kef', 40),
('Kasserine', 70),
('Sidi Bou Zid', 55),
('Medenine', 70),
('Douz', 60),
('Touzeur', 60),
('Kebili', 60),
('Hammamet', 7),
('Nabeul', 30),
('Sfax', 60),
('Gabes', 57),
('Gafssa', 20),
('Ben Arous', 25),
('Mahdia', 45),
('Monastir', 47),
('Tataouine', 58);

-- --------------------------------------------------------

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;
CREATE TABLE IF NOT EXISTS `wishlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `IdClient` int(11) DEFAULT NULL,
  `IdProduct` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ww` (`IdClient`),
  KEY `zzzz` (`IdProduct`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `wishlist`
--

INSERT INTO `wishlist` (`id`, `IdClient`, `IdProduct`) VALUES
(1, 37, 3),
(2, 37, 11),
(3, NULL, NULL),
(9, NULL, NULL),
(10, NULL, NULL),
(11, NULL, NULL),
(12, NULL, NULL),
(13, NULL, NULL),
(14, NULL, NULL),
(15, NULL, NULL),
(16, NULL, NULL),
(17, NULL, NULL);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `ccc` FOREIGN KEY (`IdClient`) REFERENCES `client` (`IdClient`),
  ADD CONSTRAINT `eeee` FOREIGN KEY (`IdProduct`) REFERENCES `product` (`IdProduct`);

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `dd` FOREIGN KEY (`IdNews`) REFERENCES `news` (`IdNews`),
  ADD CONSTRAINT `qq` FOREIGN KEY (`IdClient`) REFERENCES `client` (`IdClient`);

--
-- Constraints for table `contact`
--
ALTER TABLE `contact`
  ADD CONSTRAINT `zq` FOREIGN KEY (`IdClient`) REFERENCES `client` (`IdClient`);

--
-- Constraints for table `delivery`
--
ALTER TABLE `delivery`
  ADD CONSTRAINT `zz` FOREIGN KEY (`IdOrder`) REFERENCES `orderp` (`IdOrder`);

--
-- Constraints for table `fidelitycard`
--
ALTER TABLE `fidelitycard`
  ADD CONSTRAINT `FK_9390F1BF5D23CE99` FOREIGN KEY (`IdClient`) REFERENCES `client` (`IdClient`);

--
-- Constraints for table `orderp`
--
ALTER TABLE `orderp`
  ADD CONSTRAINT `aaa` FOREIGN KEY (`IdClient`) REFERENCES `client` (`IdClient`);

--
-- Constraints for table `wishlist`
--
ALTER TABLE `wishlist`
  ADD CONSTRAINT `ww` FOREIGN KEY (`IdClient`) REFERENCES `client` (`IdClient`),
  ADD CONSTRAINT `zzzz` FOREIGN KEY (`IdProduct`) REFERENCES `product` (`IdProduct`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
