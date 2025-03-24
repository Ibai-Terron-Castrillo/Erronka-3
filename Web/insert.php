<?php
session_start();
require_once("db.php");
$conn = konexioaSortu();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $izena = $_POST['izena'];
    $abizena1 = $_POST['abizena1'];
    $abizena2 = $_POST['abizena2'] ?? null;
    $nan = $_POST['nan'];
    $email = $_POST['email'];
    $password = $_POST['pass'];
    $passwordconfirm = $_POST['passconfirm'];

    function errorea($mezua)
    {
        echo "<script>
            alert('$mezua');
            window.location.href='erregistratu.php';
          </script>";
        exit();
    }

    if ($password !== $passwordconfirm) {
        errorea("Pasahitzak ez datoz bat");
    }

    if ($stmt = $conn->prepare('SELECT id FROM bezeroa WHERE email = ?')) {
        $stmt->bind_param('s', $email);
        $stmt->execute();
        $stmt->store_result();

        if ($stmt->num_rows > 0) {
            errorea("Erabiltzaile hori dagoeneko existitzen da");
        }
        $stmt->close();
    }

    if ($stmt = $conn->prepare('INSERT INTO bezeroa (izena, abizena1, abizena2, nan, email, pasahitza) VALUES (?, ?, ?, ?, ?, ?)')) {
        $stmt->bind_param('ssssss', $izena, $abizena1, $abizena2, $nan, $email, $password);
        if ($stmt->execute()) {
            $userId = $stmt->insert_id;

            $xml = new DOMDocument();
            $xml->load('conf.xml');

            $user = $xml->createElement('user');

            $id = $xml->createElement('id', $userId);
            $user->appendChild($id);

            $theme = $xml->createElement('theme', 'night');
            $user->appendChild($theme);

            $xml->getElementsByTagName('configuration')->item(0)->appendChild($user);

            $xml->save('conf.xml');

            echo "<script>
                alert('Erregistro Zuzena');
                window.location.href='index.php';
              </script>";
        } else {
            errorea("Errorea erregistratzean. Saiatu berriro.");
        }
        $stmt->close();
    } else {
        errorea("Errorea konexioan.");
    }
}
?>