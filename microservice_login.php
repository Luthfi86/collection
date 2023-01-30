<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

require 'vendor/autoload.php';
require 'koneksi.php';

$app = new \Slim\App;

$app->post('/login', function (Request $request, Response $response) use ($koneksi) {
    session_start();

    $username = $request->getParsedBody()['username'];
    $password = $request->getParsedBody()['password'];

    $data = mysqli_query($koneksi, "select * from user where username='$username' and password='$password'");

    $cek = mysqli_num_rows($data);

    if ($cek > 0) {
        $_SESSION['username'] = $username;
        $_SESSION['status'] = "login";
        return $response->withJson([
            'status' => 'success',
            'message' => 'Login success'
        ]);
    } else {
        return $response->withJson([
            'status' => 'failed',
            'message' => 'Login failed'
        ]);
    }
});

$app->run();
?>