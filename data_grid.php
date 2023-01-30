<table>
	<thead>
		<tr>
			<th>ID Klien</th>
			<th>Nama Klien</th>
			<th>Alamat</th>
			<th>Logo</th>
			<th>Aksi</th>
		</tr>
	</thead>
	<tbody>
		<?php 
			include('koneksi.php');

			if(isset($_GET['kata_cari'])) {
				
				$kata_cari = $_GET['kata_cari'];

				$query = "SELECT * FROM klien2 WHERE Nama_klien like '%".$kata_cari."%' OR Alamat like '%".$kata_cari."%' OR Idklien like '%".$kata_cari."%' ORDER BY No ASC";
			} else {
		
				$query = "SELECT * FROM klien2 ORDER BY No ASC";
			}

			$result = mysqli_query($con, $query);

			if(!$result) {
				die("Query Error : ".mysqli_errno($con)." - ".mysqli_error($con));
			}
		
			while ($row = mysqli_fetch_assoc($result)) {
		?>
		<tr>
			<td style="text-align: left"><?php echo $row['Idklien']; ?></td>
			<td style="text-align: left"><?php echo $row['Nama_klien']; ?></td>
			<td style="text-align: left"><?php echo $row['Alamat']; ?></td>
			<td style="text-align: center;"><img src="/Klien/gambar/<?php echo $row['logo']; ?>" style="width: 120px;"></td>
			<td><a href="index.php?Idklien=<?= $row['Idklien']; ?>">Pilih</a></td>
		</tr>
		<?php
			}
			mysqli_close($con);
		?>
	</tbody>
</table>
