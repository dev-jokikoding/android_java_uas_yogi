-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 28, 2023 at 05:18 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbuas052`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbluas052`
--

CREATE TABLE `tbluas052` (
  `id` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `alamat` text NOT NULL,
  `no_penjual` varchar(15) NOT NULL,
  `kode_barang` varchar(50) NOT NULL,
  `jumlah_penjualan` int(11) NOT NULL,
  `harga_satuan` int(11) NOT NULL,
  `diskon` int(11) NOT NULL,
  `total_harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbluas052`
--

INSERT INTO `tbluas052` (`id`, `nama`, `alamat`, `no_penjual`, `kode_barang`, `jumlah_penjualan`, `harga_satuan`, `diskon`, `total_harga`) VALUES
(7, 'Nasi Goreng', 'Jalan Semarang', '0851676754', 'NGR', 4, 17000, 5, 64600);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbluas052`
--
ALTER TABLE `tbluas052`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbluas052`
--
ALTER TABLE `tbluas052`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
