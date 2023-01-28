<?php 

try{
    // connect to localhost
    $serverName = "localhost";
    $userName = "root";
    $password = "";
    $dbName = "dbuas052";
    $tblName = "tbluas052";
    $port = "8111";

    $konn = new PDO("mysql:host=$serverName;port=$port;dbname=$dbName", $userName, $password);
    

    }catch(PDOException $err){

     echo "Koneksi Gagal".$err;

    }
    if($_SERVER['REQUEST_METHOD']==="GET"){
        $msql="select * from $tblName order by id";
        $hasil = $konn->prepare($msql);
        $hasil->execute();
        $data=$hasil->fetchAll(PDO::FETCH_ASSOC);
        echo json_encode($data);
    } else if ($_SERVER['REQUEST_METHOD']==="POST"){
        // $id = $_POST['id'];
        $nama = $_POST['nama'];
        $alamat = $_POST['alamat'];
        $no_penjual = $_POST['no_penjual'];
        $kode_barang = $_POST['kode_barang'];
        $jumlah_penjualan = $_POST['jumlah_penjualan'];
        $harga_satuan = $_POST['harga_satuan'];
        $diskon = $_POST['diskon'];
        $total_harga = $_POST['total_harga'];
        $msql="insert into $tblName(nama,alamat,no_penjual,kode_barang,jumlah_penjualan,harga_satuan,diskon,total_harga) values ('$nama','$alamat','$no_penjual','$kode_barang','$jumlah_penjualan','$harga_satuan','$diskon','$total_harga')";
        $hasil = $konn->prepare($msql);
        $hasil->execute();
        $data=$hasil->fetchAll(PDO::FETCH_ASSOC);
        echo json_encode($data);
    } else if ($_SERVER['REQUEST_METHOD']==="PUT"){
        if (isset($_GET['id'])){
            $id = $_GET['id'];
            $nama = $_GET['nama'];
            $alamat = $_GET['alamat'];
            $no_penjual = $_GET['no_penjual'];
            $kode_barang = $_GET['kode_barang'];
            $jumlah_penjualan = $_GET['jumlah_penjualan'];
            $harga_satuan = $_GET['harga_satuan'];
            $diskon = $_GET['diskon'];
            $total_harga = $_GET['total_harga'];
            $msql="update $tblName set nama='$nama', alamat='$alamat', no_penjual='$no_penjual', kode_barang='$kode_barang', jumlah_penjualan='$jumlah_penjualan', harga_satuan='$harga_satuan', diskon='$diskon', total_harga='$total_harga' WHERE id='$id'";
            $hasil = $konn->prepare($msql);
            $hasil->execute();
            $data=$hasil->fetchAll(PDO::FETCH_ASSOC);
            echo json_encode($data);
        } else {
            echo "ID tidak ditemukan";
        }
    } else if ($_SERVER['REQUEST_METHOD']==="DELETE"){
        $id = $_GET['id'];
        $msql="delete from $tblName where id='$id'";
        $hasil = $konn->prepare($msql);
        $hasil->execute();
        $data=$hasil->fetchAll(PDO::FETCH_ASSOC);
        echo json_encode($data);
    }

?>