<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $email = $_POST['email'];
    $mezua = $_POST['mezua'];

    $filePath = "kontaktuaGorde.xml";

    if (file_exists($filePath)) {
        $xml = simplexml_load_file($filePath);
    } else {
        $xml = simplexml_load_string("<?xml version='1.0' encoding='UTF-8'?><kontaktuak></kontaktuak>");
    }

    $mezuaElement = $xml->addChild("mezua");
    $mezuaElement->addChild("email", htmlspecialchars($email));
    $mezuaElement->addChild("testua", htmlspecialchars($mezua));

    $xml->asXML($filePath);

    echo "Mezua gorde da: " . htmlspecialchars($filePath);
}
?>