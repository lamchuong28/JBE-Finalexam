
CREATE DATABASE r2sshop;


-- Dumping data for table r2sshop.table_address: ~1 rows (approximately)
INSERT INTO `table_address` (`id`, `user_id`, `detail_address`) VALUES
	(2, 1, 'Thôn 1, Phước Thể, Tuy Phong, Bình Thuận');

-- Dumping data for table r2sshop.table_cart: ~2 rows (approximately)
INSERT INTO `table_cart` (`created_date`, `id`, `user_id`) VALUES
	('2024-06-08 18:04:59.000000', 1, 1),
	('2024-06-08 21:08:52.000000', 2, 2);

-- Dumping data for table r2sshop.table_cartline_item: ~10 rows (approximately)
INSERT INTO `table_cartline_item` (`is_deleted`, `total_price`, `added_date`, `cart_id`, `id`, `order_id`, `quantity`, `variant_product_id`) VALUES
	(b'1', 88980000.00, '2024-06-17 13:14:36.000000', 1, 1, NULL, 2, 1),
	(b'1', 46990000.00, '2024-06-17 13:15:42.000000', 1, 2, NULL, 1, 2),
	(b'1', 88980000.00, '2024-06-17 13:33:42.000000', NULL, 3, 3, 2, 1),
	(b'1', 46990000.00, '2024-06-17 13:33:42.000000', NULL, 4, 3, 1, 2),
	(b'1', 88980000.00, '2024-06-17 13:37:48.000000', NULL, 5, 4, 2, 1),
	(b'1', 46990000.00, '2024-06-17 13:37:48.000000', NULL, 6, 4, 1, 2),
	(b'1', 46990000.00, '2024-06-17 13:38:14.000000', 1, 7, NULL, 1, 2),
	(b'1', 88980000.00, '2024-06-17 13:39:12.000000', NULL, 8, 5, 2, 1),
	(b'1', 46990000.00, '2024-06-17 13:39:12.000000', NULL, 9, 5, 1, 2),
	(b'1', 46990000.00, '2024-06-17 13:39:12.000000', NULL, 10, 5, 1, 2);

-- Dumping data for table r2sshop.table_category: ~7 rows (approximately)
INSERT INTO `table_category` (`id`, `description`, `name`) VALUES
	(1, 'Tổng hợp các dòng điện thoại', 'Điện thoại'),
	(2, 'Tổng hợp các dòng máy tính xách tay', 'Laptop'),
	(3, 'Tổng hợp các dòng máy tính bảng', 'Tablet'),
	(4, 'Tổng hợp các dòng như  tai nghe, dây sạc, củ sạc, sạc dự phòng,v.v', 'Phụ kiện'),
	(5, 'Tổng hợp các dòng đồng hộ điện tử', 'Smartwatch'),
	(6, 'Tổng hợp các dòng đồng hồ thời trang', 'Đồng hồ'),
	(7, 'Tổng hợp các dòng máy tính để bàn và máy in', 'PC-Máy in');

-- Dumping data for table r2sshop.table_order: ~3 rows (approximately)
INSERT INTO `table_order` (`total_price`, `delivery_time`, `id`, `address`) VALUES
	(135970000, '2024-06-18 22:30:00.000000', 3, 'Phước Thể'),
	(0, '2024-06-18 22:30:00.000000', 4, 'Phước Thể'),
	(46990000, '2024-06-18 22:30:00.000000', 5, 'Phước Thể');

-- Dumping data for table r2sshop.table_product: ~32 rows (approximately)
INSERT INTO `table_product` (`category_id`, `id`, `name`) VALUES
	(1, 1, 'Android'),
	(1, 2, 'IPhone'),
	(1, 3, 'Điện thoại phổ thông'),
	(2, 4, 'Gaming'),
	(2, 5, 'MacBook'),
	(2, 6, 'Học tập - Văn phòng'),
	(2, 7, 'Đồ họa - Kỹ thuật'),
	(2, 8, 'Mỏng nhẹ'),
	(2, 9, 'Cao cấp, sang trọng'),
	(2, 10, 'Phần mềm'),
	(3, 11, 'IPadOS'),
	(3, 12, 'Android'),
	(4, 13, 'Phụ kiện di động'),
	(4, 14, 'Phụ kiện laptop'),
	(4, 15, 'Thiết bị âm thanh'),
	(4, 16, 'Thiết bị nhà thông minh'),
	(4, 17, 'Thiết bị lưu trữ'),
	(4, 18, 'Phụ kiện khác'),
	(5, 19, 'Thời trang sành điệu'),
	(5, 20, 'Thể thao chuyên nghiệp'),
	(5, 21, 'Định vị trẻ em'),
	(5, 22, 'Vòng đeo tay thông minh'),
	(5, 23, 'Dây đồng hồ'),
	(6, 24, 'Nam'),
	(6, 25, 'Nữ'),
	(6, 26, 'Trẻ em'),
	(7, 27, 'Máy in'),
	(7, 28, 'Mực in'),
	(7, 29, 'Màn hình máy tính'),
	(7, 30, 'Máy tính để bàn'),
	(7, 31, 'Máy chơi game'),
	(2, 32, 'Old Product');

-- Dumping data for table r2sshop.table_roles: ~2 rows (approximately)
INSERT INTO `table_roles` (`id`, `col_description`, `col_name`) VALUES
	(1, 'Administrator role', 'ADMIN'),
	(2, 'User role', 'USER');

-- Dumping data for table r2sshop.table_role_user: ~2 rows (approximately)
INSERT INTO `table_role_user` (`role_id`, `user_id`) VALUES
	(2, 1),
	(1, 2);

-- Dumping data for table r2sshop.table_users: ~2 rows (approximately)
INSERT INTO `table_users` (`id`, `email`, `full_name`, `pass_word`, `user_name`) VALUES
	(1, 'newuser1@example.com', 'New User1', '$2a$10$O7n4cZ9FzeLqpFAuAC/X1O9ZsRFweA3eFsuUt5DQmPeHOip.ehtFu', 'newuser1'),
	(2, 'updateduser3@example.com', 'Updated User3', '$2a$10$nGBcmhr0GFp/5Hl/M80NrO.MtMHUERU.9hbn.Hpv0Py4P0vxUyIBi', 'newuser2');

-- Dumping data for table r2sshop.table_variant_product: ~19 rows (approximately)
INSERT INTO `table_variant_product` (`price`, `id`, `product_id`, `color`, `model`, `size`) VALUES
	(44490000.00, 1, 1, 'Xám', 'Điện thoại Samsung Galaxy S24 Ultra 5G', 'Dài 162.3mm - Ngang 79mm - Dày 8.6mm - Nặng 232g'),
	(46990000.00, 2, 2, 'Titan tự nhiên', 'Điện thoại iPhone 15 Pro Max 1TB', 'Dài 159.9mm - Ngang 76.7mm - Dày 8.25mm - Nặng 221g'),
	(1590000.00, 3, 3, 'Đỏ', 'Điện thoại Nokia 8210 4G', 'Dài 131.3mm - Ngang 56.2mm - Dày 13.8mm - Nặng 107g'),
	(64990000.00, 4, 4, 'Xanh', 'Laptop MSI Gaming Stealth 16 AI Studio A1VGG Ultra 9 185H/32GB/2TB/8GB RTX4070/240Hz/Balo/Chuột/Win11', 'Dài 355.8mm - Rộng 259.7mm - Dày 19.95mm - Nặng 1.99kg'),
	(79990000.00, 5, 5, 'Đen', 'Laptop Apple MacBook Pro 16 inch M3 Pro 36GB/1TB', 'Dài 355.7mm - Rộng 248.1mm - Dày 16.8mm - Nặng 2.14kg'),
	(16990000.00, 6, 6, 'Bạc', 'Laptop Dell Inspiron 15 3520 i5 1235U/16GB/512GB/120Hz/OfficeHS/KYHD/Win11', 'Dài 358.5mm - Rộng 235.56mm - Dày 18.99mm - Nặng 1.9kg'),
	(28490000.00, 7, 7, 'Bạc', 'Laptop Asus Vivobook 14X OLED K3405VC i5 13500H/16GB/512GB/4GB RTX3050/Win11', 'Dài 317.1mm - Rộng 222.5mm - Dày 18.9mm - Nặng 1.4kg'),
	(52590000.00, 8, 8, 'Xanh', 'Laptop HP Elitebook Dragonfly G3 i7 1255U/16GB/1TB SSD/Touch/Win11 Pro', 'Dài 297.4mm - Rộng 220.4mm - Dày 16.4mm - Nặng 0.99kg'),
	(55990000.00, 9, 9, 'Xám', 'Laptop MSI Prestige 16 AI Studio B1VFG Ultra 9 185H/32GB/1TB/8GB RTX4060/Balo/Chuột/Win11', 'Dài 358.4mm - Rộng 254.4mm - Dày 18.95mm - Nặng 1.6kg'),
	(2990000.00, 10, 10, 'Trắng', 'Microsoft Office Home & Student 2021 chính hãng (Vĩnh viễn, 1 thiết bị Windows/Mac)', 'Không'),
	(43490000.00, 11, 11, 'Bạc', 'Máy tính bảng iPad Pro M4 13 inch 5G 256GB', 'Dài 281.6mm - Ngang 215.5mm - Dày 5.1mm - Nặng 582g'),
	(30990000.00, 12, 12, 'Kem', 'Máy tính bảng Samsung Galaxy Tab S9+ 5G 512GB', 'Dài 285.4mm - Ngang 185.4mm - Dày 5.7mm - Nặng 586g'),
	(44490000.00, 13, 1, 'Vàng', 'Điện thoại Samsung Galaxy S24 Ultra 5G', 'Dài 162.3mm - Ngang 79mm - Dày 8.6mm - Nặng 232g'),
	(44490000.00, 14, 1, 'Tím', 'Điện thoại Samsung Galaxy S24 Ultra 5G', 'Dài 162.3mm - Ngang 79mm - Dày 8.6mm - Nặng 232g'),
	(44490000.00, 15, 1, 'Đen', 'Điện thoại Samsung Galaxy S24 Ultra 5G', 'Dài 162.3mm - Ngang 79mm - Dày 8.6mm - Nặng 232g'),
	(46990000.00, 16, 2, 'Titan xanh', 'Điện thoại iPhone 15 Pro Max 1TB', 'Dài 159.9mm - Ngang 76.7mm - Dày 8.25mm - Nặng 221g'),
	(46990000.00, 17, 2, 'Titan trắng', 'Điện thoại iPhone 15 Pro Max 1TB', 'Dài 159.9mm - Ngang 76.7mm - Dày 8.25mm - Nặng 221g'),
	(46990000.00, 18, 2, 'Titan đen', 'Điện thoại iPhone 15 Pro Max 1TB', 'Dài 159.9mm - Ngang 76.7mm - Dày 8.25mm - Nặng 221g'),
	(5690000.00, 19, 15, 'Trắng', 'Tai nghe Bluetooth AirPods Pro Gen 2 MagSafe Charge (USB-C) Apple MTJV3', 'Dài 3.09 cm - Rộng 2.18 cm - Cao 2.17 cm');
