package com.example.elservice.data.local

object RegionProvider {

	val provinces = listOf(
		Province(1, "Aceh"),
		Province(2, "Sumatera Utara"),
		Province(3, "Sumatera Barat"),
		Province(4, "Riau"),
		Province(5, "Jambi"),
		Province(6, "Sumatera Selatan"),
		Province(7, "Bangka Belitung"),
		Province(8, "Bengkulu"),
		Province(9, "Lampung"),
		Province(10, "DKI Jakarta"),
		Province(11, "Jawa Barat"),
		Province(12, "Jawa Tengah"),
		Province(13, "DI Yogyakarta"),
		Province(14, "Jawa Timur"),
		Province(15, "Banten"),
		Province(16, "Bali"),
		Province(17, "Nusa Tenggara Barat"),
		Province(18, "Nusa Tenggara Timur"),
		Province(19, "Kalimantan Barat"),
		Province(20, "Kalimantan Tengah"),
		Province(21, "Kalimantan Selatan"),
		Province(22, "Kalimantan Timur"),
		Province(23, "Kalimantan Utara"),
		Province(24, "Sulawesi Utara"),
		Province(25, "Sulawesi Tengah"),
		Province(26, "Sulawesi Selatan"),
		Province(27, "Sulawesi Tenggara"),
		Province(28, "Gorontalo"),
		Province(29, "Sulawesi Barat"),
		Province(30, "Maluku"),
		Province(31, "Maluku Utara"),
		Province(32, "Papua"),
		Province(33, "Papua Barat"),
		Province(34, "Papua Tengah"),
		Province(35, "Papua Pegunungan"),
		Province(36, "Papua Selatan"),
		Province(37, "Papua Barat Daya")
	)

	val cities = listOf(
		// 1. Aceh
		City(1, 1, "Banda Aceh"),
		City(2, 1, "Langsa"),
		City(3, 1, "Lhokseumawe"),
		City(4, 1, "Sabang"),
		City(5, 1, "Subulussalam"),

		// 2. Sumatera Utara
		City(6, 2, "Medan"),
		City(7, 2, "Binjai"),
		City(8, 2, "Pematangsiantar"),
		City(9, 2, "Sibolga"),
		City(10, 2, "Tanjungbalai"),
		City(11, 2, "Tebing Tinggi"),
		City(12, 2, "Padangsidimpuan"),
		City(13, 2, "Gunungsitoli"),

		// 3. Sumatera Barat
		City(14, 3, "Padang"),
		City(15, 3, "Bukittinggi"),
		City(16, 3, "Padang Panjang"),
		City(17, 3, "Pariaman"),
		City(18, 3, "Payakumbuh"),
		City(19, 3, "Sawahlunto"),
		City(20, 3, "Solok"),

		// 4. Riau
		City(21, 4, "Pekanbaru"),
		City(22, 4, "Dumai"),

		// 5. Jambi
		City(23, 5, "Jambi"),
		City(24, 5, "Sungai Penuh"),

		// 6. Sumatera Selatan
		City(25, 6, "Palembang"),
		City(26, 6, "Pagar Alam"),
		City(27, 6, "Prabumulih"),
		City(28, 6, "Lubuklinggau"),

		// 7. Bangka Belitung
		City(29, 7, "Pangkalpinang"),

		// 8. Bengkulu
		City(30, 8, "Bengkulu"),

		// 9. Lampung
		City(31, 9, "Bandar Lampung"),
		City(32, 9, "Metro"),

		// 10. DKI Jakarta (Kota Administrasi)
		City(33, 10, "Jakarta Pusat"),
		City(34, 10, "Jakarta Utara"),
		City(35, 10, "Jakarta Barat"),
		City(36, 10, "Jakarta Selatan"),
		City(37, 10, "Jakarta Timur"),

		// 11. Jawa Barat
		City(38, 11, "Bandung"),
		City(39, 11, "Bekasi"),
		City(40, 11, "Bogor"),
		City(41, 11, "Cimahi"),
		City(42, 11, "Cirebon"),
		City(43, 11, "Depok"),
		City(44, 11, "Sukabumi"),
		City(45, 11, "Tasikmalaya"),
		City(46, 11, "Banjar"),

		// 12. Jawa Tengah
		City(47, 12, "Semarang"),
		City(48, 12, "Magelang"),
		City(49, 12, "Pekalongan"),
		City(50, 12, "Salatiga"),
		City(51, 12, "Surakarta"),
		City(52, 12, "Tegal"),

		// 13. DI Yogyakarta
		City(53, 13, "Yogyakarta"),

		// 14. Jawa Timur
		City(54, 14, "Surabaya"),
		City(55, 14, "Batu"),
		City(56, 14, "Blitar"),
		City(57, 14, "Kediri"),
		City(58, 14, "Madiun"),
		City(59, 14, "Malang"),
		City(60, 14, "Mojokerto"),
		City(61, 14, "Pasuruan"),
		City(62, 14, "Probolinggo"),

		// 15. Banten
		City(63, 15, "Serang"),
		City(64, 15, "Cilegon"),
		City(65, 15, "Tangerang"),
		City(66, 15, "Tangerang Selatan"),

		// 16. Bali
		City(67, 16, "Denpasar"),

		// 17. Nusa Tenggara Barat
		City(68, 17, "Mataram"),
		City(69, 17, "Bima"),

		// 18. Nusa Tenggara Timur
		City(70, 18, "Kupang"),

		// 19. Kalimantan Barat
		City(71, 19, "Pontianak"),
		City(72, 19, "Singkawang"),

		// 20. Kalimantan Tengah
		City(73, 20, "Palangka Raya"),

		// 21. Kalimantan Selatan
		City(74, 21, "Banjarmasin"),
		City(75, 21, "Banjarbaru"),

		// 22. Kalimantan Timur
		City(76, 22, "Samarinda"),
		City(77, 22, "Balikpapan"),
		City(78, 22, "Bontang"),

		// 23. Kalimantan Utara
		City(79, 23, "Tarakan"),

		// 24. Sulawesi Utara
		City(80, 24, "Manado"),
		City(81, 24, "Bitung"),
		City(82, 24, "Kotamobagu"),
		City(83, 24, "Tomohon"),

		// 25. Sulawesi Tengah
		City(84, 25, "Palu"),

		// 26. Sulawesi Selatan
		City(85, 26, "Makassar"),
		City(86, 26, "Parepare"),
		City(87, 26, "Palopo"),

		// 27. Sulawesi Tenggara
		City(88, 27, "Kendari"),
		City(89, 27, "Baubau"),

		// 28. Gorontalo
		City(90, 28, "Gorontalo"),

		// 29. Sulawesi Barat
		City(91, 29, "Mamuju"), // Ibu kota provinsi (secara administratif Kabupaten, namun diisi sebagai representasi utama)

		// 30. Maluku
		City(92, 30, "Ambon"),
		City(93, 30, "Tual"),

		// 31. Maluku Utara
		City(94, 31, "Ternate"),
		City(95, 31, "Tidore Kepulauan"),

		// 32. Papua (Induk)
		City(96, 32, "Jayapura"),

		// 33. Papua Barat
		City(97, 33, "Sorong"),
		// Manokwari (Ibu kota provinsi, belum berstatus Kota)

		// 34. Papua Tengah (Provinsi Baru - Ibu Kota: Nabire)
		City(98, 34, "Nabire"),

		// 35. Papua Pegunungan (Provinsi Baru - Ibu Kota: Jayawijaya/Wamena)
		City(99, 35, "Wamena"),

		// 36. Papua Selatan (Provinsi Baru - Ibu Kota: Merauke)
		City(100, 36, "Merauke"),

		// 37. Papua Barat Daya (Provinsi Baru - Ibu Kota: Sorong)
		City(101, 37, "Sorong"), // Kota Sorong sudah dimasukkan di Papua Barat sebelumnya, namun perlu dimasukkan lagi untuk mencakup Papua Barat Daya
	)

	fun provincesList(): List<Province> = provinces

	fun citiesByProvince(provinceId: Int): List<City> =
		cities.filter { it.provinceId == provinceId }

	fun searchProvinces(query: String): List<Province> =
		provinces.filter { it.name.contains(query, ignoreCase = true) }

	fun searchCities(provinceId: Int, query: String): List<City> =
		citiesByProvince(provinceId)
			.filter { it.name.contains(query, ignoreCase = true) }
}