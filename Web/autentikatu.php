<?php
session_start();

require_once("db.php");
$conn = konexioaSortu();

if (isset($_POST['email']) && isset($_POST['password'])) {
    if ($stmt = $conn->prepare('SELECT id, izena, pasahitza FROM bezeroa WHERE email = ?')) {
        $stmt->bind_param('s', $_POST['email']);
        $stmt->execute();
        $stmt->store_result();

        if ($stmt->num_rows > 0) {
            $stmt->bind_result($id, $izena, $pasahitza);
            $stmt->fetch();

            if ($_POST['password'] === $pasahitza) {
                session_regenerate_id();
                $_SESSION['loggedin'] = TRUE;
                $_SESSION['email'] = $_POST['email'];
                $_SESSION['id'] = $id;
                $_SESSION['izena'] = $izena;


                header('Location: index.php');
                exit();
            } else {
                header('Location: index.php');
                exit();
            }
        } else {
            echo "<script>
                    alert('Erabiltzaile hori ez da existitzen');
                    window.location.href='index.php';
                  </script>";
        }
    } else {
        echo "<script>
                    alert('Errorea');
                    window.location.href='index.php';
                  </script>";
    }
    $stmt->close();
}
?>