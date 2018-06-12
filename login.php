<?php
require "conn.php";
$user_name = $_POST["user_name"];
$user_password = $_POST["password"];
$mysql_query = "select * from users where username like '$user_name' and password like '$user_password';";
$result = mysqli_query($conn, $mysql_query);
if(mysqli_num_rows($result)>0){
	$val = "0";
}
else{
	$val = "1";
}
echo $val;
?>