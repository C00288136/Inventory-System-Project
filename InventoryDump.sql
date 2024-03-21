-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 20, 2024 at 11:12 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Inventory`
--
CREATE DATABASE IF NOT EXISTS `Inventory` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `Inventory`;

-- --------------------------------------------------------

--
-- Table structure for table `Employees`
--

CREATE TABLE `Employees` (
  `Emp_ID` int(11) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Role` varchar(30) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `FirstName` varchar(255) DEFAULT NULL,
  `Age` int(11) DEFAULT NULL,
  `Phone_No` varchar(15) DEFAULT NULL,
  `Address` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Employees`
--

INSERT INTO `Employees` (`Emp_ID`, `Username`, `Password`, `Role`, `LastName`, `FirstName`, `Age`, `Phone_No`, `Address`) VALUES
(101, 'admin', '25d55ad283aa400af464c76d713c07ad', '', 'Higgins', 'Bob', 22, '87435384953', '16 hills Av'),
(106, '', '', '', 'Power', 'Mark', 60, '894623784', '230 Carmody Square'),
(108, '', '', '', 'Smith', 'John', 30, '555-1234', '123 Main St'),
(109, '', '', '', 'Johnson', 'Alice', 28, '555-5678', '456 Oak St'),
(110, '', '', '', 'Williams', 'Robert', 35, '555-9876', '789 Pine St'),
(111, '', '', '', 'Davis', 'Emily', 22, '555-4321', '101 Elm St'),
(112, '', '', '', 'Miller', 'Brian', 27, '555-8765', '202 Cedar St'),
(113, '', '', '', 'Jones', 'Jessica', 32, '555-2345', '303 Birch St'),
(114, '', '', '', 'Brown', 'Michael', 29, '555-6543', '404 Maple St'),
(115, '', '', '', 'Wilson', 'Sophia', 26, '555-8765', '505 Walnut St'),
(116, '', '', '', 'Moore', 'David', 31, '555-3210', '606 Pine St'),
(117, '', '', '', 'Taylor', 'Emma', 24, '555-7890', '707 Oak St'),
(118, '', '', '', 'Anderson', 'Christopher', 33, '555-2345', '808 Cedar St'),
(119, '', '', '', 'Thomas', 'Olivia', 23, '555-8765', '909 Birch St'),
(120, '', '', '', 'Jackson', 'Daniel', 28, '555-4321', '111 Elm St'),
(121, '', '', '', 'White', 'Ava', 30, '555-5678', '222 Pine St'),
(122, '', '', '', 'Harris', 'William', 25, '555-7890', '333 Maple St'),
(123, '', '', '', 'Die', 'John', 2312, '4281390', 'nvasdiop'),
(124, '', '', '', 'john', 'dhiua', 31, '312', '3');

-- --------------------------------------------------------

--
-- Table structure for table `Orders`
--

CREATE TABLE `Orders` (
  `Order_ID` int(8) NOT NULL,
  `Emp_ID` int(8) NOT NULL,
  `Stock_ID` int(8) NOT NULL,
  `OrderDate` date NOT NULL,
  `TotalCost` decimal(4,2) NOT NULL,
  `PaymentStatus` enum('paid','pending') NOT NULL,
  `Est_Delivery` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Orders`
--

INSERT INTO `Orders` (`Order_ID`, `Emp_ID`, `Stock_ID`, `OrderDate`, `TotalCost`, `PaymentStatus`, `Est_Delivery`) VALUES
(1, 110, 20, '2024-01-01', 23.55, 'paid', '2024-02-01'),
(2, 110, 20, '2024-01-01', 23.55, 'paid', '2024-02-01');

-- --------------------------------------------------------

--
-- Table structure for table `Sales`
--

CREATE TABLE `Sales` (
  `Sale_ID` int(8) NOT NULL,
  `Emp_ID` int(8) NOT NULL,
  `Stock_ID` int(8) NOT NULL,
  `SaleDate` date NOT NULL,
  `TotalPrice` decimal(4,2) NOT NULL,
  `Quantity` int(3) NOT NULL,
  `Payment_Method` enum('cash','credit','debit','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Sales`
--

INSERT INTO `Sales` (`Sale_ID`, `Emp_ID`, `Stock_ID`, `SaleDate`, `TotalPrice`, `Quantity`, `Payment_Method`) VALUES
(104, 106, 5, '2023-11-30', 15.49, 3, 'credit'),
(320, 109, 20, '2023-12-15', 19.47, 4, 'credit'),
(321, 110, 25, '2023-11-15', 2.98, 1, 'cash'),
(322, 111, 1, '2023-10-10', 11.25, 2, 'credit'),
(323, 112, 6, '2023-12-05', 5.50, 1, 'debit'),
(324, 113, 11, '2023-11-05', 14.75, 3, 'cash'),
(325, 114, 16, '2023-10-02', 8.25, 2, 'credit'),
(326, 115, 21, '2023-10-18', 18.99, 4, 'debit'),
(327, 116, 2, '2023-11-08', 2.25, 1, 'cash'),
(328, 117, 7, '2023-10-12', 16.99, 3, 'credit'),
(329, 118, 12, '2023-11-25', 12.50, 2, 'debit'),
(330, 119, 17, '2023-11-20', 5.98, 1, 'cash'),
(331, 120, 22, '2023-10-05', 7.50, 2, 'credit'),
(332, 121, 20, '2023-11-10', 22.99, 3, 'debit'),
(333, 122, 3, '2023-11-12', 3.75, 1, 'cash'),
(334, 109, 8, '2023-10-22', 13.99, 2, 'credit'),
(335, 109, 13, '2023-11-03', 4.50, 1, 'debit'),
(336, 109, 18, '2023-10-29', 19.99, 4, 'cash'),
(337, 109, 23, '2023-12-01', 11.25, 2, 'credit'),
(338, 110, 3, '2023-12-10', 9.99, 1, 'cash'),
(339, 111, 8, '2023-11-01', 14.50, 3, 'debit'),
(340, 112, 13, '2023-11-16', 2.98, 1, 'cash'),
(341, 113, 23, '2023-11-18', 18.75, 4, 'credit'),
(342, 114, 3, '2023-10-13', 7.50, 2, 'cash'),
(343, 115, 1, '2023-12-23', 6.25, 1, 'debit'),
(344, 116, 23, '2023-12-08', 15.99, 3, 'credit'),
(345, 117, 16, '2023-10-17', 3.75, 1, 'cash'),
(346, 118, 15, '2023-11-26', 11.99, 2, 'debit'),
(347, 119, 4, '2023-12-02', 5.50, 1, 'cash'),
(348, 120, 9, '2023-10-06', 14.99, 3, 'credit'),
(349, 121, 14, '2023-10-21', 9.75, 2, 'debit'),
(350, 122, 19, '2023-11-09', 4.98, 1, 'cash'),
(351, 110, 24, '2023-11-19', 8.99, 2, 'credit'),
(352, 109, 6, '2023-10-27', 25.99, 4, 'debit'),
(353, 108, 11, '2023-10-14', 3.25, 1, 'cash'),
(354, 109, 16, '2023-12-22', 12.99, 2, 'credit'),
(355, 110, 21, '2023-10-30', 7.50, 1, 'debit'),
(356, 111, 2, '2023-10-31', 21.99, 3, 'cash'),
(357, 112, 1, '2023-11-04', 14.25, 3, 'credit'),
(358, 113, 5, '2023-11-13', 29.97, 2, 'debit'),
(359, 114, 10, '2023-11-07', 7.98, 1, 'cash'),
(360, 115, 15, '2023-12-17', 19.47, 4, 'credit'),
(361, 116, 20, '2023-12-16', 2.98, 1, 'cash'),
(362, 117, 25, '2023-11-24', 11.25, 2, 'credit'),
(363, 118, 22, '2023-10-11', 5.50, 1, 'debit'),
(364, 119, 25, '2023-11-23', 14.75, 3, 'cash');

-- --------------------------------------------------------

--
-- Table structure for table `Stock_Items`
--

CREATE TABLE `Stock_Items` (
  `product_ID` int(8) NOT NULL,
  `name` varchar(55) NOT NULL,
  `quantity_in_stock` int(11) NOT NULL,
  `unit_price` decimal(4,2) NOT NULL,
  `sale_price` decimal(4,2) NOT NULL,
  `supplier_ID` int(8) NOT NULL,
  `Aisle_num` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Stock_Items`
--

INSERT INTO `Stock_Items` (`product_ID`, `name`, `quantity_in_stock`, `unit_price`, `sale_price`, `supplier_ID`, `Aisle_num`) VALUES
(1, 'Apple', 150, 1.25, 1.99, 12345678, 1),
(2, 'Banana', 200, 0.75, 1.29, 87654321, 2),
(3, 'Milk (1 gallon)', 50, 2.50, 3.99, 23456789, 3),
(4, 'Bread', 100, 2.00, 2.99, 98765432, 4),
(5, 'Eggs (dozen)', 80, 1.75, 2.49, 13579246, 5),
(6, 'Chicken Breast (per pound)', 60, 3.50, 4.99, 24681357, 6),
(7, 'Potatoes (per pound)', 120, 0.50, 0.79, 11223344, 1),
(8, 'Onions (per pound)', 90, 0.75, 1.29, 55667788, 2),
(9, 'Tomatoes (per pound)', 110, 1.00, 1.49, 99887766, 3),
(10, 'Cereal', 40, 3.00, 4.99, 44332211, 4),
(11, 'Pasta (1 pound)', 150, 1.50, 2.29, 76543210, 5),
(12, 'Rice (2 pounds)', 70, 2.00, 2.99, 98765432, 6),
(13, 'Canned Beans', 100, 1.25, 1.99, 13579246, 1),
(14, 'Peanut Butter', 120, 3.50, 4.99, 11223344, 2),
(15, 'Jelly', 55, 2.00, 2.99, 99887766, 3),
(16, 'Orange Juice (1 quart)', 85, 2.50, 3.99, 44332211, 4),
(17, 'Coffee (1 pound)', 25, 5.00, 7.99, 76543210, 5),
(18, 'Tea Bags (box)', 150, 2.50, 3.99, 98765432, 6),
(19, 'Sugar (2 pounds)', 60, 1.50, 2.29, 13579246, 1),
(20, 'Flour (5 pounds)', 100, 2.00, 2.99, 11223344, 2),
(21, 'Cheese (per pound)', 75, 4.00, 5.99, 99887766, 3),
(22, 'Yogurt (per cup)', 110, 1.25, 1.99, 44332211, 4),
(23, 'Applesauce (jar)', 45, 2.50, 3.99, 76543210, 5),
(24, 'Carrots (per pound)', 80, 0.75, 1.29, 98765432, 6),
(25, 'Broccoli (per pound)', 130, 1.50, 2.29, 13579246, 1),
(26, 'Ground Beef (per pound)', 70, 4.00, 5.99, 11223344, 2),
(27, 'Salmon Fillet (per pound)', 95, 7.00, 9.99, 99887766, 3),
(28, 'Frozen Pizza', 120, 5.00, 7.99, 44332211, 4),
(29, 'Ice Cream (1 quart)', 50, 3.50, 4.99, 76543210, 5),
(30, 'Chips (bag)', 110, 2.00, 2.99, 98765432, 6),
(31, 'Soda (6-pack)', 75, 4.00, 5.99, 13579246, 1),
(32, 'Water (case)', 90, 3.00, 4.99, 11223344, 2),
(33, 'Detergent (bottle)', 40, 5.00, 7.99, 99887766, 3),
(34, 'Soap Bars (pack)', 100, 1.50, 2.29, 44332211, 4),
(35, 'Shampoo', 65, 3.00, 4.99, 76543210, 5),
(36, 'Toothpaste', 80, 2.00, 2.99, 98765432, 6),
(37, 'Toilet Paper (pack)', 150, 6.00, 8.99, 13579246, 1),
(38, 'Tissues (box)', 55, 1.50, 2.29, 11223344, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Employees`
--
ALTER TABLE `Employees`
  ADD PRIMARY KEY (`Emp_ID`);

--
-- Indexes for table `Orders`
--
ALTER TABLE `Orders`
  ADD PRIMARY KEY (`Order_ID`),
  ADD KEY `emps` (`Emp_ID`),
  ADD KEY `products` (`Stock_ID`);

--
-- Indexes for table `Sales`
--
ALTER TABLE `Sales`
  ADD PRIMARY KEY (`Sale_ID`),
  ADD KEY `Emp_ID` (`Emp_ID`),
  ADD KEY `product_ID` (`Stock_ID`),
  ADD KEY `Emp_ID_2` (`Emp_ID`,`Stock_ID`);

--
-- Indexes for table `Stock_Items`
--
ALTER TABLE `Stock_Items`
  ADD PRIMARY KEY (`product_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Employees`
--
ALTER TABLE `Employees`
  MODIFY `Emp_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=125;

--
-- AUTO_INCREMENT for table `Orders`
--
ALTER TABLE `Orders`
  MODIFY `Order_ID` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `Sales`
--
ALTER TABLE `Sales`
  MODIFY `Sale_ID` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=365;

--
-- AUTO_INCREMENT for table `Stock_Items`
--
ALTER TABLE `Stock_Items`
  MODIFY `product_ID` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Orders`
--
ALTER TABLE `Orders`
  ADD CONSTRAINT `emps` FOREIGN KEY (`Emp_ID`) REFERENCES `Employees` (`Emp_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `products` FOREIGN KEY (`Stock_ID`) REFERENCES `Stock_Items` (`product_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `Sales`
--
ALTER TABLE `Sales`
  ADD CONSTRAINT `sales_ibfk_1` FOREIGN KEY (`Stock_ID`) REFERENCES `Stock_Items` (`product_ID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `sales_ibfk_2` FOREIGN KEY (`Emp_ID`) REFERENCES `Employees` (`Emp_ID`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
