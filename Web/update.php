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
    $theme = $_POST['theme'];

    function errorea($mezua)
    {
        echo "<script>
            alert('$mezua');
            window.location.href='perfil.php';
          </script>";
        exit();
    }

    if ($password !== $passwordconfirm) {
        errorea("Pasahitzak ez datoz bat");
    }

    if ($stmt = $conn->prepare('UPDATE bezeroa SET izena = ?, abizena1 = ?, abizena2 = ?, nan = ?, email = ?, pasahitza = ? WHERE id = ?')) {
        $stmt->bind_param('ssssssi', $izena, $abizena1, $abizena2, $nan, $email, $password, $_SESSION['id']);
        if ($stmt->execute()) {
            $xml = simplexml_load_file('conf.xml');
            $userId = $_SESSION['id'];
            $user = $xml->xpath("//user[id='$userId']")[0];
            $user->theme = $theme;
            $xml->asXML('conf.xml');
            echo "<script>
                alert('Aldaketa Zuzena');
                window.location.href='perfil.php';
              </script>";
        } else {
            errorea("Errorea. Saiatu berriro.");
        }
        $stmt->close();
    } else {
        errorea("Errorea konexioan.");
    }
}
?>