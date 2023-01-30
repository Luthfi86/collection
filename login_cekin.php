<?php

session_start();

include 'koneksi.php';

$redis = new Redis();
$redis->connect('127.0.0.1', 6379);

$username = $_POST['username'];
$password = $_POST['password'];


$cache_key = 'user_' . $username;
$cached_data = $redis->get($cache_key);

if ($cached_data) {
    $_SESSION['username'] = $username;
    $_SESSION['status'] = "login";
    header("location:admin/main.php");
} else {

    $data = mysqli_query($koneksi, "select * from user where username='$username' and password='$password'");
    $cek = mysqli_num_rows($data);
    
    if ($cek > 0) {
        $_SESSION['username'] = $username;
        $_SESSION['status'] = "login";
        
     
        $redis->set($cache_key, $username, 3600);
        
        header("location:admin/main.php");
    } else {
        header("location:index.php?pesan=gagal");
    }
}

?>
