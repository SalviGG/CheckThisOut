<?php

    require "conn.php";

    $user_name = $_POST["user_name"];
    $password = $_POST["password"];

    try {
        $conn->beginTransaction();
        $query= "Select * from user where username = '$user_name' and password = '$password'";
        $results = $conn->query($query);
        $conn->commit();
        $datos = array();
        foreach ($results as $row) {
            $datos[]=$row;
        }
    } catch (Exception $e){
        echo $e->getMessage();
    }

    echo json_encode($datos);
?>