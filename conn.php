<?php

$db_servername = "localhost";
$db_username = "root";
$db_password = "";
$db_dbname = "checkthisoutbd";

try {
    $conn = new PDO("mysql:host=$db_servername; dbname=$db_dbname",$db_username,$db_password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOExeption $e) {
    die("Algo ha salido mal");
}

?>