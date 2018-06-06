<?php

    require "conn.php";

    $user_name = $_REQUEST["user_name"];
    $password = $_REQUEST["password"];

    try {
        $conn->beginTransaction();
        $query= "Select * from user where username = '$user_name' and password = '$password'";
        $results = $conn->prepare($query);
        $results->execute();
        $datos = array();
        while ($row=$results->fetch(PDO::FETCH_ASSOC)){
            $datos['users'][] = $row;
        }
    } catch (Exception $e){
        echo $e->getMessage();
    }

    echo json_encode($datos);
?>