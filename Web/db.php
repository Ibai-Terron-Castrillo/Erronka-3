<?php

function konexioaSortu()
{

    $servername = "172.16.237.169";
    $username = "root";
    $password = "1MG2024";
    $dbname = "erronka3";

    $conn = new mysqli($servername, $username, $password, $dbname);


    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    return $conn;
}