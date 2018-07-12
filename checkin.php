<?php

    require "conn.php";

    $user_id = $_REQUEST["user_id"];
    $movie_id = $_REQUEST["movie_id"];
    $movie_name = $_REQUEST["movie_name"];
    

    try {
        $conn->beginTransaction();
        $query= "insert into Movie (idMovie,movieName,checkedin)
                values('$movie_id','$movie_name',1)";
        $conn->exec($query);
        //$conn->commit();
        try{
            $query2 = "insert into userMovie values('$user_id','$movie_id')";
            $conn->exec($query2);
            $conn->commit();
            echo "Check-in exitoso";
        }catch(Exception $e){
            echo $e->getMessage();

        }
        
    } catch (Exception $e){
        echo $e->getMessage();
    }

?>