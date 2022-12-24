-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 22, 2021 at 08:49 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cakesie`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertPayment` (IN `payment_name` VARCHAR(30), IN `amount` INT(10))  BEGIN DECLARE id char(5); SET id = getMethod(payment_name); 
IF NOT EXISTS (SELECT*FROM payment WHERE METHOD_ID = id AND TOTAL_AMOUNT = amount) THEN
INSERT INTO payment(METHOD_ID, TOTAL_AMOUNT) VALUES(id, amount); END IF; END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `saveCustom` (IN `uname` VARCHAR(50), IN `cake` VARCHAR(50), IN `width` VARCHAR(5), IN `shape` VARCHAR(5), IN `flavor` VARCHAR(30), IN `layer` VARCHAR(30), IN `note1` VARCHAR(100))  BEGIN 
DECLARE order1 int; 
DECLARE id int; 
SET order1 = getCustomID2(cake, width, shape, flavor, layer, note1, @custom_cake_id); SET id = getCustID(uname); UPDATE cake_order SET CUSTOM_ID = order1 WHERE CUST_ID = id; END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `saveOrder1` (IN `uname` VARCHAR(50))  BEGIN
DECLARE order1 int;
SET order1 = getCustID(uname);
INSERT INTO cake_order(CUST_ID) VALUES (order1);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `saveOrder2` (IN `uname` VARCHAR(50), IN `cake` VARCHAR(5), IN `width` VARCHAR(5), IN `shape` VARCHAR(5), IN `flavor` VARCHAR(5), IN `layer` VARCHAR(5), IN `note1` VARCHAR(100))  BEGIN DECLARE order1 int; DECLARE id int; SET order1 = getCustomID(cake, width, shape, flavor, layer, note1, @custom_cake_id); SET id = getCustID(uname); UPDATE cake_order SET CUSTOM_ID = order1 WHERE CUST_ID = id; END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `saveOrder3` (IN `name` VARCHAR(50), IN `type` VARCHAR(30), IN `payment_name` VARCHAR(30), IN `amount` BIGINT, IN `add1` VARCHAR(100), IN `date1` DATE)  BEGIN
DECLARE typeID char(5);
DECLARE paymentID int;
DECLARE custID int;
SET typeID = getType(type);
SET paymentID = getPayment(payment_name, amount);
SET custID = getCustID(name);
UPDATE cake_order SET ID_PAYMENT = paymentID, TYPE_ID = typeID, ADDRESS = add1, DATE_PICKUP = date1 WHERE CUST_ID = custID;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `savePayment` (IN `name` VARCHAR(50), IN `payment_name` VARCHAR(30), IN `amount` INT(10), IN `add1` VARCHAR(100), IN `date1` DATE)  BEGIN DECLARE paymentID int; DECLARE custID int; SET paymentID = getPayment(payment_name, amount); SET custID = getCustID(name); UPDATE cake_order SET ID_PAYMENT = paymentID, ADDRESS = add1, DATE_PICKUP = date1 WHERE CUST_ID = custID; END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `saveType` (IN `name` VARCHAR(50), IN `type` VARCHAR(30))  BEGIN DECLARE typeID char(5); DECLARE custID int; SET typeID = getType(type); SET custID = getCustID(name); UPDATE cake_order SET TYPE_ID = typeID WHERE CUST_ID = custID; END$$

--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `getCake` (`name` VARCHAR(50)) RETURNS CHAR(5) CHARSET utf8mb4 BEGIN
DECLARE id char(5);
SET id = (SELECT CAKE_ID FROM cake WHERE CAKE_NAME = name);
RETURN id;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `getCustID` (`uname` VARCHAR(50)) RETURNS INT(11) BEGIN
DECLARE id int;
SET id = (SELECT CUST_ID FROM customer WHERE username = uname);
RETURN id;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `getCustomID` (`cake` VARCHAR(5), `width` VARCHAR(5), `shape` VARCHAR(5), `flavor` VARCHAR(5), `layer` VARCHAR(5), `note1` VARCHAR(100), `custom_cake_id` INT) RETURNS INT(11) BEGIN INSERT INTO custom_cake(CAKE_ID, W_ID, S_ID, FLAVOR_ID, LAYER_ID, NOTE) VALUES(cake, width, shape, flavor, layer, note1); SET custom_cake_id = LAST_INSERT_ID(); RETURN custom_cake_id; END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `getCustomID2` (`n_cake` VARCHAR(30), `width` VARCHAR(5), `shape` VARCHAR(5), `n_flavor` VARCHAR(30), `n_layer` VARCHAR(30), `note1` VARCHAR(50), `custom_cake_id` INT) RETURNS INT(11) BEGIN DECLARE idCake char(5); DECLARE idFlavor char(5); DECLARE idLayer char(5); SET idCake = getCake(n_cake); SET idFlavor = getFlavor(n_flavor); SET idLayer = getLayer(n_layer); INSERT INTO custom_cake(CAKE_ID, W_ID, S_ID, FLAVOR_ID, LAYER_ID, NOTE) VALUES (idCake, width, shape, idFlavor, idLayer, note1); SET custom_cake_id = LAST_INSERT_ID(); RETURN custom_cake_id; END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `getFlavor` (`name` VARCHAR(30)) RETURNS CHAR(5) CHARSET utf8mb4 BEGIN
DECLARE id char(5);
SET id = (SELECT FLAVOR_ID FROM flavor WHERE FLAVOR_NAME = name);
RETURN id;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `getLayer` (`name` VARCHAR(30)) RETURNS CHAR(5) CHARSET utf8mb4 BEGIN
DECLARE id char(5);
SET id = (SELECT LAYER_ID FROM layer WHERE L_NAME = name);
RETURN id;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `getMethod` (`payment_name` VARCHAR(30)) RETURNS CHAR(5) CHARSET utf8mb4 BEGIN
DECLARE id char(5);
SET id = (SELECT METHOD_ID FROM payment_method WHERE METHOD_NAME = payment_name);
RETURN id;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `getPayment` (`payment_name` VARCHAR(30), `amount` INT(10)) RETURNS INT(1) BEGIN
DECLARE id int;
DECLARE id2 char(5);
SET id2 = getMethod(payment_name);
SET id = (SELECT ID_PAYMENT FROM payment WHERE METHOD_ID = id2 AND TOTAL_AMOUNT = amount);
RETURN id;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `getType` (`type` VARCHAR(30)) RETURNS CHAR(5) CHARSET utf8mb4 BEGIN
DECLARE id char(5);
SET id = (SELECT TYPE_ID FROM order_type WHERE TYPE_NAME = type);
RETURN id;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `ID_ADMIN` char(6) NOT NULL,
  `ADMIN_NAME` varchar(50) DEFAULT NULL,
  `ADMIN_PASSWORD` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `cake`
--

CREATE TABLE `cake` (
  `CAKE_ID` char(3) NOT NULL,
  `CAKE_NAME` varchar(50) DEFAULT NULL,
  `CAKE_PRICE` bigint(20) DEFAULT NULL,
  `CAKE_STOCK` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cake`
--

INSERT INTO `cake` (`CAKE_ID`, `CAKE_NAME`, `CAKE_PRICE`, `CAKE_STOCK`) VALUES
('C01', 'Peanut Rum Cake', 100000, 5),
('C02', 'Cherry Amarena Cake', 130000, 5),
('C03', 'Caramel Popcorn Cheese Cake', 200000, 3),
('C04', 'Red velvet Cake', 100000, 3),
('C05', 'Smores Cake', 70000, 2),
('C06', 'Salted Caramel Cake', 200000, 4),
('C07', 'Black Forest Cake', 150000, 10),
('C08', 'Tiramisu Cake', 120000, 3),
('C09', 'Opera Cake', 200000, 2),
('C10', 'Strawberry Cheese Cake', 150000, 5),
('C11', 'Kopyor Iced Cake', 130000, 2),
('C12', 'Blueberry Cheese Iced Cake', 170000, 2),
('C13', 'Choco Iced Cake', 200000, 3);

-- --------------------------------------------------------

--
-- Table structure for table `cake_order`
--

CREATE TABLE `cake_order` (
  `ORDER_ID` int(5) UNSIGNED ZEROFILL NOT NULL,
  `CUST_ID` int(4) UNSIGNED ZEROFILL DEFAULT NULL,
  `CUSTOM_ID` int(5) UNSIGNED ZEROFILL DEFAULT NULL,
  `ID_PAYMENT` int(4) UNSIGNED ZEROFILL DEFAULT NULL,
  `TYPE_ID` char(2) DEFAULT NULL,
  `ORDER_DATE` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `ADDRESS` varchar(100) DEFAULT NULL,
  `DATE_PICKUP` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `CUST_ID` int(4) UNSIGNED ZEROFILL NOT NULL,
  `USERNAME` varchar(50) DEFAULT NULL,
  `CUST_PASSWORD` varchar(8) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `TELEPHONE` char(13) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Table structure for table `custom_cake`
--

CREATE TABLE `custom_cake` (
  `CUSTOM_ID` int(5) UNSIGNED ZEROFILL NOT NULL,
  `CAKE_ID` char(6) DEFAULT NULL,
  `W_ID` char(5) DEFAULT NULL,
  `S_ID` char(5) DEFAULT NULL,
  `FLAVOR_ID` char(5) DEFAULT NULL,
  `LAYER_ID` char(5) DEFAULT NULL,
  `NOTE` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `flavor`
--

CREATE TABLE `flavor` (
  `FLAVOR_ID` char(5) NOT NULL,
  `FLAVOR_NAME` varchar(50) DEFAULT NULL,
  `F_QUANTITY` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `flavor`
--

INSERT INTO `flavor` (`FLAVOR_ID`, `FLAVOR_NAME`, `F_QUANTITY`) VALUES
('F01', 'Brownies', 50),
('F02', 'Red Velvet', 50),
('F03', 'Rainbow Cake', 5),
('F04', 'Choco Cookies', 2),
('F05', 'Tiramisu', 2),
('F06', 'Cheese Cake', 2);

-- --------------------------------------------------------

--
-- Table structure for table `layer`
--

CREATE TABLE `layer` (
  `LAYER_ID` char(5) NOT NULL,
  `L_NAME` varchar(50) DEFAULT NULL,
  `L_PRICE` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `layer`
--

INSERT INTO `layer` (`LAYER_ID`, `L_NAME`, `L_PRICE`) VALUES
('L01', 'Butter Cream', 20000),
('L02', 'Fondant', 50000),
('L03', 'Royal Icing', 50000);

-- --------------------------------------------------------

--
-- Table structure for table `order_type`
--

CREATE TABLE `order_type` (
  `TYPE_ID` char(2) NOT NULL,
  `TYPE_NAME` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_type`
--

INSERT INTO `order_type` (`TYPE_ID`, `TYPE_NAME`) VALUES
('T1', 'Delivery'),
('T2', 'Self Pickup');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `ID_PAYMENT` int(4) UNSIGNED ZEROFILL NOT NULL,
  `METHOD_ID` char(3) NOT NULL,
  `TOTAL_AMOUNT` int(10) DEFAULT NULL,
  `DATE_PAYMENT` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Table structure for table `payment_method`
--

CREATE TABLE `payment_method` (
  `METHOD_ID` char(3) NOT NULL,
  `METHOD_NAME` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment_method`
--

INSERT INTO `payment_method` (`METHOD_ID`, `METHOD_NAME`) VALUES
('M1', 'Mobile Banking'),
('M2', 'Debit'),
('M3', 'Credit');

-- --------------------------------------------------------

--
-- Table structure for table `shape`
--

CREATE TABLE `shape` (
  `S_ID` char(5) NOT NULL,
  `SHAPE` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `shape`
--

INSERT INTO `shape` (`S_ID`, `SHAPE`) VALUES
('S01', 'Circle'),
('S02', 'Rectangle');

-- --------------------------------------------------------

--
-- Table structure for table `width`
--

CREATE TABLE `width` (
  `W_ID` char(5) NOT NULL,
  `W_NUM` int(11) DEFAULT NULL,
  `W_PRICE` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `width`
--

INSERT INTO `width` (`W_ID`, `W_NUM`, `W_PRICE`) VALUES
('W01', 13, 35000),
('W02', 18, 50000),
('W03', 21, 75000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`ID_ADMIN`);

--
-- Indexes for table `cake`
--
ALTER TABLE `cake`
  ADD PRIMARY KEY (`CAKE_ID`);

--
-- Indexes for table `cake_order`
--
ALTER TABLE `cake_order`
  ADD PRIMARY KEY (`ORDER_ID`),
  ADD KEY `FK_RELATIONSHIP_1` (`CUST_ID`),
  ADD KEY `FK_RELATIONSHIP_13` (`TYPE_ID`),
  ADD KEY `FK_RELATIONSHIP_14` (`ID_PAYMENT`),
  ADD KEY `FK_RELATIONSHIP_2` (`CUSTOM_ID`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`CUST_ID`);

--
-- Indexes for table `custom_cake`
--
ALTER TABLE `custom_cake`
  ADD PRIMARY KEY (`CUSTOM_ID`),
  ADD KEY `FK_RELATIONSHIP_3` (`CAKE_ID`),
  ADD KEY `FK_RELATIONSHIP_4` (`W_ID`),
  ADD KEY `FK_RELATIONSHIP_5` (`S_ID`),
  ADD KEY `FK_RELATIONSHIP_6` (`FLAVOR_ID`),
  ADD KEY `FK_RELATIONSHIP_7` (`LAYER_ID`);

--
-- Indexes for table `flavor`
--
ALTER TABLE `flavor`
  ADD PRIMARY KEY (`FLAVOR_ID`);

--
-- Indexes for table `layer`
--
ALTER TABLE `layer`
  ADD PRIMARY KEY (`LAYER_ID`);

--
-- Indexes for table `order_type`
--
ALTER TABLE `order_type`
  ADD PRIMARY KEY (`TYPE_ID`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`ID_PAYMENT`),
  ADD KEY `FK_RELATIONSHIP_9` (`METHOD_ID`);

--
-- Indexes for table `payment_method`
--
ALTER TABLE `payment_method`
  ADD PRIMARY KEY (`METHOD_ID`);

--
-- Indexes for table `shape`
--
ALTER TABLE `shape`
  ADD PRIMARY KEY (`S_ID`);

--
-- Indexes for table `width`
--
ALTER TABLE `width`
  ADD PRIMARY KEY (`W_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cake_order`
--
ALTER TABLE `cake_order`
  MODIFY `ORDER_ID` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `CUST_ID` int(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `custom_cake`
--
ALTER TABLE `custom_cake`
  MODIFY `CUSTOM_ID` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `ID_PAYMENT` int(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cake_order`
--
ALTER TABLE `cake_order`
  ADD CONSTRAINT `FK_RELATIONSHIP_1` FOREIGN KEY (`CUST_ID`) REFERENCES `customer` (`CUST_ID`),
  ADD CONSTRAINT `FK_RELATIONSHIP_13` FOREIGN KEY (`TYPE_ID`) REFERENCES `order_type` (`TYPE_ID`),
  ADD CONSTRAINT `FK_RELATIONSHIP_14` FOREIGN KEY (`ID_PAYMENT`) REFERENCES `payment` (`ID_PAYMENT`),
  ADD CONSTRAINT `FK_RELATIONSHIP_2` FOREIGN KEY (`CUSTOM_ID`) REFERENCES `custom_cake` (`CUSTOM_ID`);

--
-- Constraints for table `custom_cake`
--
ALTER TABLE `custom_cake`
  ADD CONSTRAINT `FK_RELATIONSHIP_3` FOREIGN KEY (`CAKE_ID`) REFERENCES `cake` (`CAKE_ID`),
  ADD CONSTRAINT `FK_RELATIONSHIP_4` FOREIGN KEY (`W_ID`) REFERENCES `width` (`W_ID`),
  ADD CONSTRAINT `FK_RELATIONSHIP_5` FOREIGN KEY (`S_ID`) REFERENCES `shape` (`S_ID`),
  ADD CONSTRAINT `FK_RELATIONSHIP_6` FOREIGN KEY (`FLAVOR_ID`) REFERENCES `flavor` (`FLAVOR_ID`),
  ADD CONSTRAINT `FK_RELATIONSHIP_7` FOREIGN KEY (`LAYER_ID`) REFERENCES `layer` (`LAYER_ID`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `FK_RELATIONSHIP_9` FOREIGN KEY (`METHOD_ID`) REFERENCES `payment_method` (`METHOD_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
